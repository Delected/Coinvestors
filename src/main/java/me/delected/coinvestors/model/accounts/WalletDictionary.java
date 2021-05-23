package me.delected.coinvestors.model.accounts;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

class WalletDictionary {
	private final Map<String, Wallet> wallets = new HashMap<>();

	public Optional<Wallet> getWalletByKey(String s) {
		return Optional.ofNullable(wallets.get(s));
	}

	void addWallet(Wallet wallet) {
		wallets.put(wallet.getCompleteAddress(), wallet);
	}

	Set<String> getKeysInUse() {
		return wallets.keySet();
	}
}
