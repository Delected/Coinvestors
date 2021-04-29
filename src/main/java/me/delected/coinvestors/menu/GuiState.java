package me.delected.coinvestors.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class GuiState {
	//MENU, DEPOSIT, WITHDRAW, EXCHANGE, TRANSACTION, CRYPTO_SELECT, NUMBER_INPUT, STRING_INPUT

	public abstract Inventory build(Player player);

}
