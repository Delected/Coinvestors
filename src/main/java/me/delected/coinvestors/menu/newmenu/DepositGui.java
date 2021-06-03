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
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;

public class DepositGui extends ReturningGuiStage implements Confirmable {

	private static final String CRYPTO_SELECT_LINK = "DEPOSIT_CRYPTO_INPUT";
	private static final String AMOUNT_SELECT_LINK = "DEPOSIT_AMOUNT_INPUT";
	private static final String WALLET_SELECT_LINK = "DEPOSIT_WALLET_INPUT";
	private static final String TITLE = ChatColor.GREEN + "Buy Crypto";

	static {
		MenuLinker.registerLink(CRYPTO_SELECT_LINK, DepositGui::openCryptoSelect);
		MenuLinker.registerLink(AMOUNT_SELECT_LINK, DepositGui::openAmountSelect);
		MenuLinker.registerLink(WALLET_SELECT_LINK, DepositGui::openWalletSelect);
	}

	private final AmountInputGui amountInputGui = new AmountInputGui(this);
	private final CryptoInputGui cryptoInputGui = new CryptoInputGui(this);
	private final RestrictedInputGui<Wallet> walletInputGui;


	public DepositGui(GuiStage previous) {
		super(MenuState.DEPOSIT, previous);
		WalletByCryptoInputGui walletByCryptoInputGui = new WalletByCryptoInputGui(this, cryptoInputGui::getData);
		walletInputGui = new RestrictedInputGui<>(this, walletByCryptoInputGui, cryptoInputGui);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 27, TITLE);
		result.setItem(12, cryptoInputGui.getInfoStack(CRYPTO_SELECT_LINK));
		result.setItem(13, walletInputGui.getInfoStack(WALLET_SELECT_LINK));
		result.setItem(14, amountInputGui.getInfoStack(AMOUNT_SELECT_LINK));
		result.setItem(25, confirmStack(CONFIRM_LINK));
		result.setItem(26, backLinkStack(ChatColor.WHITE + "Aborts the transaction"));
		return result;
	}

	private static void openCryptoSelect(Player player) {
		getGui(player).cryptoInputGui.open(player);
	}

	private static void openAmountSelect(Player player) {
		getGui(player).amountInputGui.open(player);
	}

	private static void openWalletSelect(Player player) {
		getGui(player).walletInputGui.open(player);
	}

	private static DepositGui getGui(Player player) {
		return actualStage(player, DepositGui.class);
	}

	@Override
	public boolean isValid() {
		return amountInputGui.getData() != null && cryptoInputGui.getData() != null
			   && walletInputGui.getData() != null;
	}

	@Override
	public List<String> invalids() {
		List<String> result = new ArrayList<>();
		if (amountInputGui.getData() == null) {
			result.add("deposit amount");
		}
		if (cryptoInputGui.getData() == null) {
			result.add("crypto currency");
		}
		if (walletInputGui.getData() == null) {
			result.add("destination wallet");
		}
		return result;
	}

	@Override
	public Consumer<Player> confirmAction() {
		return p -> {
			BigDecimal amount = amountInputGui.getData();
			Wallet wallet = walletInputGui.getData();
			Coinvestors.accountService().buyCryptoIfPossible(p, wallet, amount);
			turnBack(p);
		};

	}

}

