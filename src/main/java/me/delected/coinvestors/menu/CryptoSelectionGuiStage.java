package me.delected.coinvestors.menu;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

public class CryptoSelectionGuiStage extends GuiStage {

	private static final String NEXT_PAGE = "CRYPTO_SELECT_NEXT_PAGE";
	private static final String PREV_PAGE = "CRYPTO_SELECT_PREV_PAGE";
	private static final String SELECT_CONFIRM = "CRYPTO_SELECTION_CONFIRM";
	private static final List<ItemStack> stacks = createInfoStacks();
	private static final String TITLE = ChatColor.DARK_RED + "Select your crypto currency!";

	static {
		MenuLinker.registerLink(NEXT_PAGE, CryptoSelectionGuiStage::displayNextPage);
		MenuLinker.registerLink(PREV_PAGE, CryptoSelectionGuiStage::displayPrevPage);
		MenuLinker.registerEventLink(SELECT_CONFIRM, CryptoSelectionGuiStage::selectCrypto);
	}

	private final Inventory inventory;
	private int page = 0;
	private final Consumer<Crypto> onSelect;

	protected CryptoSelectionGuiStage(final Consumer<Crypto> onSelect) {
		super(MenuState.CRYPTO_SELECT);
		this.onSelect = onSelect;
		inventory = Bukkit.createInventory(null, 54, TITLE);
		buildPageInventory();
	}


	@Override
	public Inventory build(final Player player) {
		return inventory;
	}

	private static List<ItemStack> createInfoStacks() {
		return Arrays.stream(Crypto.values()).map(CryptoSelectionGuiStage::cryptoInfo).collect(Collectors.toList());
	}

	private static ItemStack cryptoInfo(Crypto crypto) {
		return new ItemStackCreator(Material.SUNFLOWER).setName(crypto.getFullName())
				.setEventLink(SELECT_CONFIRM).build();
	}

	private ItemStack nextPage() {
		return new ItemStackCreator(Material.LIME_STAINED_GLASS_PANE).setName("Next page").setLink(NEXT_PAGE).build();
	}

	private ItemStack prevPage() {
		return new ItemStackCreator(Material.LIME_STAINED_GLASS_PANE).setName("Previous page")
				.setLink(PREV_PAGE).build();
	}

	private void buildPageInventory() {
		inventory.clear();
		inventory.setItem(45, prevPage());
		inventory.setItem(53, nextPage());
		stacks.stream().skip(page * 45L).limit(45L).forEach(inventory::addItem);
	}

	private static void displayNextPage(Player player) {
		((CryptoSelectionGuiStage) Coinvestors.getManager().getStateOf(player).getActualStage()).displayNextPage();
	}

	private static void displayPrevPage(Player player) {
		((CryptoSelectionGuiStage) Coinvestors.getManager().getStateOf(player).getActualStage()).displayPrevPage();
	}

	private void displayNextPage() {
		if (page < stacks.size() / 45) {
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

	private static void selectCrypto(Player player, InventoryClickEvent e) {
		CryptoSelectionGuiStage guiStage = (CryptoSelectionGuiStage) Coinvestors.getManager().getStateOf(player).getActualStage();
		Crypto crypto = Crypto.values()[45 * guiStage.page + e.getSlot()];
		guiStage.onSelect.accept(crypto);
	}

}
