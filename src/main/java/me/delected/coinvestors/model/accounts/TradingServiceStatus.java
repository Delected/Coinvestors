package me.delected.coinvestors.model.accounts;

import java.math.BigDecimal;

public enum TradingServiceStatus {
    STANDARD_TRADER(),
    ADVANCED_TRADER(),
    PREMIUM_TRADER(),
    MARKET_MAKER();

    BigDecimal fee;

    // temp until we decide fees
    TradingServiceStatus() {

    }
    TradingServiceStatus(BigDecimal fee) {
        this.fee = fee;
    }
}
