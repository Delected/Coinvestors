package me.delected.coinvestors.model.wallet;

import java.math.BigDecimal;

public interface Wallet {

	boolean withdraw(BigDecimal amount);

	void deposit(BigDecimal amount);

	BigDecimal getBalance();

	void setBalance(BigDecimal newAmount);
}
