package me.delected.coinvestors.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

//TODO: Make this fancy. Look what the superclass provides to you and how other classes work :P
public class AccountCreationGuiStage extends GuiStage {

	public AccountCreationGuiStage() {
		super(MenuState.ACCOUNT_CREATE);
	}

	@Override
	public Inventory build(final Player player) {
		return null;
	}
}
