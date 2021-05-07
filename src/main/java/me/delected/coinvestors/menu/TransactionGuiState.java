package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.delected.coinvestors.model.wallet.Wallet;
import me.delected.coinvestors.util.PersistentDataManager;

public class TransactionGuiState extends GuiState {

	private Wallet source;
	private BigDecimal amount;
	private Wallet target;

	public TransactionGuiState() {
		super(MenuState.TRANSACTION);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(player, 27, "Transaction Menu");
		result.setItem(12, createSourceStack());
		result.forEach(PersistentDataManager::setUnmodifiable);
		return result;
	}

	private ItemStack createSourceStack() {
		if (source == null) {
			return createNoSourceStack();
		}
		return createSourceInfoStack();
	}

	private ItemStack createSourceInfoStack() {
		ItemStack result = new ItemStack(Material.GREEN_WOOL);
		ItemMeta meta = result.getItemMeta();
		meta.setLore(Arrays.asList(ChatColor.BOLD + "" + source.getWalletType(), "", "Balance: " + source.getBalance()));
		result.setItemMeta(meta);
		return result;
	}

	private ItemStack createNoSourceStack() {
		ItemStack result = new ItemStack(Material.RED_WOOL);
		ItemMeta meta = result.getItemMeta();
		meta.setLore(Collections.singletonList(ChatColor.GREEN + "Click here to select a source Wallet"));
		result.setItemMeta(meta);
		return result;
	}


	private static ItemStack createColored(Object nullable) {
		Material material = Objects.isNull(nullable) ? Material.GREEN_WOOL : Material.RED_WOOL;
		return new ItemStack(material, 1);
	}

}
