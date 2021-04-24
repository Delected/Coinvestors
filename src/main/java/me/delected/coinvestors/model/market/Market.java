package me.delected.coinvestors.model.market;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.model.currency.ExchangePair;

public class Market {
	private final Set<ExchangePair> exchangePairs;

	public Market(Set<ExchangePair> exchangePairs) {
		this.exchangePairs = exchangePairs;
	}

	public Set<ExchangePair> getExchangePairs() {
		return exchangePairs;
	}

	public Set<Crypto> getCryptos() {
		return exchangePairs.stream()
				.flatMap(exchangePair -> Stream.of(exchangePair.getMajor(), exchangePair.getMinor()))
				.collect(Collectors.toSet());
	}
}
