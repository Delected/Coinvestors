package me.delected.coinvestors.menu.newmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.model.accounts.Wallet;

public class SendCryptoStage extends ReturningGuiStage implements Confirmable {

	private static final String TITLE = ChatColor.RED + "Send money to other wallet";
	private static final int SIZE = 36;
	private static final String SOURCE_LINK = "SEND_CRYPTO_SOURCE_INPUT";
	private static final String AMOUNT_LINK = "SEND_CRYPTO_AMOUNT_INPUT";
	private static final String TARGET_LINK = "SEND_CRYPTO_TARGET_INPUT";

	static {
		MenuLinker.registerLink(TARGET_LINK, SendCryptoStage::openTargetInput);
		MenuLinker.registerLink(AMOUNT_LINK, SendCryptoStage::openAmountInput);
		MenuLinker.registerLink(SOURCE_LINK, SendCryptoStage::openSourceInput);
	}

	private final WalletByTextInputGui targetWalletInputGui = new WalletByTextInputGui(this);
	private final AmountInputGui amountInputGui = new AmountInputGui(this);
	private final RestrictedInputGui<Wallet> sourceWalletInputGui;

	protected SendCryptoStage(final GuiStage returnStage) {
		super(MenuState.TRANSACTION, returnStage);
		WalletByCryptoInputGui cryptoInputGui = new WalletByCryptoInputGui(this,
				() -> Optional.ofNullable(targetWalletInputGui.getData()).map(Wallet::getCrypto).orElse(null));
		sourceWalletInputGui = new RestrictedInputGui<>(this, cryptoInputGui, targetWalletInputGui);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, SIZE, TITLE);
		result.setItem(11, targetWalletInputGui.getInfoStack(TARGET_LINK));
		result.setItem(13, amountInputGui.getInfoStack(AMOUNT_LINK));
		result.setItem(15, sourceWalletInputGui.getInfoStack(SOURCE_LINK));
		result.setItem(26, returnStack(ChatColor.RED + "Go back",
				ChatColor.WHITE + "Cancels the transaction"));
		return result;
	}


	private static void openSourceInput(Player player) {
		getGui(player).sourceWalletInputGui.open(player);
	}

	private static void openAmountInput(Player player) {
		getGui(player).amountInputGui.open(player);
	}

	private static void openTargetInput(Player player) {
		getGui(player).targetWalletInputGui.open(player);
	}

	private static SendCryptoStage getGui(Player player) {
		return actualStage(player, SendCryptoStage.class);
	}


	@Override
	public boolean isValid() {
		return Confirmable.checkValid(targetWalletInputGui, amountInputGui, sourceWalletInputGui);
	}

	@Override
	public List<String> invalids() {
		List<String> result = new ArrayList<>();
		if (targetWalletInputGui.getData() == null) {
			result.add("transaction destination wallet");
		}
		if (amountInputGui.getData() == null) {
			result.add("transaction amount");
		}
		if (sourceWalletInputGui.getData() == null) {
			result.add("transaction source wallet");
		}
		return result;
	}

	@Override
	public Consumer<Player> confirmAction() {
		return p -> Coinvestors.accountService()
				.sendCurrency(sourceWalletInputGui.getData(), amountInputGui.getData(), targetWalletInputGui.getData());
	}
}
