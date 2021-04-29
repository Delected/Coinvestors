package me.delected.coinvestors.menu;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class GuiPlayerState {
	private final UUID playerId;
	private Inventory menuInventory;

	public GuiPlayerState(final UUID playerId) {
		this.playerId = playerId;
	}

	public void setState(GuiState guiState) {
		this.menuInventory = guiState.build(Bukkit.getPlayer(playerId));
	}

	public Inventory getMenuInventory() {
		return menuInventory;
	}
}
