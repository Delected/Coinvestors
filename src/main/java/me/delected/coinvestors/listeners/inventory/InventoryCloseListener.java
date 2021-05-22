package me.delected.coinvestors.listeners.inventory;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.util.PersistentDataManager;

public class InventoryCloseListener extends AbstractListener<InventoryCloseEvent> {
	@Override
	@EventHandler
	public void handle(final InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if (e.getInventory() == Coinvestors.guiManager().getStateOf(player).getMenuInventory()) {
			World world = player.getWorld();
			Location location = player.getLocation();
			ItemStack[] contents = e.getInventory().getContents();
			for (int i = 0; i < contents.length; i++) {
				final ItemStack stack = contents[i];
				if (stack == null)
					continue;
				if (PersistentDataManager.isModifiable(stack)) {
					e.getInventory().setItem(i, null);
					player.getInventory().addItem(stack).forEach((k, s) -> world.dropItem(location, s));
				}
			}
		}
	}
}
