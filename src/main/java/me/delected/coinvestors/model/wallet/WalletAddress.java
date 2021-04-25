package me.delected.coinvestors.model.wallet;

import java.math.BigInteger;

public interface WalletAddress {
    BigInteger publicKey();
    BigInteger privateKey();
}
