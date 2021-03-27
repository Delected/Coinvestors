package me.delected.coinvestors.currency;

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


    private String fullName;

    Crypto(String fullName) { this.fullName = fullName; }

    public String getFullName() { return this.fullName; }
}
