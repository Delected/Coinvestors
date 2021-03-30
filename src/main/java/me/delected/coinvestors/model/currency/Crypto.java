package me.delected.coinvestors.model.currency;

/*
	not all of these are 100% correct, feel free to change if you see errors in prefixes, lengths, etc.
	? = I can't find any info
*/


import com.mifmif.common.regex.Generex;

public enum Crypto {
	BTC("Bitcoin", "(bc1|[13])[a-zA-HJ-NP-Z0-9]{25,39}", "[5KL][1-9A-HJ-NP-Za-km-z]{50,51}"),
	ETH("Ethereum", "0x[a-fA-F0-9]{40}", "?"),
	BNB("Binance Coin", "?", "?"), //	public: pref"bnb1", len42
	ADA("Cardano", "brb", "brb");




//	ADA("Cardano", Sets.newSet("addr1", "DDzFFz", "Ae2td"), 104),
//	DOT("Polkadot", Sets.newSet("1"), 47, 48),
//	XRP("Ripple", Sets.newSet("r"), 25, 35),
//	LTC("Litecoin", Sets.newSet("L", "3", "M"), 26, 33),
//	BCH("Bitcoin Cash", Sets.newSet("1", "3", "q", "Q", "p", "P", "bitcoincash:", "BITCOINCASH:"), 34, 41),
//	XLM("Stellar", Sets.newSet("G"), 56), // address is in all caps
//	WBTC("Wrapped Bitcoin", Sets.newSet("0x"), 42), // same as ETH, WBTC is basically BTC used with ETH tokens
//	DOGE("Dogecoin", Sets.newSet("D"), 33, 35), // D, followed by any capital letter or number. the rest is random
//	HEX("HEX", Sets.newSet("0x"), 42), // same as ETH, HEX uses ETH tokens
//	XMR("Monero", Sets.newSet("4"), 93), // 4[0-9AB][123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz]{93}
//	EOS("EOS", 12), // IMPORTANT: (account name, probably use player username) 12 long - a-z (lowercase), 1-5 and . (period)
//	USDT("Tether", Sets.newSet("0x"), 42), // same as ETH
//	UNI("Uniswap", Sets.newSet("0x"), 42), // same as ETH
//	THETHA("THETA", Sets.newSet("0x"), 42), // same as ETH
//	LINK("ChainLink"),
//	USDC("USD Coin"),
//	ZRX("0x"),
//	BUSD("Binance USD"),
//	TRX("TRON"),
//	FIL("Filecoin"),
//	BSV("Bitcoin SV"),
//	VET("VeChain"),
//	ETC("Ethereum Classic"),
//	DASH("Dash"),
//	ZEC("Zcash");


	private String fullName;
	private Generex publicKey;
	private Generex privateKey;


	Crypto(String fullName, String publicKey, String privateKey) {
		this.fullName = fullName;
		this.publicKey = new Generex(publicKey);
		this.privateKey = new Generex(privateKey);
	}

	public String getFullName() {
		return fullName;
	}

	public String generatePublicKey() {
		return publicKey.random();
	}

	public String generatePrivateKey() {
		return privateKey.random();
	}


	public boolean isValidPublicKey(String s) { return s.matches(publicKey.toString()); }

	public boolean isValidPrivateKey(String s) { return s.matches(privateKey.toString()); }
}
