package me.delected.coinvestors.menu.newmnu;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.menu.MenuState;

public abstract class SelectionGui<T> extends PagedGui {

	protected static final String SELECT_CONFIRM = "SELECT_GUI_CONFIRM";
	private final Supplier<List<ItemStack>> supplier;
	private final GuiStage next;
	private List<T> collection;

	static {
		MenuLinker.registerEventLink(SELECT_CONFIRM, SelectionGui::select);
	}

	private final Consumer<T> tConsumer;

	protected SelectionGui(final int size, final String title, final Consumer<T> tConsumer,
						   final Supplier<List<T>> rawDataSupplier, final Function<T, ItemStack> renderer,
						   final GuiStage next) {
		super(MenuState.INPUT, size, title);
		this.tConsumer = tConsumer;
		this.supplier = () -> {
			collection = rawDataSupplier.get();
			return collection.stream().map(renderer).collect(Collectors.toList());
		};
		this.next = next;
	}


	@Override
	protected final List<ItemStack> renderData() {
		return supplier.get();
	}

	protected T retrieveT(final InventoryClickEvent e) {
		return collection.get(getFirstField() + e.getSlot());
	}


	private void acceptT(Player player, InventoryClickEvent e) {
		T t = this.retrieveT(e);
		tConsumer.accept(t);
		Coinvestors.guiManager().redirect(player, next);
	}

	private static void select(Player player, InventoryClickEvent e) {
		SelectionGui<?> guiStage = (SelectionGui<?>) Coinvestors.guiManager().getStateOf(player).getActualStage();
		guiStage.acceptT(player, e);
	}


}
