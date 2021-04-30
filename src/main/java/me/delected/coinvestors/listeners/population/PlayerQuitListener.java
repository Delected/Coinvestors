package me.delected.coinvestors.listeners.population;

import org.bukkit.event.player.PlayerQuitEvent;

import me.delected.coinvestors.listeners.AbstractListener;

public class PlayerQuitListener extends AbstractListener<PlayerQuitEvent> implements PlayerLeaveListener {
	@Override
	public void handle(final PlayerQuitEvent event) {
		handleLeave(event);
	}
}
