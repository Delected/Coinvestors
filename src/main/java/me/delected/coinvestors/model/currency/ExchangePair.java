package me.delected.coinvestors.model.currency;

import java.math.BigDecimal;
import java.util.TreeSet;

import me.delected.coinvestors.model.market.Ask;
import me.delected.coinvestors.model.market.Bid;
import me.delected.coinvestors.model.wallet.Wallet;

public class ExchangePair {
	private final Crypto major;
	private final Crypto minor;
	private final TreeSet<Bid> bids = new TreeSet<>();
	private final TreeSet<Ask> asks = new TreeSet<>();

	public ExchangePair(final Crypto major, final Crypto minor) {
		this.major = major;
		this.minor = minor;
	}

	public Crypto getMajor() {
		return major;
	}

	public Crypto getMinor() {
		return minor;
	}

	public TreeSet<Bid> getBids() {
		return bids;
	}

	public TreeSet<Ask> getAsks() {
		return asks;
	}

	public boolean makeAsk(final BigDecimal amount, final double prize, final Wallet source, final Wallet target) {
		Ask ask = new Ask(this, amount, prize, source, target);
		if (!target.canWithdraw(ask.getTotalCost()))
			return false;
		asks.add(ask);
		return true;
	}

	public boolean makeBid(final BigDecimal amount, final double prize, final Wallet source, final Wallet target) {
		if (!target.canWithdraw(amount))
			return false;
		Bid bid = new Bid(this, amount, prize, source, target);
		bids.add(bid);
		return true;
	}

	public synchronized boolean buy(Bid bid, Wallet source, Wallet target) {
		if (bids.contains(bid))
			if (source.canWithdraw(bid.getTotalCost())) {
				bid.fulfill();
				target.deposit(bid.getAmount());
				return true;
			}
		return false;
	}

	public synchronized boolean sell(Ask ask, Wallet source, Wallet target) {
		if (asks.contains(ask))
			if (source.canWithdraw(ask.getAmount())) {
				ask.fulfill();
				target.deposit(ask.getTotalCost());
				return true;
			}
		return false;
	}

}
