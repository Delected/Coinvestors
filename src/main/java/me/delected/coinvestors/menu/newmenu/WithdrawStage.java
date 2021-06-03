package me.delected.coinvestors.menu.newmenu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;

public class WithdrawStage extends ReturningGuiStage implements Confirmable {

	private static final String TITLE = ChatColor.GOLD + "Withdraw money";
	private static final int SIZE = 27;

	private static final String AMOUNT_INPUT_LINK = "WITHDRAW_AMOUNT_INPUT";
	private static final String WALLET_INPUT_LINK = "WITHDRAW_WALLET_INPUT";

	private final WalletInputGui walletInputGui = new WalletInputGui(this);
	private final AmountInputGui amountInputGui = new AmountInputGui(this);

	protected WithdrawStage(GuiStage previous) {
		super(MenuState.WITHDRAW, previous);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, SIZE, TITLE);
		result.setItem(12, walletInputGui.getInfoStack(WALLET_INPUT_LINK));
		result.setItem(14, amountInputGui.getInfoStack(AMOUNT_INPUT_LINK));
		result.setItem(25, confirmStack(CONFIRM_LINK));
		result.setItem(26, returnStack(ChatColor.RED + "Abort the withdrawal"));
		return result;
	}


	@Override
	public List<String> invalids() {
		List<String> invalids = new ArrayList<>();
		if (walletInputGui.getData() == null) {
			invalids.add("source wallet");
		}
		if (amountInputGui.getData() == null) {
			invalids.add("selling amount");
		}
		return invalids;
	}


	//todo
	@Override
	public Consumer<Player> confirmAction() {
		return p -> {
			Wallet wallet = walletInputGui.getData();
			BigDecimal amount = amountInputGui.getData();
			Coinvestors.accountService().sellCryptoIfPossible(p, wallet, amount);
			turnBack(p);
		};
	}

	@Override
	public boolean isValid() {
		return walletInputGui.getData() != null && amountInputGui.getData() != null;
	}

}
