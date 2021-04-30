package me.delected.coinvestors.listeners.inventory;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.util.PersistentDataManager;

public class InventoryClickListener extends AbstractListener<InventoryClickEvent> {

	@Override
	@EventHandler
	public void handle(final InventoryClickEvent e) {
		if (PersistentDataManager.isUnmodifiable(e.getCurrentItem()))
			e.setCancelled(true);
	}
}
