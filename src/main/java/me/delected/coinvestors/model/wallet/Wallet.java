package me.delected.coinvestors.model.wallet;

public interface Wallet {
    String[] generateKeyPair();
    WalletAddress generateAddress();
}
