package me.delected.coinvestors.menu;

import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;

public abstract class SelectionGui<T> extends PagedGui {

	protected static final String SELECT_CONFIRM = "SELECT_GUI_CONFIRM";
	private T data;

	static {
		MenuLinker.registerEventLink(SELECT_CONFIRM, SelectionGui::select);
	}

	private final Consumer<T> tConsumer;

	protected SelectionGui(final MenuState state, final int size, final String title, final Consumer<T> tConsumer) {
		super(state, size, title);
		this.tConsumer = tConsumer;
	}

	protected abstract T retrieveT(Player player, InventoryClickEvent e);


	private void acceptT(Player player, InventoryClickEvent e) {
		T t = this.retrieveT(player, e);
		tConsumer.accept(t);
	}

	private static void select(Player player, InventoryClickEvent e) {
		SelectionGui<?> guiStage = (SelectionGui<?>) Coinvestors.guiManager().getStateOf(player).getActualStage();
		guiStage.acceptT(player, e);
	}

}
