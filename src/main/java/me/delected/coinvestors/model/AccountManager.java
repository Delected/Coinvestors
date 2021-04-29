package me.delected.coinvestors.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AccountManager {
	private final Map<UUID, Account> accountMap = new HashMap<>();

	public boolean createAccount(UUID id) {
		if (accountMap.containsKey(id))
			return false;
		accountMap.put(id, new Account(id));
		return true;
	}

	public Optional<Account> getAccountOf(UUID userUUID) {
		return Optional.ofNullable(accountMap.get(userUUID));
	}

}
