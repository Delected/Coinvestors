package me.delected.coinvestors.menu;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.util.ItemStackCreator;

public interface Confirmable {

	boolean isValid();
	List<String> invalids();

	default ItemStack confirmStack() {
		return isValid() ? validStack() : invalidStack();
	}

	default ItemStack validStack() {
		return new ItemStackCreator(Material.GREEN_WOOL).setLink(GuiStage.MAIN_MENU_LINK)
				.setName(ChatColor.GREEN + "Confirm!").build();
	}

	default ItemStack invalidStack() {
		String name = ChatColor.RED + "There are still information missing for: ";
		return new ItemStackCreator(Material.LIGHT_GRAY_WOOL).setUnmodifiable()
				.setName(name).setLore(invalids()).build();
	}

}
