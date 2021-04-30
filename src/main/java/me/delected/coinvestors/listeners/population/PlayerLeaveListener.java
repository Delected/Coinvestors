package me.delected.coinvestors.listeners.population;

import org.bukkit.event.player.PlayerEvent;

import me.delected.coinvestors.Coinvestors;

public interface PlayerLeaveListener {

	default void handleLeave(PlayerEvent event) {
		Coinvestors.getManager().removePlayer(event.getPlayer());
	}

}
