package me.delected.coinvestors.model.wallet;

import java.math.BigDecimal;

//TODO
public class WalletImpl implements Wallet {

	@Override
	public boolean withdraw(BigDecimal amount) {
		if (getBalance().compareTo(amount) < 0) {
			return false;
		}
		setBalance(getBalance().subtract(amount));
		return true;
	}

	@Override
	public void deposit(BigDecimal amount) {
		setBalance(getBalance().add(amount));
	}

	@Override
	public BigDecimal getBalance() {
		return null;
	}

	@Override
	public void setBalance(BigDecimal newAmount) {

	}
}
