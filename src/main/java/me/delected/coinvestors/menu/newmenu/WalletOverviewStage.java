package me.delected.coinvestors.menu.newmenu;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;
import me.delected.coinvestors.util.Functions;

public class WalletOverviewStage extends SelectionGui<Wallet> {

	private static final String TITLE = ChatColor.BLUE + "Your Wallets";
	private static final int SIZE = 36;

	public WalletOverviewStage(Player player, GuiStage prev) {
		super(SIZE, TITLE, Functions.ignore(),
				() -> Coinvestors.accountService().getWalletsOfPlayer(player), RenderUtils::renderWalletNoAction,
				prev, w -> new WalletDetailStage(w, new WalletOverviewStage(player, prev)));
	}

	@Override
	protected ItemStack abortStack() {
		return returnStack("Back to main menu");
	}
}
