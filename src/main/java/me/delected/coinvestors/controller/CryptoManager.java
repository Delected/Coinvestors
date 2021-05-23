package me.delected.coinvestors.controller;

import java.util.Optional;
import java.util.UUID;

import me.delected.coinvestors.model.accounts.Account;
import me.delected.coinvestors.model.accounts.AccountManager;

public class CryptoManager {
	private final AccountManager accountManager = new AccountManager();
	private final CryptoRegulator regulator = new CryptoRegulator();


	public Optional<Account> getAccountOf(UUID playerUuid) {
		return accountManager.getAccountOf(playerUuid);
	}

}
