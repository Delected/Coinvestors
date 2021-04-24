package me.delected.coinvestors.model.market;

import me.delected.coinvestors.model.currency.ExchangePair;
import me.delected.coinvestors.model.wallet.Wallet;

public class Ask extends MarketTransactionRequest implements Comparable<Ask> {

	public Ask(final ExchangePair pair, final double amount, final double prize, final Wallet source, final Wallet target) {
		super(pair, amount, prize, source, target);
	}

	@Override
	public void cancel() {
		unList();
		getSource().deposit(getTotalCost());
	}

	@Override
	public void fulfill() {
		unList();
		getTarget().deposit(getAmount());
	}

	@Override
	public void unList() {
		getPair().getAsks().remove(this);
	}

	@Override
	public int compareTo(final Ask o) {
		return -Double.compare(this.getPrize(), o.getPrize());
	}
}
