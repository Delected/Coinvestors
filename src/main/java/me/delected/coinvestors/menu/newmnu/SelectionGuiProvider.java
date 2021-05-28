package me.delected.coinvestors.menu.newmnu;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.menu.GuiStage;

public class SelectionGuiProvider {

	public static <T> void openSelectionGui(Player player, int size, String title, Supplier<List<T>> dataSupplier,
											Consumer<T> dataAcceptor,
											Function<T, ItemStack> renderer, GuiStage next) {

		SelectionGui<T> gui = new SelectionGui<T>(size, title, dataAcceptor, dataSupplier, renderer, next) {
		};
		Coinvestors.guiManager().redirect(player, gui);
	}

}
