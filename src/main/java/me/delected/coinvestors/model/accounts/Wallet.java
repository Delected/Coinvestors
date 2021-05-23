package me.delected.coinvestors.model.accounts;

import me.delected.coinvestors.model.currency.Crypto;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Wallet {
	private BigDecimal balance;
	private final Crypto crypto;
	private final UUID owner;
	private final String completeAddress;

	public Wallet(Crypto crypto, UUID owner) {
		this(BigDecimal.ZERO, crypto, owner);
	}

	public Wallet(BigDecimal balance, Crypto crypto, UUID owner) {
		this.balance = balance;
		this.crypto = crypto;
		this.owner = owner;
		this.completeAddress = crypto + "-" + owner;
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

	public UUID getOwner() {
		return owner;
	}
}
