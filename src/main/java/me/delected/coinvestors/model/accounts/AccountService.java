package me.delected.coinvestors.model.accounts;

import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import me.delected.coinvestors.exceptions.ContactTheDevsException;
import me.delected.coinvestors.model.currency.Crypto;

public class AccountService {

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
		accountManager.createAccount(player);
	}

	public boolean hasAccount(Player player) {
		return accountManager.hasAccount(player);
	}

	public Optional<Wallet> getWalletByKey(String s) {
		return walletDictionary.getWalletByKey(s);
	}

	//todo: making this really save
	// no need to return bool, just check if the person can make a transaction beforehand
	public void sendCurrency(Wallet sender, BigDecimal amount, Wallet recipient) {

	}

	// same process as sendCurrency, but converts the currency (with exchange rates)
	public void exchangeCurrency(Wallet sender, BigDecimal amount, Wallet recipient) {
		// convert and exchange here
		sendCurrency(sender, amount, recipient);
	}

	public Wallet createWallet(Player player, Crypto crypto) {
		if (!hasAccount(player))
			return null;
		Wallet result = factory.createWallet(crypto, walletDictionary);
		getAccountOf(player).orElseThrow(ContactTheDevsException::new).addWallet(result);
		return result;
	}

	public Wallet createWallet(UUID player, Crypto crypto){
		if(!accountManager.getAccountOf(player).isPresent()){
			return null;
		}
		Wallet result = factory.createWallet(crypto, walletDictionary);
		getAccountOf(player).orElseThrow(ContactTheDevsException::new).addWallet(result);
		return result;
	}

}
