package me.delected.coinvestors.menu.newmnu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.menu.MenuState;

public class DepositGui extends GuiStage {

	private static final String CRYPTO_SELECT_LINK = "DEPOSIT_CRYPTO_INPUT";
	private static final String AMOUNT_SELECT_LINK = "DEPOSIT_AMOUNT_INPUT";
	private static final String TITLE = ChatColor.GREEN + "Buy Crypto";

	static {
		MenuLinker.registerLink(CRYPTO_SELECT_LINK, DepositGui::openCryptoSelect);
		MenuLinker.registerLink(AMOUNT_SELECT_LINK, DepositGui::openAmountSelect);
	}

	private final AmountInputGui amountInputGui = new AmountInputGui(this);
	private final CryptoInputGui cryptoInputGui = new CryptoInputGui(this);


	public DepositGui() {
		super(MenuState.DEPOSIT);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 27, TITLE);
		result.setItem(12, cryptoInputGui.getInfoStack(CRYPTO_SELECT_LINK));
		result.setItem(14, amountInputGui.getInfoStack(AMOUNT_SELECT_LINK));
		return result;
	}

	private static void openCryptoSelect(Player player) {
		getGui(player).cryptoInputGui.open(player);
	}

	private static void openAmountSelect(Player player) {
		getGui(player).amountInputGui.open(player);
	}

	private static DepositGui getGui(Player player) {
		return ((DepositGui) Coinvestors.guiManager().getStateOf(player).getActualStage());
	}

}
