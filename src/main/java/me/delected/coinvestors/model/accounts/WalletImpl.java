package me.delected.coinvestors.model.accounts;

import java.math.BigDecimal;
import java.util.UUID;

import me.delected.coinvestors.model.currency.Crypto;

class WalletImpl extends Wallet {

	protected WalletImpl(final Crypto crypto, final UUID id) {
		super(crypto, id);
	}

	protected WalletImpl(final BigDecimal balance, final Crypto crypto, final UUID id) {
		super(balance, crypto, id);
	}
}
