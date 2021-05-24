package me.delected.coinvestors.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class GuiPlayerState {
	private final UUID playerId;
	private final Map<MenuState, GuiStage> stateMap = new HashMap<>();
	private Inventory menuInventory;
	private GuiStage actualState = new MenuGuiState();
	private boolean doingInput = false;

	public GuiPlayerState(final UUID playerId) {
		this.playerId = playerId;
	}

	public void setStage(GuiStage guiStage) {
		actualState = guiStage;
		stateMap.put(guiStage.getState(), guiStage);
		menuInventory = guiStage.build(Bukkit.getPlayer(playerId));
	}

	public void changeToLastState(MenuState state) {
		actualState = stateMap.get(state);
		menuInventory = actualState.build(Bukkit.getPlayer(playerId));
	}

	public Inventory getMenuInventory() {
		return menuInventory;
	}

	public GuiStage getActualStage() {
		return actualState;
	}

	public boolean isDoingInput() {
		return doingInput;
	}

	public void setDoingInput(final boolean doingInput) {
		this.doingInput = doingInput;
	}
}
