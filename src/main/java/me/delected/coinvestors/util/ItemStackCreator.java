package me.delected.coinvestors.util;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackCreator {

	public static ItemStack[] fromMaterials(Material... materials) {
		return Arrays.stream(materials).map(ItemStack::new).toArray(ItemStack[]::new);
	}

}
