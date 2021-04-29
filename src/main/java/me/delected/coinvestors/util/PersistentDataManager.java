package me.delected.coinvestors.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import me.delected.coinvestors.Coinvestors;

public class PersistentDataManager {
	private static final NamespacedKey UNMODIFIABLE_KEY
			= new NamespacedKey(Coinvestors.INSTANCE(), "UNMODIFIABLE");

	public static void setUnmodifiable(ItemStack target) {
		ItemMeta meta = target.getItemMeta();
		if (meta == null)
			return;
		meta.getPersistentDataContainer().set(UNMODIFIABLE_KEY, PersistentDataType.INTEGER, 1);
		target.setItemMeta(meta);
	}

	public static ItemStack[] asUnmodifiable(ItemStack... stacks) {
		for (final ItemStack stack : stacks) {
			setUnmodifiable(stack);
		}
		return stacks;
	}

	public static boolean isUnmodifiable(@Nullable ItemStack stack) {
		if (stack == null)
			return false;
		if (stack.getItemMeta() == null)
			return false;
		return stack.getItemMeta().getPersistentDataContainer().has(UNMODIFIABLE_KEY, PersistentDataType.INTEGER);
	}

	public static boolean isModifiable(ItemStack stack) {
		return !isUnmodifiable(stack);
	}

}
