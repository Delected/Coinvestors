package me.delected.coinvestors.model.wallet.credentialgenerator;

import java.math.BigInteger;

public interface WalletCredentialGenerator {
    BigInteger generatePublicKey();

    BigInteger generatePrivateKey();

    BigInteger generateAddress();
}
