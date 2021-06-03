package me.delected.coinvestors.menu;

import static org.bukkit.Material.BARRIER;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.newmenu.Confirmable;
import me.delected.coinvestors.menu.deprecated.ExchangeGui;
import me.delected.coinvestors.menu.newmenu.AccountCreationGuiStage;
import me.delected.coinvestors.menu.newmenu.MenuGuiState;
import me.delected.coinvestors.menu.newmenu.MenuState;
import me.delected.coinvestors.util.ItemStackCreator;

public abstract class GuiStage {

	protected static final String MENU_CLOSE_LINK = "CLOSE_LINK";
	protected static final String MAIN_MENU_LINK = "MAIN_MENU_LINK";

	static {
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
		return returnLinkStack(MAIN_MENU_LINK, ChatColor.RED + "Back to main menu", lore);
	}

	protected static ItemStack returnLinkStack(String link, String name, String... lore) {
		return new ItemStackCreator(Material.BARRIER)
				.setLink(link)
				.setName(name)
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

	protected static <T extends GuiStage> T actualStage(Player player, Class<T> guiCLass) {
		return guiCLass.cast(actualStage(player));
	}

}
