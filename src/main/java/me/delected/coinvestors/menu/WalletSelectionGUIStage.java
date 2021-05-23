package me.delected.coinvestors.menu;

import java.util.List;
import java.util.function.Consumer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.model.wallet.Wallet;

public class WalletSelectionGUIStage extends SelectionGui<Wallet> {

	private static final String TITLE = ChatColor.GREEN + "Select your wallet!";

	protected WalletSelectionGUIStage(Consumer<Wallet> onSelect) {
		super(MenuState.WALLET_SELECT, 36, TITLE, onSelect);
	}

	@Override
	protected List<ItemStack> getRawItems() {
		return null;
	}

	@Override
	protected Wallet retrieveT(final Player player, final InventoryClickEvent e) {
		return null;
	}
}
