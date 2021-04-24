package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

public class WalletFactoryImpl implements WalletFactory {

    public String[] createKeyPairFromCrypto(Crypto crypto) {
        return crypto.getWalletClass().generateKeyPair();
    }

    public WalletAddress createAddressFromCrypto(Crypto crypto) {
        return crypto.getWalletClass().generateAddress();
    }
}
