package me.delected.coinvestors.menu;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.accounts.Wallet;
import me.delected.coinvestors.model.currency.Crypto;

public class WalletSelectionByCryptoGuiStage extends WalletSelectionGUIStage {

	private final Crypto crypto;

	protected WalletSelectionByCryptoGuiStage(final UUID uuid, final Consumer<Wallet> onSelect, final Crypto crypto) {
		super(uuid, onSelect);
		this.crypto = crypto;
	}

	protected WalletSelectionByCryptoGuiStage(final UUID uuid, final Consumer<Wallet> onSelect, final String title, final Crypto crypto) {
		super(uuid, onSelect, title);
		this.crypto = crypto;
	}

	@Override
	protected List<Wallet> generateWallets() {
		return Coinvestors.accountService()
				.getAccountOf(playerUuid)
				.map(a -> a.getWallets(crypto))
				.orElse(Collections.emptyList());
	}
}
