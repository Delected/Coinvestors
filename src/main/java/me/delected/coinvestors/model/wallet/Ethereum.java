package me.delected.coinvestors.model.wallet;

public class Ethereum implements Wallet {

    @Override
    public String[] generateKeyPair() {
        return new String[0];
    }

    @Override
    public WalletAddress generateAddress() {
        return null;
    }
}
