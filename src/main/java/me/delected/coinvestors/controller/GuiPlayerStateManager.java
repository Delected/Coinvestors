package me.delected.coinvestors.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.delected.coinvestors.menu.GuiPlayerState;


public class GuiPlayerStateManager {

	private final Map<UUID, GuiPlayerState> guiStateMap = new HashMap<>();

	public GuiPlayerState getStateOf(Player player) {
		UUID uuid = player.getUniqueId();
		return guiStateMap.computeIfAbsent(uuid, GuiPlayerState::new);
	}

	public void removePlayer(Player player) {
		guiStateMap.remove(player.getUniqueId());
	}

}
