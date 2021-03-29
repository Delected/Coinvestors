package me.delected.coinvestors.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AccountManager {
	private final Map<UUID, Account> accountMap = new HashMap<>();

	public void createAccount(UUID userUUID) {
		accountMap.put(userUUID, new Account());
	}

	public Optional<Account> getAccountOf(UUID userUUID) {
		return Optional.ofNullable(accountMap.get(userUUID));
	}

}
