package me.delected.coinvestors.model.market;

import me.delected.coinvestors.model.currency.ExchangePair;
import me.delected.coinvestors.model.wallet.Wallet;

public abstract class MarketTransactionRequest {
	private final ExchangePair pair;
	private final double amount;
	private final double prize;
	private final Wallet source;
	private final Wallet target;

	protected MarketTransactionRequest(final ExchangePair pair, final double amount, final double prize, final Wallet source, final Wallet target) {
		this.pair = pair;
		this.amount = amount;
		this.prize = prize;
		this.source = source;
		this.target = target;
	}

	public double getTotalCost() {
		return amount * prize;
	}

	public double getAmount() {
		return amount;
	}

	public double getPrize() {
		return prize;
	}

	public Wallet getSource() {
		return source;
	}

	public Wallet getTarget() {
		return target;
	}

	public ExchangePair getPair() {
		return pair;
	}

	public abstract void unList();

	public abstract void cancel();

	public abstract void fulfill();

}