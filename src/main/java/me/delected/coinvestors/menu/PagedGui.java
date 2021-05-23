package me.delected.coinvestors.menu;

import java.util.List;

import org.bukkit.entity.Player;

import me.delected.coinvestors.Coinvestors;

public abstract class PagedGui<T> extends GuiStage {

	private final int size = 45;
	private List<T> rawItems;
	private int page = 0;

	private static final String NEXT_PAGE = "PAGED_GUI_NEXT_PAGE";
	private static final String PREV_PAGE = "PAGED_GUI_PREV_PAGE";
	private static final String ITEM_SELECT = "PAGED_GUI_SELECTION_CONFIRM";

	protected PagedGui(final MenuState state) {
		super(state);
	}

	protected abstract void buildPageInventory();

	private void displayNextPage() {
		if (page < rawItems.size() / 45) {
			page++;
			buildPageInventory();
		}
	}

	private void displayPrevPage() {
		if (page > 0) {
			page--;
			buildPageInventory();
		}
	}

	private static void displayNextPage(Player player) {
		((PagedGui<?>) Coinvestors.guiManager().getStateOf(player).getActualStage()).displayNextPage();
	}

	private static void displayPrevPage(Player player) {
		((PagedGui<?>) Coinvestors.guiManager().getStateOf(player).getActualStage()).displayPrevPage();
	}

}
