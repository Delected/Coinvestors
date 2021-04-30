package me.delected.coinvestors.listeners.population;

import org.bukkit.event.player.PlayerKickEvent;

import me.delected.coinvestors.listeners.AbstractListener;

public class PlayerKickListener extends AbstractListener<PlayerKickEvent> implements PlayerLeaveListener {
	@Override
	public void handle(final PlayerKickEvent event) {
		handleLeave(event);
	}
}
