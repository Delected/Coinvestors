package me.delected.coinvestors.model.currency;

import me.delected.coinvestors.model.accounts.Wallet;

public enum Crypto {
	BTC("Bitcoin"),
	ETH("Ethereum"),
	BNB("Binance Coin"),
	ADA("Cardano"),
	DOT("Polkadot"),
	XRP("Ripple"),
	LTC("Litecoin"),
	BCH("Bitcoin Cash"),
	XLM("Stellar"),
	WBTC("Wrapped Bitcoin"),
	DOGE("Dogecoin"),
	HEX("HEX"),
	XMR("Monero"),
	EOS("EOS"),
	USDT("Tether"),
	UNI("Uniswap"),
	THETHA("THETA"),
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

	String fullName;
	Wallet walletClass;


	Crypto(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @deprecated old way is fine again
	 */
	@Deprecated
	Crypto(String fullName, Wallet walletClass) {
		this.fullName = fullName;
		this.walletClass = walletClass;
	}

	public String getFullName() {
		return fullName;
	}

	@Deprecated
	public Wallet getWalletClass() {
		return walletClass;
	}
}
