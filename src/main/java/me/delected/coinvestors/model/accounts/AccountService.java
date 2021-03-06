package me.delected.coinvestors.model.accounts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.model.currency.Crypto;
import net.milkbowl.vault.economy.Economy;

public class AccountService {
	public void setEconomy(final Economy economy) {
		this.economy = economy;
	}

	private Economy economy;
	private final AccountManager accountManager = new AccountManager();
	private final WalletDictionary walletDictionary = new WalletDictionary();
	private final WalletFactory factory = new WalletFactory();


	public Optional<Account> getAccountOf(UUID id) {
		return accountManager.getAccountOf(id);
	}

	public Optional<Account> getAccountOf(Player player) {
		return getAccountOf(player.getUniqueId());
	}

	public void createAccount(Player player) {
		createAccount(player.getUniqueId());
	}

	public void createAccount(UUID player) {
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player);
		if (!economy.hasAccount(offlinePlayer)) {
			economy.createPlayerAccount(offlinePlayer);
		}
		accountManager.createAccount(player);
	}

	public boolean hasAccount(Player player) {
		return accountManager.hasAccount(player);
	}

	public Optional<Wallet> getWalletByKey(String s) {
		return walletDictionary.getWalletByKey(s);
	}


	public List<Wallet> getWalletsOfPlayer(Player player) {
		return Coinvestors.accountService()
				.getAccountOf(player)
				.map(a -> a.getWallets()
						.values()
						.stream()
						.flatMap(Collection::stream)
						.collect(Collectors.toList()))
				.orElse(Collections.emptyList());
	}

	public List<Wallet> getWalletsOfPlayer(Player player, Crypto crypto) {
		return Coinvestors.accountService()
				.getAccountOf(player)
				.map(a -> a.getWallets(crypto))
				.orElse(Collections.emptyList());
	}

	//todo: making this really save
	public boolean sendCurrency(Wallet sWallet, BigDecimal sAmount, Wallet tWallet) {
		if (sWallet.withDrawIfPossible(sAmount)) {
			tWallet.deposit(sAmount);
			return true;
		}
		return false;
	}

	// same process as sendCurrency, but converts the currency (with exchange rates)
	public void exchangeCurrency(Wallet sender, BigDecimal amount, Wallet recipient) {
		// convert and exchange here
		sendCurrency(sender, amount, recipient);
	}

	public Wallet createWallet(Player player, Crypto crypto) {
		Wallet result = factory.createWallet(crypto, walletDictionary);
		getAccountOf(player).orElseThrow(ContactTheDevsException::new).addWallet(result);
		return result;
	}

	public Wallet createWallet(UUID player, Crypto crypto) {
		if (!accountManager.getAccountOf(player).isPresent()) {
			return null;
		}
		Wallet result = factory.createWallet(crypto, walletDictionary);
		getAccountOf(player).orElseThrow(ContactTheDevsException::new).addWallet(result);
		return result;
	}

	public void sellCryptoIfPossible(Player player, Wallet wallet, BigDecimal amount) {
		Crypto crypto = wallet.getCrypto();
		String message;
		if (wallet.withDrawIfPossible(amount)) {
			BigDecimal revenue = amount.multiply(crypto.getPrice()).multiply(calcFeeMultiplier(player));
			if (!Coinvestors.economy().depositPlayer(player, revenue.doubleValue()).transactionSuccess()) {
				wallet.deposit(amount);
				message = "Internal error selling" + infoMessage(wallet, crypto, amount);
			} else {
				message = ChatColor.GREEN + "Successfully sold " + infoMessage(wallet, crypto, amount);
			}
		} else {
			message = "You can't sell more crypto than you have!";
		}
		player.sendMessage(message);
	}

	private static String infoMessage(Wallet wallet, Crypto crypto, BigDecimal amount) {
		return amount + " of " + crypto.getFullName()
			   + " from wallet " + wallet.getCompleteAddress();
	}

	public void buyCryptoIfPossible(Player player, Wallet wallet, BigDecimal amount) {
		Crypto crypto = wallet.getCrypto();
		BigDecimal price = calcPrice(crypto, amount);
		if (economy.withdrawPlayer(player, price.doubleValue()).transactionSuccess()) {
			wallet.deposit(amount.multiply(calcFeeMultiplier(player)));
		}

	}

	public boolean exchangeCryptoIfPossible(Player player, Wallet sWallet, BigDecimal sAmount, Wallet tWallet) {
		Crypto sCrypto = sWallet.getCrypto();
		Crypto tCrypto = tWallet.getCrypto();
		String message;
		BigDecimal tetherValue = calcPrice(sCrypto, sAmount);
		BigDecimal exchangedAmount = calculateAmount(tCrypto, tetherValue).multiply(calcFeeMultiplier(player));
		if (sWallet.withDrawIfPossible(sAmount)) {
			tWallet.deposit(exchangedAmount);
			message = ChatColor.GREEN + "Successfully exchanged " + infoMessage(sWallet, sCrypto, sAmount)
					  + " to " + tCrypto + " and stored it in wallet " + tWallet;
		} else {
			message = "You had not enough to withdraw " + infoMessage(sWallet, sCrypto, sAmount);
		}
		player.sendMessage(message);
		return false;
	}

	public TradingServiceStatus getStatus(Player player) {
		return getAccountOf(player).map(Account::getServiceStatus).orElse(TradingServiceStatus.STANDARD_TRADER);
	}

	//fixme
	public BigDecimal calculateAmount(Crypto crypto, BigDecimal vaultC) {
		return vaultC.divide(BigDecimal.TEN, RoundingMode.HALF_DOWN);
	}

	public BigDecimal calcPrice(Crypto crypto, BigDecimal amount) {
		return crypto.getPrice().multiply(amount);
	}

	private BigDecimal calcFeeMultiplier(Player player) {
		return BigDecimal.ONE.subtract(getStatus(player).getFee());
	}

}
