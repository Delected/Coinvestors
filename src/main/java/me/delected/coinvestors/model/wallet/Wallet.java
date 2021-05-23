package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

import java.math.BigDecimal;

public interface Wallet {

	boolean withdraw(BigDecimal amount);

	void deposit(BigDecimal amount);

	BigDecimal getBalance();

	void setBalance(BigDecimal newAmount);

	Crypto getCrypto();
}
