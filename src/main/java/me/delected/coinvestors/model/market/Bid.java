package me.delected.coinvestors.model.market;

import java.math.BigDecimal;

import me.delected.coinvestors.model.currency.ExchangePair;
import me.delected.coinvestors.model.accounts.Wallet;

public class Bid extends MarketTransactionRequest implements Comparable<Bid> {

	public Bid(final ExchangePair pair, final BigDecimal amount, final double prize, final Wallet source, final Wallet target) {
		super(pair, amount, prize, source, target);
	}

	@Override
	public void unList() {
		getPair().getBids().remove(this);
	}


	@Override
	public void cancel() {
		unList();
		getSource().deposit(getAmount());
	}

	@Override
	public void fulfill() {
		unList();
		getTarget().deposit(getTotalCost());
	}

	@Override
	public int compareTo(final Bid o) {
		return Double.compare(this.getPrice(), o.getPrice());
	}
}
