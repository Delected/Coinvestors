package me.delected.coinvestors.model.accounts;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.accounts.Account;
import me.delected.coinvestors.model.currency.Crypto;

import org.bukkit.entity.Player;

class AccountManager {
	private final Map<UUID, Account> accounts = new HashMap<>();

	public void createAccount(UUID id) {
		accounts.computeIfAbsent(id, uuid -> new Account());
	}

	public boolean hasAccount(Player player) {
		return accounts.containsKey(player.getUniqueId());
	}

	public Optional<Account> getAccountOf(UUID id) {
		return Optional.ofNullable(accounts.get(id));
	}

	public Optional<Account> getAccountOf(Player player) {
		return Optional.ofNullable(accounts.get(player.getUniqueId()));
	}

}
