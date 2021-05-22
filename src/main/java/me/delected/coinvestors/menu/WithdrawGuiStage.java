package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.model.wallet.Wallet;

public class WithdrawGuiStage extends GuiStage implements TransactionGui, ExchangeGui {

	private static final String TITLE = ChatColor.GOLD + "Withdraw money";

	private Wallet source;
	private BigDecimal amount;

	protected WithdrawGuiStage() {
		super(MenuState.WITHDRAW);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 27, TITLE);
		result.setItem(12, sourceStack(source));
		result.setItem(14, amountStack(amount));
		result.setItem(25, confirmStack());
		result.setItem(26, backLinkStack());
		return null;
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
		return TransactionGui.makeLoreWhite(invalids);
	}

	@Override
	public boolean isValid() {
		return source != null && amount != null;
	}

	//TODO: connect when functionality is implemented

	@Override
	public void retrieveSource(final String raw) {

	}

	@Override
	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public void retrieveSourceCrypto(final String raw) {

	}

	@Override
	public void retrieveTargetCrypto(final String raw) {

	}
}