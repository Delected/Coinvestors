package me.delected.coinvestors.menu;

import static org.bukkit.Material.BROWN_WOOL;
import static org.bukkit.Material.GREEN_WOOL;
import static org.bukkit.Material.RED_WOOL;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.util.ItemStackCreator;
import me.delected.coinvestors.util.PersistentDataManager;

public class MenuGuiState extends GuiState {

	private static final Inventory INVENTORY = createInventory();

	private static Inventory createInventory() {
		Inventory result = Bukkit.createInventory(null, 9, "MENU");
		result.setContents(createButtons());
		return result;
	}

	private static ItemStack[] createButtons() {
		return PersistentDataManager.asUnmodifiable(ItemStackCreator.fromMaterials(RED_WOOL, GREEN_WOOL, BROWN_WOOL));
	}

	@Override
	public Inventory build(final Player player) {
		return INVENTORY;
	}

}
