package me.delected.coinvestors.model.wallet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import me.delected.coinvestors.exceptions.InvalidWalletAddressException;
import me.delected.coinvestors.exceptions.WalletUUIDAlreadyInUseException;
import me.delected.coinvestors.model.currency.Crypto;

/**
 * Represents a full-functional crypto wallet
 */
public class Wallet {
	private static final Map<WalletUUID, Wallet> PUBLIC_MAP = new HashMap<>();
	private static final Map<WalletUUID, Wallet> PRIVATE_MAP = new HashMap<>();

	private final WalletUUID publicKey;
	private final WalletUUID privateKey;
	private double balance;
	private final Crypto crypto;

	public Wallet(final Crypto crypto) {
		this(WalletUUID.createRandomUUID(crypto), WalletUUID.createRandomUUID(crypto), 0, crypto);
	}

	public Wallet(final WalletUUID publicKey,
				  final WalletUUID privateKey, double balance, final Crypto crypto) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.balance = balance;
		this.crypto = crypto;
	}

	/**
	 * @return the balance, if some currency is present, else 0
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Decreases the balance of your wallet, if possible
	 *
	 * @param amount the amount you want to withdraw
	 * @return true if the operation could by successfully done, false if there wasn't enough balance
	 */
	public boolean withdraw(double amount) {
		if (balance < amount)
			return false;
		balance -= amount;
		return true;
	}

	/**
	 * Increases the balance of your wallet
	 *
	 * @param amount the amount you want to deposit
	 */
	public void deposit(double amount) {
		balance += amount;
	}

	public WalletUUID getPublicKey() {
		return publicKey;
	}

	public WalletUUID getPrivateKey() {
		return privateKey;
	}

	public Crypto getCrypto() {
		return crypto;
	}

	/**
	 * allows you to reach a wallet by it's public UUID
	 *
	 * @param uuid the WalletUUID you are looking for
	 * @return A Optional containing the Wallet if there was one matching the UUID
	 */
	public static Optional<Wallet> getWalletByPublicUUID(WalletUUID uuid) {
		return Optional.ofNullable(PUBLIC_MAP.get(uuid));
	}

	/**
	 * allows you to reach a wallet by it's private UUID
	 *
	 * @param uuid the WalletUUID you are looking for
	 * @return A Optional containing the Wallet if there was one matching the UUID
	 */
	public static Optional<Wallet> getWalletByPrivateUUID(WalletUUID uuid) {
		return Optional.ofNullable(PRIVATE_MAP.get(uuid));
	}

	public static class WalletUUID {
		public WalletUUID(byte[] bytes) {
			this.bytes = bytes;
		}

		private static final Set<WalletUUID> IN_USE = new HashSet<>();
		private static final char[] ALPHABET = createAlphabet();
		private final byte[] bytes;

		private WalletUUID(int length) {
			bytes = new byte[length];
		}

		private static synchronized WalletUUID random(Crypto crypto) {
			WalletUUID temp = createRandomUUID(crypto);
			while (IN_USE.contains(temp)) {
				temp = createRandomUUID(crypto);
			}
			register(temp);
			return temp;
		}

		private static WalletUUID createRandomUUID(Crypto crypto) {
			int size = crypto.getMinLength() + (int) ((crypto.getMaxLength() - crypto.getMinLength()) * Math.random());
			WalletUUID result = new WalletUUID(size);
			byte[] uuid = result.bytes;
			String pre = new ArrayList<>(crypto.getPrefixes()).get((int) (Math.random() * crypto.getPrefixes().size()));
			char[] prefix = pre.toCharArray();
			for (int i = 0; i < prefix.length; i++)
				uuid[i] = (byte) prefix[i];
			for (int i = prefix.length; i < uuid.length; i++)
				uuid[i] = getRandomCharacter();
			return result;
		}

		private static void register(WalletUUID uuid) {
			IN_USE.add(uuid);
		}

		/**
		 * Builds a WalletAddress out of the given String
		 *
		 * @param source the wallet-address String
		 * @return a WalletUUID which represents this String
		 * @throws InvalidWalletAddressException   if the given String does not match the criteria for a valid address
		 * @throws WalletUUIDAlreadyInUseException if the given WalletAddress is already assigned to a wallet
		 */
		public static synchronized WalletUUID of(String source) {
			if (!source.matches("([A-Z]|[a-z]|\\d)*"))
				throw new InvalidWalletAddressException();
			WalletUUID result = new WalletUUID(source.length());
			char[] chars = source.toCharArray();
			for (int i = 0; i < chars.length; i++)
				result.bytes[i] = (byte) chars[i];
			if (IN_USE.contains(result))
				throw new WalletUUIDAlreadyInUseException();
			register(result);
			return result;
		}

		public String getStringRepresentation() {
			System.out.println(bytes.length);
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes)
				sb.append((char) b);
			return sb.toString();
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			final WalletUUID that = (WalletUUID) o;
			return Arrays.equals(bytes, that.bytes);
		}

		@Override
		public int hashCode() {
			return Arrays.hashCode(bytes);
		}

		private static char[] createAlphabet() {
			char[] result = new char[62];
			for (int i = 0; i < 26; i++)
				result[i] = (char) ('a' + i);
			for (int i = 0; i < 26; i++)
				result[i + 26] = (char) ('A' + i);
			for (int i = 0; i < 10; i++)
				result[i + 52] = (char) ('0' + i);
			return result;
		}

		private static byte getRandomCharacter() {
			return (byte) ALPHABET[(int) (Math.random() * ALPHABET.length)];
		}

		@Override
		public String toString() {
			return getStringRepresentation();
		}
	}

}
