package me.delected.coinvestors.model.wallet;

import me.delected.coinvestors.model.currency.Crypto;

public interface WalletFactory {
	Wallet createFor(Crypto crypto);
}
