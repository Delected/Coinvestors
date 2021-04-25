package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.wallet.credentialgenerator.WalletCredentialGenerator;

import java.math.BigDecimal;

public interface Wallet {
	WalletCredentialGenerator getCredentialGenerator();

	boolean withdraw(BigDecimal amount);

	void deposit(BigDecimal amount);

	BigDecimal getBalance();

	void setBalance(BigDecimal newAmount);

	WalletType getWalletType();
}
