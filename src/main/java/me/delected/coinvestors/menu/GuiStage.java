package me.delected.coinvestors.menu;

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
		MenuLinker.registerLink(MENU_CLOSE_LINK, Player::closeInventory);
		MenuLinker.registerLink(MAIN_MENU_LINK, GuiStage::backToMainMenu);
	}

	private final MenuState state;

	protected GuiStage(final MenuState state) {
		this.state = state;
	}

	public abstract Inventory build(Player player);

	public MenuState getState() {
		return state;
	}

	protected static ItemStack backLinkStack() {
		return new ItemStackCreator(Material.BARRIER).setLink(MAIN_MENU_LINK)
				.setName(ChatColor.RED + "Back to main menu").build();
	}

	private static void backToMainMenu(Player player) {
		GuiPlayerState state = Coinvestors.getManager().getStateOf(player);
		state.setStage(new MenuGuiState());
		player.openInventory(state.getMenuInventory());
	}

}