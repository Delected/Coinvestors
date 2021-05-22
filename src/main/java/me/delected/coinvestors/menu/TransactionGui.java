package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.model.wallet.Wallet;
import me.delected.coinvestors.util.ItemStackCreator;

public interface TransactionGui {

	//fixme: replace wallet type with properly done wallet information

	default ItemStack confirmStack() {
		return isValid() ? validStack() : invalidStack();
	}

	default ItemStack validStack() {
		return new ItemStackCreator(Material.GREEN_WOOL).setLink(GuiStage.MAIN_MENU_LINK)
				.setName(ChatColor.GREEN + "Confirm transaction!").build();
	}

	default ItemStack invalidStack() {
		String name = ChatColor.RED + "There are still information missing for: "
					  + String.join(", ", invalids()) + "!";
		return new ItemStackCreator(Material.LIGHT_GRAY_WOOL).setUnmodifiable().setName(name).build();
	}

	List<String> invalids();

	boolean isValid();

	default ItemStack sourceStack(Wallet source, String sourceInputLink) {
		return source == null ? sourcePrompt(sourceInputLink) : sourceInfo(source);
	}

	default ItemStack sourceInfo(Wallet source) {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Source Wallet")
				.setLore(Arrays.asList(ChatColor.BOLD + "WAS_TYPE", "Balance: " + source.getBalance()))
				.setUnmodifiable().build();
	}

	default ItemStack sourcePrompt(String sourceInputLink) {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.GREEN + "Click here to select a source Wallet")
				.setLink(sourceInputLink).build();
	}


	default ItemStack targetStack(Wallet target, String destinationInputLink) {
		return target == null ? targetPrompt(destinationInputLink) : targetInfo(target);
	}

	default ItemStack targetInfo(final Wallet target) {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Target")
				.setLore(Arrays.asList(ChatColor.BOLD + "WAS_TYPE", "Balance: " + target.getBalance()))
				.setUnmodifiable().build();
	}

	default ItemStack targetPrompt(String destinationInputLink) {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.GREEN + "Click here to select a target Wallet")
				.setLink(destinationInputLink).build();

	}


	default ItemStack amountStack(BigDecimal amount, String amountInputLink) {
		return amount == null ? amountPrompt(amountInputLink) : amountInfo(amount);
	}

	default ItemStack amountInfo(BigDecimal amount) {
		String name = ChatColor.BOLD + "" + ChatColor.GOLD + "Amount: " + amount;
		return new ItemStackCreator(Material.SUNFLOWER).setName(name).setUnmodifiable().build();
	}

	default ItemStack amountPrompt(String amountInputLink) {
		return new ItemStackCreator(Material.RED_WOOL).setLink(amountInputLink)
				.setName(ChatColor.GREEN + "Click here to select the transaction amount").build();
	}


}
