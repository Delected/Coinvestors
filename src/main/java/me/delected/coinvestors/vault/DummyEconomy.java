package me.delected.coinvestors.vault;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;

import net.milkbowl.vault.economy.AbstractEconomy;
import net.milkbowl.vault.economy.EconomyResponse;

public class DummyEconomy extends AbstractEconomy {
	private static final String NAME = "FOO";
	private Map<UUID, Double> accounts;
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
		return null;
	}

	@Override
	public String currencyNamePlural() {
		return "FooBar";
	}

	@Override
	public String currencyNameSingular() {
		return "Foo";
	}

	@Override
	public boolean hasAccount(final String playerName) {
		return Optional.ofNullable(Bukkit.getPlayerExact(playerName))
				.map(p -> accounts.containsKey(p.getUniqueId()))
				.orElse(false);
	}

	@Override
	public boolean hasAccount(final String playerName, final String worldName) {
		return hasAccount(playerName);
	}

	@Override
	public double getBalance(final String playerName) {
		return 0;
	}

	@Override
	public double getBalance(final String playerName, final String world) {
		return 0;
	}

	@Override
	public boolean has(final String playerName, final double amount) {
		return false;
	}

	@Override
	public boolean has(final String playerName, final String worldName, final double amount) {
		return false;
	}

	@Override
	public EconomyResponse withdrawPlayer(final String playerName, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(final String playerName, final String worldName, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(final String playerName, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(final String playerName, final String worldName, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse createBank(final String name, final String player) {
		return null;
	}

	@Override
	public EconomyResponse deleteBank(final String name) {
		return null;
	}

	@Override
	public EconomyResponse bankBalance(final String name) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(final String name, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(final String name, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(final String name, final double amount) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(final String name, final String playerName) {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(final String name, final String playerName) {
		return null;
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public boolean createPlayerAccount(final String playerName) {
		return false;
	}

	@Override
	public boolean createPlayerAccount(final String playerName, final String worldName) {
		return false;
	}
}