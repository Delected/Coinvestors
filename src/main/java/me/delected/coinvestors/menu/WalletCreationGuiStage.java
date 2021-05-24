package me.delected.coinvestors.menu;

import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

//TODO: redirect to the Wallet overview instead to the main menu when wallet overview exists
public class WalletCreationGuiStage extends GuiStage {

	private static final String CRYPTO_SELECTION_LINK = "WALLET_CREATE_CRYPTO_SELECTION_LINK";
	private static final String CONFIRMATION_LINK = "WALLET_CREATION_CONFIRMATION_LINK";
	private static final String TITLE = ChatColor.BLUE + "Create a new Wallet";

	static {
		MenuLinker.registerLink(CRYPTO_SELECTION_LINK, WalletCreationGuiStage::openCryptoSelection);
		MenuLinker.registerLink(CONFIRMATION_LINK, WalletCreationGuiStage::onCreationConfirm);
	}

	private Crypto crypto;

	protected WalletCreationGuiStage() {
		super(MenuState.WALLET_CREATE);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(null, 27, TITLE);
		result.setItem(13, crypto());
		result.setItem(25, confirmStack());
		result.setItem(26, backLinkStack());
		return result;
	}

	private ItemStack crypto() {
		return crypto == null ? cryptoPrompt() : cryptoInfo();
	}

	private ItemStack cryptoInfo() {
		return new ItemStackCreator(Material.SUNFLOWER).setName("Crypto")
				.setLore(Collections.singletonList(crypto.getFullName())).setLink(CRYPTO_SELECTION_LINK).build();
	}

	private static ItemStack cryptoPrompt() {
		return new ItemStackCreator(Material.SUNFLOWER).setName(ChatColor.GREEN + "Select Crypto")
				.setLink(CRYPTO_SELECTION_LINK).build();
	}

	private ItemStack confirmStack() {
		return crypto == null ? confirmUnavailable() : confirm();
	}

	private ItemStack confirmUnavailable() {
		String name = ChatColor.RED + "You need to select a currency before confirming the creation";
		return new ItemStackCreator(Material.LIGHT_GRAY_WOOL).setName(name).setUnmodifiable().build();
	}

	private ItemStack confirm() {
		String name = ChatColor.GREEN + "Confirm the creation of your new " + crypto + " wallet!";
		return new ItemStackCreator(Material.GREEN_WOOL).setName(name).setLink(CONFIRMATION_LINK).build();
	}

	private static void openCryptoSelection(Player player) {
		WalletCreationGuiStage stage = (WalletCreationGuiStage) Coinvestors.guiManager().getStateOf(player).getActualStage();
		redirect(player, new CryptoSelectionGuiStage(c -> stage.setCrypto(c, player)));
	}

	private void setCrypto(Crypto crypto, Player player) {
		this.crypto = crypto;
		GuiPlayerState state = Coinvestors.guiManager().getStateOf(player);
		state.setStage(this);
		player.openInventory(state.getMenuInventory());
	}


	//fixme
	private static void onCreationConfirm(Player player) {
		WalletCreationGuiStage stage = (WalletCreationGuiStage) actualStage(player);
		Coinvestors.accountService().createWallet(player, stage.crypto);
		toMainMenu(player);
	}


}
