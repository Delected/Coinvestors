package me.delected.coinvestors.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import me.delected.coinvestors.model.currency.Crypto;

/**
 * Represents a full-functional crypto wallet
 */
public class Wallet {

	private static final Map<WalletUUID, Wallet> WALLET_MAP = new HashMap<>();

	private final UUID owner;
	private final WalletUUID walletUUID;
	private final Map<Crypto, Double> balances;

	public Wallet(final UUID owner) {
		this(owner, WalletUUID.random(), new HashMap<>());
	}

	public Wallet(final UUID owner, final WalletUUID walletUUID, final Map<Crypto, Double> balances) {
		this.owner = owner;
		this.walletUUID = walletUUID;
		this.balances = balances;
	}

	/**
	 * @param crypto the cryptocurrency you want to get the balance of
	 * @return the balance, if some currency is present, else 0
	 */
	public double getBalance(Crypto crypto) {
		return balances.getOrDefault(crypto, 0d);
	}

	/**
	 * @return a List of all Pairs with Crypto as key and a amount greater than 0 as value
	 */
	public List<Map.Entry<Crypto, Double>> getHoldBalances() {
		return balances.entrySet().stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toList());
	}

	/**
	 * Decreases the balance of your wallet, if possible
	 *
	 * @param currency the currency you want to withdraw
	 * @param amount   the amount you want to withdraw
	 * @return true if the operation could by successfully done, false if there wasn't enough balance
	 */
	public boolean withdraw(Crypto currency, double amount) {
		Double current = balances.get(currency);
		if (current == null || current < amount)
			return false;
		current -= amount;
		balances.put(currency, current);
		return true;
	}

	/**
	 * Increases the balance of your wallet
	 *
	 * @param currency the currency you want to deposit
	 * @param amount   the amount you want to deposit
	 */
	public void deposit(Crypto currency, double amount) {
		balances.merge(currency, amount, Double::sum);
	}

	public UUID getOwner() {
		return owner;
	}

	public WalletUUID getWalletUUID() {
		return walletUUID;
	}

	/**
	 * allows you to reach a wallet by it's UUID
	 *
	 * @param uuid the WalletUUID you are looking for
	 * @return A Optional containing the Wallet if there was one matching the UUID
	 */
	public static Optional<Wallet> getWalletByUUID(WalletUUID uuid) {
		return Optional.ofNullable(WALLET_MAP.get(uuid));
	}

	//TODO: complete this class and implement the methods

	public static class WalletUUID {

		private final byte[] bytes = new byte[35 - 26];

		public static WalletUUID random() {
			WalletUUID temp = createRandomUUID();
			while (WALLET_MAP.containsKey(temp)) {
				temp = createRandomUUID();
			}
			return temp;
		}

		private static WalletUUID createRandomUUID() {
			return null;
		}

		public static WalletUUID of(String source) {
			return null;
		}

	}
}
