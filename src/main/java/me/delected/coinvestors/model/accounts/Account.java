package me.delected.coinvestors.model.accounts;

import me.delected.coinvestors.model.currency.Crypto;

import java.util.*;

public class Account {

	public final Map<Crypto, List<Wallet>> wallets = new HashMap<>();
	public TradingServiceStatus serviceStatus;

	public void addWallet(Wallet wallet) {
		Crypto crypto = wallet.getCrypto();
		wallets.computeIfAbsent(wallet.getCrypto(), c -> new ArrayList<>());
		wallets.get(crypto).add(wallet);
	}

	public Map<Crypto, List<Wallet>> getWallets() {
		return wallets;
	}

	public List<Wallet> getWallets(Crypto crypto) {
		return wallets.get(crypto);
	}
}
