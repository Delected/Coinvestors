package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.wallet.Wallet;
import me.delected.coinvestors.util.ItemStackCreator;

interface TransactionGui extends Confirmable {

	String SOURCE_INPUT_LINK = "WALLET_TRANSACTION_SOURCE_INPUT";
	String AMOUNT_INPUT_LINK = "WALLET_TRANSACTION_AMOUNT_INPUT";
	String DESTINATION_INPUT_LINK = "WALLET_TRANSACTION_DESTINATION_INPUT";


	//fixme: replace wallet type with properly done wallet information


	default ItemStack sourceStack(Wallet source) {
		return source == null ? sourcePrompt() : sourceInfo(source);
	}

	default ItemStack sourceInfo(Wallet source) {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Source Wallet")
				.setLore(Arrays.asList(ChatColor.BOLD + "WAS_TYPE", "Balance: " + source.getBalance()))
				.setUnmodifiable().build();
	}

	default ItemStack sourcePrompt() {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.GREEN + "Click here to select a source Wallet")
				.setLink(SOURCE_INPUT_LINK).build();
	}


	default ItemStack targetStack(Wallet target) {
		return target == null ? targetPrompt() : targetInfo(target);
	}

	default ItemStack targetInfo(final Wallet target) {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Target")
				.setLore(Arrays.asList(ChatColor.BOLD + "WAS_TYPE", "Balance: " + target.getBalance()))
				.setUnmodifiable().build();
	}

	default ItemStack targetPrompt() {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.GREEN + "Click here to select a target Wallet")
				.setLink(DESTINATION_INPUT_LINK).build();

	}


	default ItemStack amountStack(BigDecimal amount) {
		return amount == null ? amountPrompt() : amountInfo(amount);
	}

	default ItemStack amountInfo(BigDecimal amount) {
		String name = ChatColor.BOLD + "" + ChatColor.GOLD + "Amount: " + amount;
		return new ItemStackCreator(Material.SUNFLOWER).setName(name).setUnmodifiable().build();
	}

	default ItemStack amountPrompt() {
		return new ItemStackCreator(Material.RED_WOOL).setLink(AMOUNT_INPUT_LINK)
				.setName(ChatColor.GREEN + "Click here to select the transaction amount").build();
	}

	default void retrieveSource(String raw) {

	}

	default void retrieveTarget(String raw) {

	}

	default void setAmount(final BigDecimal amount) {

	}

	static void openSourceInput(Player player) {
		TransactionGui transactionGui = prepareInputFor(player);
		GuiStage guiStage = (GuiStage) transactionGui;
		InputStageManager.openStringInput(player, transactionGui::retrieveSource, guiStage::build);
	}

	static void openAmountInput(Player player) {
		TransactionGui transactionGui = prepareInputFor(player);
		GuiStage guiStage = (GuiStage) transactionGui;
		InputStageManager.openNumberInput(player, transactionGui::setAmount, guiStage::build);
	}

	static void openTargetInput(Player player) {
		TransactionGui transactionGui = prepareInputFor(player);
		GuiStage guiStage = (GuiStage) transactionGui;
		InputStageManager.openStringInput(player, transactionGui::retrieveTarget, guiStage::build);
	}

	static TransactionGui prepareInputFor(Player player) {
		Coinvestors.guiManager().setDoingInput(player, true);
		return (TransactionGui) Coinvestors.guiManager().getStateOf(player).getActualStage();
	}

	static List<String> makeLoreWhite(List<String> unformatted) {
		return unformatted.stream().map(s -> ChatColor.WHITE + s).collect(Collectors.toList());
	}

}
