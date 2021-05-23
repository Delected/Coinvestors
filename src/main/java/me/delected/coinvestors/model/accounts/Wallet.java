package me.delected.coinvestors.model.accounts;

import me.delected.coinvestors.model.currency.Crypto;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Wallet {
	private BigDecimal balance;
	private final Crypto crypto;
	private final String completeAddress;

	protected Wallet(Crypto crypto, UUID id) {
		this(BigDecimal.ZERO, crypto, id);
	}

	//fixme: uuid generation is needed
	protected Wallet(BigDecimal balance, Crypto crypto, UUID id) {
		this.balance = balance;
		this.crypto = crypto;
		this.completeAddress = crypto + "-" + id;
	}

	public void withdraw(BigDecimal amount) {
		balance = balance.subtract(amount);
	}

	public boolean canWithdraw(BigDecimal amount) {
		return balance.compareTo(amount) > -1;
	}

	public void deposit(BigDecimal amount) {
		balance = balance.add(amount);
	}


	public BigDecimal getBalance() {
		return balance;
	}

	public Crypto getCrypto() {
		return crypto;
	}

	public String getCompleteAddress() {
		return completeAddress;
	}
}
