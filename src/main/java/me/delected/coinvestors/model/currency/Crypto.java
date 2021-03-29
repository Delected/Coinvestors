package me.delected.coinvestors.model.currency;

import me.delected.coinvestors.util.Sets;

import java.util.Set;

// not all of these are 100% correct, feel free to change if you see errors in prefixes, lengths, etc
public enum Crypto {
    BTC("Bitcoin", Sets.newSet("1", "3", "bc1"), 26, 35),
    ETH("Ethereum", Sets.newSet("0x"), 42),
    BNB("Binance Coin", Sets.newSet("bnb1"), 42),
    ADA("Cardano", Sets.newSet("addr1", "DDzFFz", "Ae2td"), 104),
    DOT("Polkadot", Sets.newSet("1"), 47, 48),
    XRP("Ripple", Sets.newSet("r"), 25, 35),
    LTC("Litecoin", Sets.newSet("L", "3", "M"), 26, 33),
    BCH("Bitcoin Cash", Sets.newSet("1", "3", "q", "Q", "p", "P", "bitcoincash:", "BITCOINCASH:"), 34, 41);
//    XLM("Stellar"),
//    WBTC("Wrapped Bitcoin"),
//    DOGE("Dogecoin"),
//    HEX("HEX"),
//    XMR("Monero"),
//    EOS("EOS"),
//    USDT("Tether"),
//    UNI("Uniswap"),
//    THETHA("THETA"),
//    LINK("ChainLink"),
//    USDC("USD Coin"),
//    ZRX("0x"),
//    BUSD("Binance USD"),
//    TRX("TRON"),
//    FIL("Filecoin"),
//    BSV("Bitcoin SV"),
//    VET("VeChain"),
//    ETC("Ethereum Classic"),
//    DASH("Dash"),
//    ZEC("Zcash");


    private String fullName;
    private Set<String> prefixes;
    private int minLength;
    private int maxLength;
    private int length;

    Crypto(String fullName, Set<String> prefixes, int minLength, int maxLength) {
        this.fullName = fullName;
        this.prefixes = prefixes;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    // for keys with the same min and max length
    Crypto(String fullName, Set<String> prefixes, int length) {
        this.fullName = fullName;
        this.prefixes = prefixes;
        this.length = length;
    }

    public String getFullName() { return fullName; }
    public Set<String> getPrefixes() { return prefixes; }
    public int getMinLength() { return minLength; }
    public int getMaxLength() { return maxLength; }
    public int getLength() { return length; }
}
