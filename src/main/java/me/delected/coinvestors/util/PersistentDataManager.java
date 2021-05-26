package me.delected.coinvestors.util;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import me.delected.coinvestors.Coinvestors;

public class PersistentDataManager {
	private static final JavaPlugin COINVESTORS = Coinvestors.instance();
	public static final NamespacedKey UNMODIFIABLE_KEY = new NamespacedKey(COINVESTORS, "UNMODIFIABLE");
	public static final NamespacedKey LINK_KEY = new NamespacedKey(COINVESTORS, "LINK");
	public static final NamespacedKey EVENT_LINK_KEY = new NamespacedKey(COINVESTORS, "EVENT_LINK_KEY");

	public static boolean isUnmodifiable(@Nullable ItemStack stack) {
		return hasPersistentDatum(stack, UNMODIFIABLE_KEY, PersistentDataType.INTEGER);
	}

	public static boolean isModifiable(ItemStack stack) {
		return !isUnmodifiable(stack);
	}

	public static boolean isLink(ItemStack stack) {
		return hasPersistentDatum(stack, LINK_KEY, PersistentDataType.STRING);
	}

	public static String getLinkID(ItemStack stack) {
		if (!isLink(stack))
			return null;
		return getPersistentDatum(stack, LINK_KEY, PersistentDataType.STRING);
	}

	public static boolean isEventLink(ItemStack stack) {
		return hasPersistentDatum(stack, EVENT_LINK_KEY, PersistentDataType.STRING);
	}

	public static String getEventLinkID(ItemStack stack) {
		if (!isEventLink(stack))
			return null;
		return getPersistentDatum(stack, EVENT_LINK_KEY, PersistentDataType.STRING);
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
