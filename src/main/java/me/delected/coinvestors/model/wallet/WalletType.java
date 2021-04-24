package me.delected.coinvestors.model.wallet;

public enum WalletType {
    ShortAddressWallet(0),
    MediumAddressWallet(1),
    LongAddressWallet(2);

    int length;

    WalletType(int length) {
        this.length = length;
    }
}
