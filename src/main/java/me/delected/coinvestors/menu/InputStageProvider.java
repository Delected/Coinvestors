package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.util.ItemStackCreator;
import net.wesjd.anvilgui.AnvilGUI;

public class InputStageProvider {

	private static final ItemStack FIRST = createFirst();

	public static void openStringInput(Player player, Consumer<String> resultAcceptor, Function<Player, Inventory> prevGui) {
		createBuilder().onComplete((p, s) -> {
			resultAcceptor.accept(s);
			Coinvestors.guiManager().setDoingInput(player, false);
			return AnvilGUI.Response.openInventory(prevGui.apply(p));
		}).open(player);
	}

	public static void openNumberInput(Player player, Consumer<BigDecimal> resultAcceptor, Function<Player, Inventory> prevGui) {
		createBuilder().onComplete((p, s) -> {
			try {
				BigDecimal bigDecimal = new BigDecimal(s);
				resultAcceptor.accept(bigDecimal);
				Coinvestors.guiManager().setDoingInput(player, false);
			} catch (NumberFormatException e) {
				resultAcceptor.accept(null);
			}
			return AnvilGUI.Response.openInventory(prevGui.apply(p));
		}).open(player);
	}

	private static AnvilGUI.Builder createBuilder() {
		return new AnvilGUI.Builder().plugin(Coinvestors.INSTANCE()).itemLeft(FIRST).text("Put your input here!");
	}

	private static ItemStack createFirst() {
		String message = ChatColor.GREEN + "Put your input into the rename field and craft to confirm!";
		return new ItemStackCreator(Material.LIME_STAINED_GLASS_PANE).setLore(Collections.singletonList(message))
				.setUnmodifiable().build();
	}


}
