package me.delected.coinvestors.model.market;

import java.math.BigDecimal;

import me.delected.coinvestors.model.currency.ExchangePair;
import me.delected.coinvestors.model.accounts.Wallet;

public abstract class MarketTransactionRequest {
	private final ExchangePair pair;
	private final BigDecimal amount;
	private final double prize;
	private final Wallet source;
	private final Wallet target;

	protected MarketTransactionRequest(final ExchangePair pair, final BigDecimal amount, final double prize, final Wallet source, final Wallet target) {
		this.pair = pair;
		this.amount = amount;
		this.prize = prize;
		this.source = source;
		this.target = target;
	}

	public BigDecimal getTotalCost() {
		return amount.multiply(BigDecimal.valueOf(prize));
	}

	public BigDecimal getAmount() {
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
