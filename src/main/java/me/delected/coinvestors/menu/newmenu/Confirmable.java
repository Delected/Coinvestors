package me.delected.coinvestors.menu.newmenu;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.util.ItemStackCreator;

public interface Confirmable {

	String CONFIRM_LINK = "CONFIRM_BUTTON_ACTION";

	boolean isValid();

	List<String> invalids();

	default ItemStack confirmStack(String link) {
		return isValid() ? validStack(link) : invalidStack();
	}

	default ItemStack validStack(String link) {
		return new ItemStackCreator(Material.GREEN_WOOL).setLink(link)
				.setName(ChatColor.GREEN + "Confirm!").build();
	}

	Consumer<Player> confirmAction();

	default ItemStack invalidStack() {
		String name = ChatColor.RED + "The following information is still missing: ";
		final List<String> listedInvalids = invalids().stream()
				.map(item -> String.join(" ", ChatColor.WHITE + "◆", item))
				.collect(Collectors.toList());
		return new ItemStackCreator(Material.LIGHT_GRAY_WOOL).setUnmodifiable()
				.setName(name).setLore(listedInvalids)
				.build();
	}

	static void confirm(Player player) {
		((Confirmable) Coinvestors.guiManager().getStateOf(player).getActualStage()).confirmAction().accept(player);
	}

	static boolean checkValid(InputGui<?>... inputGuis) {
		return Arrays.stream(inputGuis).allMatch(inputGui -> Objects.nonNull(inputGui.getData()));
	}

}
