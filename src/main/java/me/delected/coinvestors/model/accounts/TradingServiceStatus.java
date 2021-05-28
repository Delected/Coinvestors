package me.delected.coinvestors.model.accounts;

import java.math.BigDecimal;

public enum TradingServiceStatus {
	STANDARD_TRADER(),
	ADVANCED_TRADER(),
	PREMIUM_TRADER(),
	MARKET_MAKER();

	private BigDecimal fee;

	// temp until we decide fees
	TradingServiceStatus() {
		this(BigDecimal.ZERO);
	}

	TradingServiceStatus(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getFee() {
		return fee;
	}
}
