package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.wallet.credentialgenerator.WalletCredentialGenerator;

import java.math.BigDecimal;

//TODO
public class WalletImpl implements Wallet {

	@Override
	public WalletCredentialGenerator getCredentialGenerator() {
		return null;
	}

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

	@Override
	public WalletType getWalletType() {
		return null;
	}




}
