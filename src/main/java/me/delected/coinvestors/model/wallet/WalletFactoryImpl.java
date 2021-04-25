package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

public class WalletFactoryImpl implements WalletFactory {

    @Override
    public Wallet creatFor(Crypto crypto) {
        return crypto.getWalletClass();
    }
}
