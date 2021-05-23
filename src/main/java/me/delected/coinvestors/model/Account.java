package me.delected.coinvestors.model;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.model.wallet.Wallet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Account {

	public final Map<Crypto, List<Wallet>> wallets = new HashMap<>();

	public void addWallet(Wallet wallet) {
		Crypto crypto = wallet.getCrypto();
		wallets.computeIfAbsent(wallet.getCrypto(), c -> new ArrayList<>());
		wallets.get(crypto).add(wallet);
	}
}
