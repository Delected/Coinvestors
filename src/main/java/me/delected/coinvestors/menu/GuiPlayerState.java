package me.delected.coinvestors.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class GuiPlayerState {
	private final UUID playerId;
	private final Map<MenuState, GuiState> stateMap = new HashMap<>();
	private Inventory menuInventory;
	private GuiState actualState;

	public GuiPlayerState(final UUID playerId) {
		this.playerId = playerId;
	}

	public void setState(GuiState guiState) {
		actualState = guiState;
		stateMap.put(guiState.getState(), guiState);
		menuInventory = guiState.build(Bukkit.getPlayer(playerId));
	}

	public void changeToLastState(MenuState state) {
		actualState = stateMap.get(state);
		menuInventory = actualState.build(Bukkit.getPlayer(playerId));
	}

	public Inventory getMenuInventory() {
		return menuInventory;
	}

	public GuiState getActualState() {
		return actualState;
	}
}
