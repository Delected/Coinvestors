package me.delected.coinvestors.menu;

import static org.bukkit.Material.BARRIER;
import static org.bukkit.Material.BLUE_WOOL;
import static org.bukkit.Material.BROWN_WOOL;
import static org.bukkit.Material.GREEN_WOOL;
import static org.bukkit.Material.LIGHT_BLUE_WOOL;
import static org.bukkit.Material.LIME_WOOL;
import static org.bukkit.Material.RED_WOOL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.util.ItemStackCreator;

public class MenuGuiState extends GuiStage {

	private static final Inventory INVENTORY = createInventory();
	private static final String MENU_TRANSACTION_LINK = "MENU_TO_TRANSACTION";
	private static final String MENU_WALLET_CREATION_LINK = "MENU_TO_WALLET_CREATION";

	static {
		MenuLinker.registerLink(MENU_TRANSACTION_LINK, MenuGuiState::onMenuToTransactionClick);
		MenuLinker.registerLink(MENU_WALLET_CREATION_LINK, MenuGuiState::onMenuToWalletCreationClick);
	}

	public MenuGuiState() {
		super(MenuState.MENU);
	}

	private static Inventory createInventory() {
		Inventory result = Bukkit.createInventory(null, 9, "MENU");
		ItemStack[] menu = new ItemStack[9];
		menu[0] = new ItemStackCreator(RED_WOOL).setName(ChatColor.GREEN + "My Wallets")
				.setUnmodifiable().build();
		menu[1] = new ItemStackCreator(BLUE_WOOL).setLink(MENU_TRANSACTION_LINK)
				.setName(ChatColor.GREEN + "Make transaction").build();
		menu[2] = new ItemStackCreator(GREEN_WOOL).setName(ChatColor.GREEN + "Deposit money").setUnmodifiable().build();
		menu[3] = new ItemStackCreator(BROWN_WOOL).setName(ChatColor.YELLOW + "Withdraw money")
				.setUnmodifiable().build();
		menu[4] = new ItemStackCreator(LIGHT_BLUE_WOOL).setName(ChatColor.GREEN + "Exchange").setUnmodifiable().build();
		menu[5] = new ItemStackCreator(LIME_WOOL).setName(ChatColor.GREEN + "Create new Wallet")
				.setLink(MENU_WALLET_CREATION_LINK).build();
		menu[8] = new ItemStackCreator(BARRIER).setName(ChatColor.RED + "Close").setLink(MENU_CLOSE_LINK).build();
		result.setContents(menu);
		return result;
	}

	private static void onMenuToTransactionClick(Player player) {
		redirect(player, new TransactionGuiState());
	}

	private static void onMenuToWalletCreationClick(Player player) {
		redirect(player, new WalletCreationGuiStage());
	}

	@Override
	public Inventory build(final Player player) {
		return INVENTORY;
	}

}
