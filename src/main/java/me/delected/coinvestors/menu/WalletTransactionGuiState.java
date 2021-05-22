package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.model.wallet.Wallet;

public class WalletTransactionGuiState extends GuiStage implements TransactionGui {

	private static final String SOURCE_INPUT_LINK = "WALLET_TRANSACTION_SOURCE_INPUT";
	private static final String AMOUNT_INPUT_LINK = "WALLET_TRANSACTION_AMOUNT_INPUT";
	private static final String DESTINATION_INPUT_LINK = "WALLET_TRANSACTION_DESTINATION_INPUT";

	private Wallet source;
	private BigDecimal amount;
	private Wallet target;

	static {
		MenuLinker.registerLink(SOURCE_INPUT_LINK, WalletTransactionGuiState::openSourceInput);
		MenuLinker.registerLink(DESTINATION_INPUT_LINK, WalletTransactionGuiState::openTargetInput);
		MenuLinker.registerLink(AMOUNT_INPUT_LINK, WalletTransactionGuiState::openAmountInput);
	}

	public WalletTransactionGuiState() {
		super(MenuState.TRANSACTION);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(player, 27, "Transaction Menu");
		result.setItem(11, sourceStack(source, SOURCE_INPUT_LINK));
		result.setItem(13, amountStack(amount, AMOUNT_INPUT_LINK));
		result.setItem(15, targetStack(target, DESTINATION_INPUT_LINK));
		result.setItem(25, confirmStack());
		result.setItem(26, backLinkStack());
		return result;
	}

	private static void openSourceInput(Player player) {
		WalletTransactionGuiState actualStage = prepareInputFor(player);
		InputStageManager.openStringInput(player, actualStage::retrieveSource, actualStage::build);
	}

	private static void openAmountInput(Player player) {
		WalletTransactionGuiState actualStage = prepareInputFor(player);
		InputStageManager.openNumberInput(player, actualStage::setAmount, actualStage::build);
	}

	private static void openTargetInput(Player player) {
		WalletTransactionGuiState actualStage = prepareInputFor(player);
		InputStageManager.openStringInput(player, actualStage::retrieveTarget, actualStage::build);
	}

	private static WalletTransactionGuiState prepareInputFor(Player player) {
		Coinvestors.guiManager().setDoingInput(player, true);
		return (WalletTransactionGuiState) Coinvestors.guiManager().getStateOf(player).getActualStage();
	}

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
		return invalids;
	}

	public boolean isValid() {
		return amount != null && source != null && target != null;
	}

	//TODO: When wallet access logic is implemented, retrieve the wallets here

	public void retrieveSource(final String raw) {
		setSource(null);
	}

	private void setSource(Wallet source) {
		this.source = source;
	}

	public void retrieveTarget(String raw) {
		setTarget(null);
	}

	private void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	private void setTarget(final Wallet target) {
		this.target = target;
	}


}
