package me.delected.coinvestors.menu;

import static org.bukkit.Material.BARRIER;
import static org.bukkit.Material.WITHER_SKELETON_SKULL;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.util.ItemStackCreator;

public abstract class GuiStage {

	protected static final String MENU_CLOSE_LINK = "CLOSE_LINK";
	protected static final String MAIN_MENU_LINK = "MAIN_MENU_LINK";

	static {
		MenuLinker.registerLink(TransactionGui.SOURCE_INPUT_LINK, TransactionGui::openSourceInput);
		MenuLinker.registerLink(TransactionGui.DESTINATION_INPUT_LINK, TransactionGui::openTargetInput);
		MenuLinker.registerLink(TransactionGui.AMOUNT_INPUT_LINK, TransactionGui::openAmountInput);
		MenuLinker.registerLink(ExchangeGui.TARGET_CRYPTO_INPUT_LINK, ExchangeGui::openTargetCryptoInput);
		MenuLinker.registerLink(ExchangeGui.SOURCE_CRYPTO_INPUT_LINK, ExchangeGui::openSourceCryptoInput);
		MenuLinker.registerLink(Confirmable.CONFIRM_LINK, Confirmable::confirm);
		MenuLinker.registerLink(MENU_CLOSE_LINK, Player::closeInventory);
		MenuLinker.registerLink(MAIN_MENU_LINK, GuiStage::toMainMenu);
	}

	private final MenuState state;

	protected GuiStage(final MenuState state) {
		this.state = state;
	}

	public abstract Inventory build(Player player);

	public MenuState getState() {
		return state;
	}

	protected static ItemStack backLinkStack(String... lore) {
		return new ItemStackCreator(Material.BARRIER)
				.setLink(MAIN_MENU_LINK)
				.setName(ChatColor.RED + "Back to main menu")
				.setLore(lore)
				.build();
	}

	protected static ItemStack closeStack(String... lore) {
		return new ItemStackCreator(BARRIER).setName(ChatColor.RED + "Close")
				.setLore(lore)
				.setLink(MENU_CLOSE_LINK)
				.build();
	}

	protected static void redirect(Player player, GuiStage next) {
		Coinvestors.guiManager().redirect(player, next);
	}

	public static void toMainMenu(Player player) {
		redirect(player, new MenuGuiState());
	}

	public static void toAccountCreation(Player player) {
		redirect(player, new AccountCreationGuiStage());
	}

	protected static GuiStage actualStage(Player player) {
		return Coinvestors.guiManager().getStateOf(player).getActualStage();
	}
}
