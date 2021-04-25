package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

public class WalletFactoryImpl implements WalletFactory {

    public String[] createKeyPairFromCrypto(Crypto crypto) {
        return null; //TODO
    }

    public WalletAddress createAddressFromCrypto(Crypto crypto) {
        return null; //TODO
    }

    @Override
    public Wallet creatFor(Crypto crypto) {
        return null;
    }
}
