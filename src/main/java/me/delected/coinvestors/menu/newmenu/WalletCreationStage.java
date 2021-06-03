package me.delected.coinvestors.menu.newmenu;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.menu.GuiStage;

//TODO: redirect to the Wallet overview instead to the main menu when wallet overview exists
public class WalletCreationStage extends GuiStage implements Confirmable {

	private static final String CRYPTO_SELECT_LINK = "WALLET_CREATE_CRYPTO_INPUT";
	private static final String TITLE = ChatColor.BLUE + "Create a new wallet";

	static {
		MenuLinker.registerLink(CRYPTO_SELECT_LINK, WalletCreationStage::openCryptoSelect);
	}

	private final CryptoInputGui cryptoInputGui = new CryptoInputGui(this);

	protected WalletCreationStage() {
		super(MenuState.WALLET_CREATE);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 27, TITLE);
		result.setItem(13, cryptoInputGui.getInfoStack(CRYPTO_SELECT_LINK));
		result.setItem(25, confirmStack(CONFIRM_LINK));
		result.setItem(26, backLinkStack(ChatColor.WHITE + "Aborts the wallet creation"));
		return result;
	}

	private static void openCryptoSelect(Player player) {
		getGui(player).cryptoInputGui.open(player);
	}

	private static WalletCreationStage getGui(Player player) {
		return actualStage(player, WalletCreationStage.class);
	}


	//TODO: REDIRECT TO WALLET OVERVIEW ONCE IMPLEMENTED.
	private void onCreationConfirm(Player player) {
		Coinvestors.accountService().createWallet(player, cryptoInputGui.getData());
		toMainMenu(player);
	}


	@Override
	public boolean isValid() {
		return cryptoInputGui.getData() != null;
	}

	@Override
	public List<String> invalids() {
		if (!isValid()) {
			return Collections.singletonList("desired crypto currency");
		}
		return Collections.emptyList();
	}

	@Override
	public Consumer<Player> confirmAction() {
		return this::onCreationConfirm;
	}
}
