package me.delected.coinvestors.controller;

import java.util.Optional;
import java.util.UUID;

import me.delected.coinvestors.model.accounts.Account;
import me.delected.coinvestors.model.accounts.AccountService;

public class CryptoManager {
	private final AccountService accountService = new AccountService();
	private final CryptoRegulator regulator = new CryptoRegulator();


	public Optional<Account> getAccountOf(UUID playerUuid) {
		return accountService.getAccountOf(playerUuid);
	}

}
