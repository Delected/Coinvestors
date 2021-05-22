package me.delected.coinvestors.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

public class CryptoSelectionGuiStage extends GuiStage {

	private static List<ItemStack> stacks = createInfoStacks();
	private int page;

	protected CryptoSelectionGuiStage() {
		super(MenuState.CRYPTO_SELECT);
	}

	//TODO
	@Override
	public Inventory build(final Player player) {
		return Bukkit.createInventory(null, 9);
	}

	private static List<ItemStack> createInfoStacks() {
		return Arrays.stream(Crypto.values()).map(CryptoSelectionGuiStage::cryptoInfo).collect(Collectors.toList());
	}

	private static ItemStack cryptoInfo(Crypto crypto) {
		return new ItemStackCreator(Material.SUNFLOWER).setName(crypto.getFullName()).setUnmodifiable().build();
	}
}
