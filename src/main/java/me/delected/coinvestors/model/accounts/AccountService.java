package me.delected.coinvestors.model.accounts;

import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class AccountService {
    private final AccountManager accountManager;
    private final WalletDictionary walletDictionary;
    public AccountService() {
        this.accountManager = new AccountManager();
        this.walletDictionary = new WalletDictionary();
    }
    public Optional<Account> getAccountOf(UUID id) {
        return accountManager.getAccountOf(id);
    }

    public Optional<Account> getAccountOf(Player player) {
        return getAccountOf(player.getUniqueId());
    }

    public void createAccount(Player player) {
        accountManager.createAccount(player.getUniqueId());
    }

    public boolean hasAccount(Player player) {
        return accountManager.hasAccount(player);
    }

    public Optional<Wallet> getWalletByKey(String s) {
        return walletDictionary.getWalletByKey(s);
    }

    // no need to return bool, just check if the person can make a transaction beforehand
    public void sendCurrency(Wallet sender, BigDecimal amount, Wallet recipient) {

    }

    // same process as sendCurrency, but converts the currency (with exchange rates)
    public void exchangeCurrency(Wallet sender, BigDecimal amount, Wallet recipient) {
        // convert and exchange here
        sendCurrency(sender, amount, recipient);
    }
}
