package me.delected.coinvestors.model.wallet;

public enum WalletType {
    SHORT_ADDRESS_WALLET(0),
    MEDIUM_ADDRESS_WALLET(1),
    LONG_ADDRESS_WALLET(2);

    int length;

    WalletType(int length) {
        this.length = length;
    }
}
