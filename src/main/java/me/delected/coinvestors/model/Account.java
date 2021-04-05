package me.delected.coinvestors.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import me.delected.coinvestors.model.currency.Crypto;

public class Account {
	private final List<Wallet.WalletUUID> wallets;

	public Map<Wallet.WalletUUID, Wallet> getWallets() {
		return wallets.stream()
				.map(Wallet::getWalletByPrivateUUID)
				.map(wallet -> wallet.orElse(null))
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(Wallet::getPrivateKey, w -> w));
	}

	public Account() {
		this(new ArrayList<>());
	}

	public Account(List<Wallet.WalletUUID> wallets) {
		this.wallets = wallets;
	}

	public void addWallet(Crypto crypto) {
		Wallet newWallet = new Wallet(crypto);
		wallets.add(newWallet.getPrivateKey());
	}

	public Optional<Wallet> getWallet(Wallet.WalletUUID uuid) {
		return Wallet.getWalletByPrivateUUID(uuid);
	}

}
