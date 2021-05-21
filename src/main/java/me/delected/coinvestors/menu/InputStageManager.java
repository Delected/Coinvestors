package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.wesjd.anvilgui.AnvilGUI;

public class InputStageManager {

	public static void openStringInput(Player player, Consumer<String> resultAcceptor, Supplier<Inventory> prevGui) {
		new AnvilGUI.Builder().onComplete((p, s) -> {
			resultAcceptor.accept(s);
			return AnvilGUI.Response.openInventory(prevGui.get());
		}).open(player);
	}

	public static void openNumberInput(Player player, Consumer<BigDecimal> resultAcceptor, Supplier<Inventory> prevGui) {
		new AnvilGUI.Builder().onComplete((p, s) -> {
			try {
				BigDecimal bigDecimal = new BigDecimal(s);
				resultAcceptor.accept(bigDecimal);
			} catch (NumberFormatException e) {
				resultAcceptor.accept(null);
			}
			return AnvilGUI.Response.openInventory(prevGui.get());
		}).open(player);
	}

}
