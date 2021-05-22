package me.delected.coinvestors.controller;

import me.delected.coinvestors.model.AccountManager;

public class CryptoManager {
	private final AccountManager accountManager = new AccountManager();
	private final CryptoRegulator regulator = new CryptoRegulator();

	public AccountManager getAccountManager() {
		return accountManager;
	}
}
