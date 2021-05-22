package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.model.wallet.Wallet;

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
		return null;
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
	public void retrieveSourceCrypto(final String raw) {
		sourceCrypto = ExchangeGui.parseCrypto(raw);
	}

	@Override
	public void retrieveTargetCrypto(final String raw) {
		targetCrypto = ExchangeGui.parseCrypto(raw);
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
}