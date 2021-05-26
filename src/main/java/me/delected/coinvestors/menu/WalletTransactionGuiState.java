package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.accounts.Wallet;

public class WalletTransactionGuiState extends GuiStage implements TransactionGui {

	private Wallet source;
	private BigDecimal amount;
	private Wallet target;

	public WalletTransactionGuiState() {
		super(MenuState.TRANSACTION);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(player, 27, "Transaction Menu");
		result.setItem(11, sourceStack(source));
		result.setItem(13, amountStack(amount));
		result.setItem(15, targetStack(target));
		result.setItem(25, confirmStack());
		result.setItem(26, backLinkStack());
		return result;
	}

	@Override
	public List<String> invalids() {
		List<String> invalids = new ArrayList<>();
		if (source == null) {
			invalids.add("source wallet");
		}
		if (amount == null) {
			invalids.add("transaction amount");
		}
		if (target == null) {
			invalids.add("destination wallet");
		}
		return TransactionGui.makeLoreWhite(invalids);
	}

	@Override
	public Consumer<Player> confirmAction() {
		return p -> Coinvestors.accountService().sendCurrency(source, amount, target);
	}

	@Override
	public boolean isValid() {
		return amount != null && source != null && target != null;
	}

	//TODO: When wallet access logic is implemented, retrieve the wallets here

	@Override
	public void retrieveSource(final String raw) {
		setSource(Coinvestors.accountService().getWalletByKey(raw).orElse(null));
	}

	@Override
	public void retrieveTarget(String raw) {
		setTarget(Coinvestors.accountService().getWalletByKey(raw).orElse(null));
	}

	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	private void setSource(Wallet source) {
		this.source = source;
	}

	private void setTarget(final Wallet target) {
		this.target = target;
	}


}
