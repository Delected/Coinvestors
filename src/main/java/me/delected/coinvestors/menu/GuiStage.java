package me.delected.coinvestors.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GuiStage {

	private final MenuState state;
	private boolean doingInput;

	protected GuiStage(final MenuState state) {
		this.state = state;
	}

	public abstract Inventory build(Player player);

	public MenuState getState() {
		return state;
	}


}
