package me.delected.coinvestors.model.market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import me.delected.coinvestors.model.currency.ExchangePair;

public class Market {
	private final Map<ExchangePair, TreeSet<Bid>> bid;
	private final Map<ExchangePair, TreeSet<Ask>> ask;

	public Market(Set<ExchangePair> exchangePairs) {
		bid = new HashMap<>();
		ask = new HashMap<>();
		exchangePairs.forEach(e -> {
			bid.putIfAbsent(e, new TreeSet<>());
			ask.putIfAbsent(e, new TreeSet<>());
		});
	}

	//TODO

	public void makeBid() {

	}

	public void makeAsk() {

	}

	public boolean fulfillAsk(Ask ask) {
		return false;
	}

	public boolean fulfillBid(Bid bid) {
		return false;
	}

	public List<Bid> getBids(ExchangePair exchangePair) {
		return new ArrayList<>(bid.get(exchangePair));
	}

	public List<Ask> getAsks(ExchangePair exchangePair) {
		return new ArrayList<>(ask.get(exchangePair));
	}

}
