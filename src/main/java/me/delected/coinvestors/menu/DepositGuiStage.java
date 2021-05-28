package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.accounts.Wallet;
import me.delected.coinvestors.model.currency.Crypto;

public class DepositGuiStage extends GuiStage implements TransactionGui, ExchangeGui {

	private static final String TITLE = ChatColor.GREEN + "Buy Crypto!";
	private Crypto crypto;
	private Wallet target;
	private BigDecimal amount;

	protected DepositGuiStage() {
		super(MenuState.DEPOSIT);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 27, TITLE);
		result.setItem(25, confirmStack());
		result.setItem(26, closeStack());
		return null;
	}

	@Override
	public boolean isValid() {
		return false;
	}

	@Override
	public List<String> invalids() {
		return null;
	}

	@Override
	public Consumer<Player> confirmAction() {
		return p -> {
			Coinvestors.accountService().buyCryptoIfPossible(p, crypto, target, amount);
			toMainMenu(p);
		};
	}



	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public void setTargetCrypto(final Crypto crypto) {
		this.crypto = crypto;
	}
}
