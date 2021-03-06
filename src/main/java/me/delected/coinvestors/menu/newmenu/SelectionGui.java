package me.delected.coinvestors.menu.newmenu;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.util.ItemStackCreator;

public abstract class SelectionGui<T> extends PagedGui {

	protected static final String SELECT_CONFIRM = "SELECT_GUI_CONFIRM";
	private final Supplier<List<ItemStack>> supplier;
	private final Function<T, GuiStage> next;
	private List<T> collection;

	static {
		MenuLinker.registerEventLink(SELECT_CONFIRM, SelectionGui::select);
	}

	private final Consumer<T> tConsumer;

	protected SelectionGui(final int size, final String title, final Consumer<T> tConsumer,
						   final Supplier<List<T>> rawDataSupplier, final Function<T, ItemStack> renderer,
						   final GuiStage prev,
						   final Function<T, GuiStage> next) {
		super(MenuState.INPUT, size, title, prev);
		this.tConsumer = tConsumer;
		this.supplier = () -> {
			collection = rawDataSupplier.get();
			return collection.stream().map(renderer).collect(Collectors.toList());
		};
		this.next = next;
		reloadData();
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = super.build(player);
		result.setItem(result.getSize() - 2, abortStack());
		return result;
	}

	protected ItemStack abortStack() {
		return returnStack(ChatColor.RED + "Abort input!", ChatColor.WHITE + "Returns to the previous menu");
	}

	@Override
	protected final List<ItemStack> renderData() {
		return supplier.get().stream().map(ItemStackCreator::new)
				.map(i -> i.setEventLink(SELECT_CONFIRM).build())
				.collect(Collectors.toList());
	}

	protected T retrieveT(final InventoryClickEvent e) {
		return collection.get(getFirstField() + e.getSlot());
	}


	private void acceptT(Player player, InventoryClickEvent e) {
		T t = this.retrieveT(e);
		tConsumer.accept(t);
		Coinvestors.guiManager().redirect(player, next.apply(t));
	}

	private static void select(Player player, InventoryClickEvent e) {
		SelectionGui<?> guiStage = (SelectionGui<?>) Coinvestors.guiManager().getStateOf(player).getActualStage();
		guiStage.acceptT(player, e);
	}


	public static class Builder<T> {
		private final String name;
		private final int size;
		private Consumer<T> tConsumer;
		private Supplier<List<T>> rawDataSupplier;
		private Function<T, ItemStack> renderer;
		private GuiStage prev;
		private GuiStage next;

		public Builder(final String name, final int size) {
			this.name = name;
			this.size = size;
		}

		public Builder<T> consumer(final Consumer<T> tConsumer) {
			this.tConsumer = tConsumer;
			return this;
		}

		public Builder<T> dataSupplier(final Supplier<List<T>> rawDataSupplier) {
			this.rawDataSupplier = rawDataSupplier;
			return this;
		}

		public Builder<T> renderer(final Function<T, ItemStack> renderer) {
			this.renderer = renderer;
			return this;
		}

		public Builder<T> prevGui(final GuiStage previous) {
			this.prev = previous;
			return this;
		}

		public Builder<T> nextGui(final GuiStage next) {
			this.next = next;
			return this;
		}

		public void buildAndOpen(Player p) {
			Coinvestors.guiManager().redirect(p, this.build());
		}

		private SelectionGui<T> build() {
			if (name == null || tConsumer == null || rawDataSupplier == null
				|| renderer == null || prev == null || next == null) {
				throw new IllegalArgumentException("one or more arguments was null!");
			}
			return new SelectionGui<T>(size, name, tConsumer, rawDataSupplier, renderer, prev, t -> next) {
			};
		}
	}
}
