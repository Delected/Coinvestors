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

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

public class CryptoSelectionGuiStage extends SelectionGui<Crypto> {

	private static final List<ItemStack> STACKS = createInfoStacks();
	private static final String TITLE = ChatColor.DARK_RED + "Select your crypto currency!";


	protected CryptoSelectionGuiStage(final Consumer<Crypto> onSelect) {
		super(MenuState.CRYPTO_SELECT, 54, TITLE, onSelect);
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


	@Override
	protected Crypto retrieveT(final Player player, final InventoryClickEvent e) {
		return Crypto.values()[getFirstField() + e.getSlot()];
	}
}
