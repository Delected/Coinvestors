package me.delected.coinvestors.model.currency;

import me.delected.coinvestors.model.wallet.address.AddressGenerator;
import me.delected.coinvestors.util.Sets;

import java.util.Set;

/*
	not all of these are 100% correct, feel free to change if you see errors in prefixes, lengths, etc.
	Also, the features in the comments on the side are only QOL, and can be implemented later (unless explicitly labled IMPORTANT).
	
	TODO:
	- Possibly change constructors to take just a String for fullName and a Generator class
	- Create Generator class, and make child classes with different generation algorithms incl. QOL features
*/

public enum Crypto {
	BTC("Bitcoin", Sets.newSet("1", "3", "bc1"), 26, 35),
	ETH("Ethereum", Sets.newSet("0x"), 42),
	BNB("Binance Coin", Sets.newSet("bnb1"), 42),
	ADA("Cardano", Sets.newSet("addr1", "DDzFFz", "Ae2td"), 104),
	DOT("Polkadot", Sets.newSet("1"), 47, 48),
	XRP("Ripple", Sets.newSet("r"), 25, 35),
	LTC("Litecoin", Sets.newSet("L", "3", "M"), 26, 33),
	BCH("Bitcoin Cash", Sets.newSet("1", "3", "q", "Q", "p", "P", "bitcoincash:", "BITCOINCASH:"), 34, 41),
	XLM("Stellar", Sets.newSet("G"), 56), // address is in all caps
	WBTC("Wrapped Bitcoin", Sets.newSet("0x"), 42), // same as ETH, WBTC is basically BTC used with ETH tokens
	DOGE("Dogecoin", Sets.newSet("D"), 33, 35), // D, followed by any capital letter or number. the rest is random
	HEX("HEX", Sets.newSet("0x"), 42), // same as ETH, HEX uses ETH tokens
	XMR("Monero", Sets.newSet("4"), 93), // 4[0-9AB][123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]{93}
	EOS("EOS", 12), // IMPORTANT: (account name, probably use player username) 12 long - a-z (lowercase), 1-5 and . (period)
	USDT("Tether", Sets.newSet("0x"), 42), // same as ETH
	UNI("Uniswap", Sets.newSet("0x"), 42), // same as ETH
	THETHA("THETA", Sets.newSet("0x"), 42), // same as ETH
	LINK("ChainLink"),
	USDC("USD Coin"),
	ZRX("0x"),
	BUSD("Binance USD"),
	TRX("TRON"),
	FIL("Filecoin"),
	BSV("Bitcoin SV"),
	VET("VeChain"),
	ETC("Ethereum Classic"),
	DASH("Dash"),
	ZEC("Zcash");


	private String fullName;
	private Set<String> prefixes;
	private int minLength;
	private int maxLength;
	private AddressGenerator generator;

	// UPDATED:
	Crypto(String fullName, AddressGenerator generator) {
		this.fullName = fullName;
		this.generator = generator;
	}

	@Deprecated
	Crypto(String todo) {

	}

	@Deprecated
	Crypto(String fullName, Set<String> prefixes, int minLength, int maxLength) {
		this.fullName = fullName;
		this.prefixes = prefixes;
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	// for keys with the same min and max length
	@Deprecated
	Crypto(String fullName, Set<String> prefixes, int length) {
		this.fullName = fullName;
		this.prefixes = prefixes;
		this.minLength = length;
	}

	// for keys that need a different generation method
	@Deprecated
	Crypto(String fullName, int length) {
		this.fullName = fullName;
		this.minLength = this.maxLength = length;
	}

	public String getFullName() {
		return fullName;
	}

	public Set<String> getPrefixes() {
		return prefixes;
	}

	@Deprecated
	public int getMinLength() {
		return minLength;
	}

	@Deprecated
	public int getMaxLength() {
		return maxLength;
	}

	public AddressGenerator getGenerator() {
		return generator;
	}
}
