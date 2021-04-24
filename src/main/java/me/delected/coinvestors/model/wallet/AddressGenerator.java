package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

public interface AddressGenerator {
    WalletAddress createAddress(Crypto crypto);
}
