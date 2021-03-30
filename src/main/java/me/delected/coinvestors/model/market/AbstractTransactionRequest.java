package me.delected.coinvestors.model.market;

import me.delected.coinvestors.model.wallet.Wallet;

public abstract class AbstractTransactionRequest {
	private final double amount;
	private final double prize;
	private final Wallet.WalletUUID target;

	protected AbstractTransactionRequest(final double amount, final double prize, final Wallet.WalletUUID target) {
		this.amount = amount;
		this.prize = prize;
		this.target = target;
	}

	public double getAmount() {
		return amount;
	}

	public double getPrize() {
		return prize;
	}

	public Wallet.WalletUUID getTarget() {
		return target;
	}
}
