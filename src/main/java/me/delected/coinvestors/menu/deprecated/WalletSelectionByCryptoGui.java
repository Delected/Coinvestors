package me.delected.coinvestors.menu.deprecated;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.accounts.Wallet;
import me.delected.coinvestors.model.currency.Crypto;

public class WalletSelectionByCryptoGui extends WalletSelectionGUIStage {

	private final Crypto crypto;

	protected WalletSelectionByCryptoGui(final UUID uuid, final Consumer<Wallet> onSelect, final Crypto crypto) {
		super(uuid, onSelect);
		this.crypto = crypto;
	}

	protected WalletSelectionByCryptoGui(final UUID uuid, final Consumer<Wallet> onSelect, final String title, final Crypto crypto) {
		super(uuid, onSelect, title);
		this.crypto = crypto;
	}

	@Override
	protected List<Wallet> generateWallets() {
		return Coinvestors.accountService().getWalletsOfPlayer(Bukkit.getPlayer(playerUuid), crypto);
	}
}
