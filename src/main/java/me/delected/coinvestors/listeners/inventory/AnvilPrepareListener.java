package me.delected.coinvestors.listeners.inventory;

import java.util.Collections;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.listeners.AbstractListener;
import me.delected.coinvestors.util.ItemStackCreator;

public class AnvilPrepareListener extends AbstractListener<PrepareAnvilEvent> {

	private static final ItemStack INPUT_PROMPT = generateInputPrompt();

	@EventHandler
	@Override
	public void handle(final PrepareAnvilEvent event) {
		Player player = (Player) (event.getView().getBottomInventory().getHolder());
		if (player != null && Coinvestors.getManager().getStateOf(player).isDoingInput()) {
			event.getInventory().setItem(2, INPUT_PROMPT);
			event.getInventory().setRepairCost(0);
		}
	}

	private static ItemStack generateInputPrompt() {
		return new ItemStackCreator(Material.LIME_STAINED_GLASS_PANE).setUnmodifiable()
				.setLore(Collections.singletonList(ChatColor.GREEN + "Confirm!")).build();
	}

}
