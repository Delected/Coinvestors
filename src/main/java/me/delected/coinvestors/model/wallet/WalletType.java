package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.wallet.credentialgenerator.EthereumGenerator;
import me.delected.coinvestors.model.wallet.credentialgenerator.WalletCredentialGenerator;

public enum WalletType {
    ETHEREUM(EthereumGenerator.class);

    Class<? extends WalletCredentialGenerator> generator;

    WalletType(Class<? extends WalletCredentialGenerator> generator) {
        this.generator = generator;
    }

    public Class<? extends WalletCredentialGenerator> getGenerator() {
        return generator;
    }
}
