package me.delected.coinvestors.model.accounts;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class WalletDictionary {
    private final Map<String, Wallet> wallets = new HashMap<>();

    public Optional<Wallet> getWalletByKey(String s) {
        return Optional.ofNullable(wallets.get(s));
    }
}
