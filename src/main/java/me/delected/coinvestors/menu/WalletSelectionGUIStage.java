package me.delected.coinvestors.menu;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.accounts.Account;
import me.delected.coinvestors.model.accounts.Wallet;
import me.delected.coinvestors.util.ItemStackCreator;

public class WalletSelectionGUIStage extends SelectionGui<Wallet> {

	private static final String TITLE = ChatColor.GREEN + "Select your wallet";

	protected final UUID playerUuid;
	private final List<Wallet> wallets;

	protected List<Wallet> generateWallets() {
		Optional<Account> accountOf = Coinvestors.accountService().getAccountOf(playerUuid);
		return accountOf.map(account -> account.wallets.entrySet().stream().flatMap(e -> e.getValue().stream())
				.collect(Collectors.toList())).orElse(Collections.emptyList());
	}

	protected WalletSelectionGUIStage(UUID uuid, Consumer<Wallet> onSelect) {
		this(uuid, onSelect, TITLE);
	}

	protected WalletSelectionGUIStage(UUID uuid, Consumer<Wallet> onSelect, String title) {
		super(MenuState.WALLET_SELECT, 36, title, onSelect);
		this.playerUuid = uuid;
		wallets = generateWallets();
		buildPageInventory();
	}

	@Override
	protected List<ItemStack> getRawItems() {
		return wallets.stream().map(this::createWalletStack).collect(Collectors.toList());
	}

	private ItemStack createWalletStack(Wallet wallet) {
		return new ItemStackCreator(Material.SUNFLOWER).setName(wallet.getCrypto() + " Wallet")
				.setEventLink(SELECT_CONFIRM)
				.setLore(ChatColor.YELLOW + "Balance: " + ChatColor.WHITE + wallet.getBalance(),
						ChatColor.YELLOW + "Wallet-ID: " + ChatColor.WHITE + wallet.getCompleteAddress()).build();
	}

	@Override
	protected Wallet retrieveT(final Player player, final InventoryClickEvent e) {
		return wallets.get(getFirstField() + e.getSlot());
	}

}
