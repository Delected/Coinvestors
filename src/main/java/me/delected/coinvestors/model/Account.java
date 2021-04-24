package me.delected.coinvestors.model;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.model.wallet.Wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Account {
	UUID id;

	public Account(UUID id) {
		this.id = id;
	}

	public Map<Crypto, List<Wallet>> wallets = new HashMap<>();

	public void addWallet() {

	}
}
