package me.delected.coinvestors.model.currency;

import com.google.common.collect.Sets;

import java.util.Set;

// not all of these are 100% correct, feel free to change if you see errors in prefixes, lengths, etc
public enum Crypto {
    BTC("Bitcoin", Sets.newHashSet("1", "3", "bc1"), 26, 35),
    ETH("Ethereum", Sets.newHashSet("0x"), 42),
    BNB("Binance Coin", Sets.newHashSet("bnb1"), 42),
    ADA("Cardano", Sets.newHashSet("addr1", "DDzFFz", "Ae2td"), 104),
    DOT("Polkadot", Sets.newHashSet("1"), 47, 48),
    XRP("Ripple", Sets.newHashSet("r"), 25, 35),
    LTC("Litecoin", Sets.newHashSet("L", "3", "M"), 26, 33),
    BCH("Bitcoin Cash", Sets.newHashSet("1", "3", "q", "Q", "p", "P", "bitcoincash:", "BITCOINCASH:"), 34, 41);
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

    Crypto(String fullName, Set<String> prefixes, int minLength, int maxLength) {
        this.fullName = fullName;
        this.prefixes = prefixes;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    // for keys with the same min and max length
    Crypto(String fullName, Set<String> prefixes, int minLength) {
        this.fullName = fullName;
        this.prefixes = prefixes;
        this.minLength = minLength;
    }

    public String getFullName() { return fullName; }
    public Set<String> getPrefixes() { return prefixes; }
    public int getMinLength() { return minLength; }
    public int getMaxLength() { return maxLength; }
}
