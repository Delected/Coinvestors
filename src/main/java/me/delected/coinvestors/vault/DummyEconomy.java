package me.delected.coinvestors.vault;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class DummyEconomy implements Economy {
	private static final EconomyResponse NOT_IMPLEMENTED =
			new EconomyResponse(0d, 0d, EconomyResponse.ResponseType.NOT_IMPLEMENTED, null);
	private static final String NAME = "FOO";
	private final Map<UUID, Double> accounts = new HashMap<>();
	boolean enabled;


	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public int fractionalDigits() {
		return 2;
	}

	@Override
	public String format(final double amount) {
		return String.valueOf(amount);
	}

	@Override
	public String currencyNamePlural() {
		return NAME + "BAR";
	}

	@Override
	public String currencyNameSingular() {
		return NAME;
	}

	@Deprecated
	public boolean hasAccount(final String playerName) {
		return Optional.ofNullable(Bukkit.getPlayerExact(playerName))
				.map(p -> accounts.containsKey(p.getUniqueId()))
				.orElse(false);
	}

	@Override
	public boolean hasAccount(final OfflinePlayer player) {
		return false;
	}

	@Deprecated
	@Override
	public boolean hasAccount(final String playerName, final String worldName) {
		return false;
	}

	@Override
	public boolean hasAccount(final OfflinePlayer player, final String worldName) {
		return false;
	}

	@Deprecated
	@Override
	public double getBalance(final String playerName) {
		return 0;
	}

	@Override
	public double getBalance(final OfflinePlayer player) {
		return accounts.get(player.getUniqueId());
	}

	@Override
	@Deprecated
	public double getBalance(final String playerName, final String world) {
		return 0;
	}

	@Override
	public double getBalance(final OfflinePlayer player, final String world) {
		return getBalance(player);
	}

	@Override
	@Deprecated
	public boolean has(final String playerName, final double amount) {
		return false;
	}

	@Override
	public boolean has(final OfflinePlayer player, final double amount) {
		return Optional.ofNullable(accounts.get(player.getUniqueId())).map(bal -> bal < amount).orElse(false);
	}

	@Override
	@Deprecated
	public boolean has(final String playerName, final String worldName, final double amount) {
		return false;
	}

	@Override
	public boolean has(final OfflinePlayer player, final String worldName, final double amount) {
		return has(player, amount);
	}

	@Override
	@Deprecated
	public EconomyResponse withdrawPlayer(final String playerName, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse withdrawPlayer(final OfflinePlayer player, final double amount) {
		if (has(player, amount)) {
			return new EconomyResponse(amount,
					Optional.ofNullable(accounts.computeIfPresent(player.getUniqueId(), (id, am) -> am - amount))
							.orElse(0d), EconomyResponse.ResponseType.SUCCESS, null);
		}
		return new EconomyResponse(amount, accounts.getOrDefault(player.getUniqueId(), 0d),
				EconomyResponse.ResponseType.FAILURE, "Not enough balance!");
	}

	@Override
	@Deprecated
	public EconomyResponse withdrawPlayer(final String playerName, final String worldName, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse withdrawPlayer(final OfflinePlayer player, final String worldName, final double amount) {
		return withdrawPlayer(player, amount);
	}

	@Override
	@Deprecated
	public EconomyResponse depositPlayer(final String playerName, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse depositPlayer(final OfflinePlayer player, final double amount) {
		if (hasAccount(player)) {
			return new EconomyResponse(amount,
					Optional.ofNullable(accounts.computeIfPresent(player.getUniqueId(), (u, d) -> amount + d))
							.orElse(0d), EconomyResponse.ResponseType.SUCCESS, null);
		}
		return new EconomyResponse(amount, 0d, EconomyResponse.ResponseType.FAILURE, "No account!");

	}

	@Override
	public EconomyResponse depositPlayer(final String playerName, final String worldName, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse depositPlayer(final OfflinePlayer player, final String worldName, final double amount) {
		return depositPlayer(player, amount);
	}

	@Override
	public EconomyResponse createBank(final String name, final String player) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse createBank(final String name, final OfflinePlayer player) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse deleteBank(final String name) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse bankBalance(final String name) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse bankHas(final String name, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse bankWithdraw(final String name, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse bankDeposit(final String name, final double amount) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse isBankOwner(final String name, final String playerName) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse isBankOwner(final String name, final OfflinePlayer player) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse isBankMember(final String name, final String playerName) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public EconomyResponse isBankMember(final String name, final OfflinePlayer player) {
		return NOT_IMPLEMENTED;
	}

	@Override
	public List<String> getBanks() {
		return Collections.emptyList();
	}


	@Override
	public boolean createPlayerAccount(final String playerName) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(final OfflinePlayer player) {
		return accounts.putIfAbsent(player.getUniqueId(), 0d) == null;
	}

	@Override
	public boolean createPlayerAccount(final String playerName, final String worldName) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(final OfflinePlayer player, final String worldName) {
		return createPlayerAccount(player);
	}

	public void addDebug(OfflinePlayer player) {
		depositPlayer(player, 100d);
	}

}