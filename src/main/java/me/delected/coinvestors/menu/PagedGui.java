package me.delected.coinvestors.menu;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.util.ItemStackCreator;

public abstract class PagedGui extends GuiStage {

	private static final String NEXT_PAGE = "PAGED_GUI_NEXT_PAGE";
	private static final String PREV_PAGE = "PAGED_GUI_PREV_PAGE";

	static {
		MenuLinker.registerLink(NEXT_PAGE, PagedGui::displayNextPage);
		MenuLinker.registerLink(PREV_PAGE, PagedGui::displayPrevPage);
	}

	private final int size;
	private final int itemSpace;
	private int page = 0;
	private final Inventory inventory;

	protected PagedGui(final MenuState state, final int size, String title) {
		super(state);
		if (size < 18) {
			throw new IllegalArgumentException("Inventory must have at least 2 full rows!");
		}
		this.size = size;
		inventory = Bukkit.createInventory(null, size, title);
		this.itemSpace = size - 9;
	}

	@Override
	public Inventory build(final Player player) {
		return inventory;
	}

	protected void buildPageInventory() {
		inventory.clear();
		inventory.setItem(itemSpace, prevPage());
		inventory.setItem(size - 1, nextPage());
		getRawItems().stream().skip(getFirstField()).limit(itemSpace).forEach(inventory::addItem);
	}

	protected abstract List<ItemStack> getRawItems();

	private void displayNextPage() {
		if (page < getRawItems().size() / itemSpace) {
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

	private ItemStack nextPage() {
		return new ItemStackCreator(Material.LIME_STAINED_GLASS_PANE).setName("Next page").setLink(NEXT_PAGE).build();
	}

	private ItemStack prevPage() {
		return new ItemStackCreator(Material.LIME_STAINED_GLASS_PANE).setName("Previous page")
				.setLink(PREV_PAGE).build();
	}

	private static void displayNextPage(Player player) {
		((PagedGui) Coinvestors.guiManager().getStateOf(player).getActualStage()).displayNextPage();
	}

	private static void displayPrevPage(Player player) {
		((PagedGui) Coinvestors.guiManager().getStateOf(player).getActualStage()).displayPrevPage();
	}

	protected int getFirstField() {
		return page * itemSpace;
	}
}
