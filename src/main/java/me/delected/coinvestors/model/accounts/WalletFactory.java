package me.delected.coinvestors.model.accounts;

import java.util.Set;
import java.util.UUID;

import me.delected.coinvestors.model.currency.Crypto;

class WalletFactory {

	public Wallet createWallet(Crypto crypto, WalletDictionary dictionary) {
		Wallet result = new WalletImpl(crypto, generateNewUuid(crypto, dictionary.getKeysInUse()));
		dictionary.addWallet(result);
		return result;
	}

	private static UUID generateNewUuid(Crypto crypto, Set<String> existing) {
		UUID result;
		do {
			result = UUID.randomUUID();
		} while (existing.contains(crypto + "-" + result));
		return result;
	}
}
