package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.model.accounts.Wallet;

public class ExchangeGuiStage extends GuiStage implements ExchangeGui, TransactionGui {

	private static final String TITLE = ChatColor.YELLOW + "Exchange crypto";

	private Crypto sourceCrypto;
	private Wallet source;
	private BigDecimal amount;
	private Crypto targetCrypto;
	private Wallet target;

	protected ExchangeGuiStage() {
		super(MenuState.EXCHANGE);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 36, TITLE);
		result.setItem(11, sourceCryptoStack(sourceCrypto));
		result.setItem(15, targetCryptoStack(targetCrypto));
		result.setItem(20, sourceStack(source));
		result.setItem(22, amountStack(amount));
		result.setItem(24, targetStack(target));
		result.setItem(34, confirmStack());
		result.setItem(35, backLinkStack());
		return result;
	}

	@Override
	public ItemStack sourceStack(final Wallet source) {
		if (sourceCrypto == null)
			return ExchangeGui.walletInputUnavailable();
		return TransactionGui.super.sourceStack(source);
	}

	@Override
	public ItemStack targetStack(final Wallet target) {
		if (targetCrypto == null)
			return ExchangeGui.walletInputUnavailable();
		return TransactionGui.super.targetStack(target);
	}

	@Override
	public List<String> invalids() {
		List<String> invalids = new ArrayList<>();
		if (sourceCrypto == null) {
			invalids.add("Source crypto");
		}
		if (source == null) {
			invalids.add("Source wallet");
		}
		if (amount == null) {
			invalids.add("transaction amount");
		}
		if (targetCrypto == null) {
			invalids.add("Destination crypto");
		}
		if (target == null) {
			invalids.add("Destination wallet");
		}
		return TransactionGui.makeLoreWhite(invalids);
	}

	@Override
	public boolean isValid() {
		return sourceCrypto != null && targetCrypto != null && amount != null && source != null && target != null;
	}

	@Override
	public void retrieveSource(final String raw) {
		setSource(null);
	}

	@Override
	public void retrieveTarget(String raw) {
		setTarget(null);
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

	@Override
	public void setSourceCrypto(final Crypto crypto) {
		this.sourceCrypto = crypto;
	}

	@Override
	public void setTargetCrypto(final Crypto crypto) {
		this.targetCrypto = crypto;
	}
}
