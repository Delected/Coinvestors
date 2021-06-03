package me.delected.coinvestors.menu.newmenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;

//TODO: Decide if necessary and if so make useful
public class WalletDetailStage extends ReturningGuiStage {

	private static final String TITLE = ChatColor.BLUE + "Wallet menu";
	private static final int SIZE = 27;
	private final Inventory inventory;

	protected WalletDetailStage(final Wallet wallet, final GuiStage returnStage) {
		super(MenuState.WALLET_SELECT, returnStage);
		inventory = Bukkit.createInventory(null, SIZE, TITLE);
		inventory.setItem(13, RenderUtils.renderWalletNoAction(wallet));
		inventory.setItem(26, returnStack("Back"));
	}

	@Override
	public Inventory build(final Player player) {
		return inventory;
	}
}
