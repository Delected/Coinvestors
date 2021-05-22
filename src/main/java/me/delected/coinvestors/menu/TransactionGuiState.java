package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.model.wallet.Wallet;
import me.delected.coinvestors.util.ItemStackCreator;

public class TransactionGuiState extends GuiStage {

	private static final String SOURCE_INPUT_LINK = "TRANSACTION_SOURCE_INPUT";
	private static final String AMOUNT_INPUT_LINK = "TRANSACTION_AMOUNT_INPUT";
	private static final String DESTINATION_INPUT_LINK = "TRANSACTION_DESTINATION_INPUT";

	private Wallet source;
	private BigDecimal amount;
	private Wallet target;

	static {
		MenuLinker.registerLink(SOURCE_INPUT_LINK, TransactionGuiState::openSourceInput);
		MenuLinker.registerLink(DESTINATION_INPUT_LINK, TransactionGuiState::openTargetInput);
		MenuLinker.registerLink(AMOUNT_INPUT_LINK, TransactionGuiState::openAmountInput);
	}

	public TransactionGuiState() {
		super(MenuState.TRANSACTION);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(player, 27, "Transaction Menu");
		result.setItem(11, sourceStack());
		result.setItem(13, amountStack());
		result.setItem(15, targetStack());
		result.setItem(26, backLinkStack());
		return result;
	}

	private ItemStack sourceStack() {
		return source == null ? sourcePrompt() : sourceInfo();
	}

	//fixme new info for source and target wallets

	private ItemStack sourceInfo() {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Source Wallet")
				.setLore(Arrays.asList(ChatColor.BOLD + "WAS_TYPE", "Balance: " + source.getBalance()))
				.setUnmodifiable().build();
	}

	private static ItemStack sourcePrompt() {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.GREEN + "Click here to select a source Wallet")
				.setLink(SOURCE_INPUT_LINK).build();
	}

	private ItemStack targetStack() {
		return target == null ? targetPrompt() : targetInfo();
	}

	private ItemStack targetInfo() {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Target")
				.setLore(Arrays.asList(ChatColor.BOLD + "WAS_TYPE", "Balance: " + source.getBalance()))
				.setUnmodifiable().build();
	}

	private static ItemStack targetPrompt() {
		return new ItemStackCreator(Material.RED_WOOL).setName(ChatColor.GREEN + "Click here to select a target Wallet")
				.setLink(DESTINATION_INPUT_LINK).build();
	}

	private ItemStack amountStack() {
		return amount == null ? amountPrompt() : amountInfo();
	}

	private ItemStack amountInfo() {
		String name = ChatColor.BOLD + "" + ChatColor.GOLD + "Amount: " + amount;
		return new ItemStackCreator(Material.SUNFLOWER).setName(name).setUnmodifiable().build();
	}

	private static ItemStack amountPrompt() {
		return new ItemStackCreator(Material.RED_WOOL).setLink(AMOUNT_INPUT_LINK)
				.setName(ChatColor.GREEN + "Click here to select the transaction amount").build();
	}

	private static void openSourceInput(Player player) {
		TransactionGuiState actualStage = prepareInputFor(player);
		InputStageManager.openStringInput(player, actualStage::retrieveSource, actualStage::build);
	}

	private static void openAmountInput(Player player) {
		TransactionGuiState actualStage = prepareInputFor(player);
		InputStageManager.openNumberInput(player, actualStage::setAmount, actualStage::build);
	}

	private static void openTargetInput(Player player) {
		TransactionGuiState actualStage = prepareInputFor(player);
		InputStageManager.openStringInput(player, actualStage::retrieveTarget, actualStage::build);
	}

	private static TransactionGuiState prepareInputFor(Player player) {
		Coinvestors.guiManager().setDoingInput(player, true);
		return (TransactionGuiState) Coinvestors.guiManager().getStateOf(player).getActualStage();
	}

	//TODO: When wallet access logic is implemented, retrieve the wallets here

	public void retrieveSource(final String raw) {
		setSource(null);
	}

	private void setSource(Wallet source) {
		this.source = source;
	}

	public void retrieveTarget(String raw) {
		setTarget(null);
	}

	private void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	private void setTarget(final Wallet target) {
		this.target = target;
	}
}
