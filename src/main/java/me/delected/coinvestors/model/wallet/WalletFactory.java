package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

public interface WalletFactory {
	String[] createKeyPairFromCrypto(Crypto crypto);
	WalletAddress createAddressFromCrypto(Crypto crypto);
}
