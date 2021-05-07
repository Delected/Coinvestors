package me.delected.coinvestors.util;

import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import me.delected.coinvestors.Coinvestors;

public class PersistentDataManager {
	private static final JavaPlugin COINVESTORS = Coinvestors.INSTANCE();
	private static final NamespacedKey UNMODIFIABLE_KEY = new NamespacedKey(COINVESTORS, "UNMODIFIABLE");
	private static final NamespacedKey LINK_KEY = new NamespacedKey(COINVESTORS, "LINK");

	public static ItemStack setUnmodifiable(ItemStack target) {
		addPersistentDatum(target, UNMODIFIABLE_KEY, PersistentDataType.INTEGER, 1);
		return target;
	}

	public static ItemStack[] asUnmodifiable(ItemStack... stacks) {
		for (final ItemStack stack : stacks) {
			setUnmodifiable(stack);
		}
		return stacks;
	}

	public static boolean isUnmodifiable(@Nullable ItemStack stack) {
		return hasPersistentDatum(stack, UNMODIFIABLE_KEY, PersistentDataType.INTEGER);
	}

	public static boolean isModifiable(ItemStack stack) {
		return !isUnmodifiable(stack);
	}

	public static ItemStack setLink(ItemStack target, String link) {
		addPersistentDatum(target, LINK_KEY, PersistentDataType.STRING, link);
		return target;
	}

	public static boolean isLink(ItemStack stack) {
		return hasPersistentDatum(stack, LINK_KEY, PersistentDataType.STRING);
	}


	public static String getLink(ItemStack stack) {
		if (!isLink(stack))
			return null;
		return getPersistentDatum(stack, LINK_KEY, PersistentDataType.STRING);
	}

	private static <T> void addPersistentDatum(ItemStack target, NamespacedKey key, PersistentDataType<?, T> persistentDataType, T data) {
		ItemMeta meta = target.getItemMeta();
		if (meta != null) {
			meta.getPersistentDataContainer().set(key, persistentDataType, data);
			target.setItemMeta(meta);
		}
	}

	private static <T> boolean hasPersistentDatum(ItemStack stack, NamespacedKey key, PersistentDataType<?, T> type) {
		if (stack == null || stack.getItemMeta() == null)
			return false;
		return stack.getItemMeta().getPersistentDataContainer().has(key, type);
	}

	@SuppressWarnings("ConstantConditions")
	private static <T> T getPersistentDatum(ItemStack stack, NamespacedKey key, PersistentDataType<?, T> type) {
		return stack.getItemMeta().getPersistentDataContainer().get(key, type);
	}

}
