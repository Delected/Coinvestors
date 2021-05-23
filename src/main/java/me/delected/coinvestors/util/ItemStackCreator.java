package me.delected.coinvestors.util;

import static me.delected.coinvestors.util.PersistentDataManager.EVENT_LINK_KEY;
import static me.delected.coinvestors.util.PersistentDataManager.LINK_KEY;
import static me.delected.coinvestors.util.PersistentDataManager.UNMODIFIABLE_KEY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class ItemStackCreator {

	private final ItemStack stack;
	private final ItemMeta meta;

	public ItemStackCreator(ItemStack stack) {
		this.stack = stack;
		this.meta = stack.getItemMeta();
		if (meta == null)
			throw new IllegalArgumentException("Chosen ItemStack Type must have meta!");
	}

	public ItemStackCreator(Material material) {
		this(new ItemStack(material));
	}

	public ItemStack build() {
		stack.setItemMeta(meta);
		return stack;
	}

	public ItemStackCreator setLore(final List<String> lore) {
		meta.setLore(lore);
		return this;
	}

	public ItemStackCreator setLore(String... lore) {
		if (lore.length == 0)
			meta.setLore(Collections.emptyList());
		else if (lore.length == 1)
			meta.setLore(Collections.singletonList(lore[0]));
		else
			meta.setLore(Arrays.asList(lore));
		return this;
	}

	public ItemStackCreator setName(final String name) {
		meta.setDisplayName(name);
		return this;
	}

	private <T> void addPersistentDatum(NamespacedKey key, PersistentDataType<?, T> persistentDataType, T data) {
		meta.getPersistentDataContainer().set(key, persistentDataType, data);
	}

	public ItemStackCreator setUnmodifiable() {
		addPersistentDatum(UNMODIFIABLE_KEY, PersistentDataType.INTEGER, 1);
		return this;
	}

	public ItemStackCreator setLink(String link) {
		addPersistentDatum(LINK_KEY, PersistentDataType.STRING, link);
		return setUnmodifiable();
	}

	public ItemStackCreator setEventLink(String link) {
		addPersistentDatum(EVENT_LINK_KEY, PersistentDataType.STRING, link);
		return setUnmodifiable();
	}
}