package me.delected.coinvestors.listeners.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.util.PersistentDataManager;

public class InventoryClickListener extends AbstractListener<InventoryClickEvent> {

	@Override
	@EventHandler
	public void handle(final InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		if (PersistentDataManager.isUnmodifiable(item)) {
			e.setCancelled(true);
		}
		if (PersistentDataManager.isLink(item)) {
			MenuLinker.getAction(PersistentDataManager.getLinkID(item)).accept((Player) e.getWhoClicked());
		}
	}
}
