package me.delected.coinvestors.menu;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

public class CryptoSelectionGuiStage extends PagedGui {

	private static final String SELECT_CONFIRM = "CRYPTO_SELECTION_CONFIRM";
	private static final List<ItemStack> STACKS = createInfoStacks();
	private static final String TITLE = ChatColor.DARK_RED + "Select your crypto currency!";

	static {
		MenuLinker.registerEventLink(SELECT_CONFIRM, CryptoSelectionGuiStage::selectCrypto);
	}

	private final Consumer<Crypto> onSelect;

	protected CryptoSelectionGuiStage(final Consumer<Crypto> onSelect) {
		super(MenuState.CRYPTO_SELECT, 54, TITLE);
		this.onSelect = onSelect;
		buildPageInventory();
	}

	private static List<ItemStack> createInfoStacks() {
		return Arrays.stream(Crypto.values()).map(CryptoSelectionGuiStage::cryptoInfo).collect(Collectors.toList());
	}

	private static ItemStack cryptoInfo(Crypto crypto) {
		return new ItemStackCreator(Material.SUNFLOWER).setName(crypto.getFullName())
				.setEventLink(SELECT_CONFIRM).build();
	}

	@Override
	protected List<ItemStack> getRawItems() {
		return STACKS;
	}

	private static void selectCrypto(Player player, InventoryClickEvent e) {
		CryptoSelectionGuiStage guiStage = (CryptoSelectionGuiStage) Coinvestors.guiManager().getStateOf(player).getActualStage();
		Crypto crypto = Crypto.values()[guiStage.getFirstField() + e.getSlot()];
		guiStage.onSelect.accept(crypto);
	}

}
