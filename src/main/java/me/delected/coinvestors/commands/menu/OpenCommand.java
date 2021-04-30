package me.delected.coinvestors.commands.menu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiPlayerState;
import me.delected.coinvestors.menu.MenuGuiState;


public class OpenCommand implements CommandExecutor {

	@Override
	public boolean onCommand(final @NotNull CommandSender commandSender, final @NotNull Command command, final @NotNull String alias, final String[] args) {
		if (!(commandSender instanceof Player))
			return true;
		Player player = (Player) commandSender;
		GuiPlayerState state = Coinvestors.getManager().getStateOf(player);
		state.setState(new MenuGuiState());
		player.openInventory(state.getMenuInventory());
		return true;
	}

}