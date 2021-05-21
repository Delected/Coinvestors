package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.controller.MenuLinker;
import me.delected.coinvestors.model.wallet.Wallet;
import me.delected.coinvestors.util.PersistentDataManager;

public class TransactionGuiState extends GuiStage {

	private static final String SOURCE_INPUT_LINK = "TRANSACTION_SOURCE_INPUT";
	private static final String AMOUNT_INPUT_LINK = "TRANSACTION_AMOUNT_INPUT";
	private static final String DESTINATION_INPUT_LINK = "TRANSACTION_DESTINATION_INPUT";

	private Wallet source;
	private BigDecimal amount;
	private Wallet target;

	static {
		MenuLinker.registerLink(SOURCE_INPUT_LINK, TransactionGuiState::openSourceInput);
	}

	public TransactionGuiState() {
		super(MenuState.TRANSACTION);
	}

	@Override
	public Inventory build(final Player player) {
		Inventory result = Bukkit.createInventory(player, 27, "Transaction Menu");
		result.setItem(12, PersistentDataManager.setUnmodifiable(createSourceStack()));
		return result;
	}

	private ItemStack createSourceStack() {
		if (source == null) {
			return createNoSourceStack();
		}
		return createSourceInfoStack();
	}

	private ItemStack createSourceInfoStack() {
		ItemStack result = new ItemStack(Material.GREEN_WOOL);
		ItemMeta meta = result.getItemMeta();
		meta.setLore(Arrays.asList(ChatColor.BOLD + "" + source.getWalletType(), "", "Balance: " + source.getBalance()));
		result.setItemMeta(meta);
		return result;
	}

	private static ItemStack createNoSourceStack() {
		ItemStack result = new ItemStack(Material.RED_WOOL);
		ItemMeta meta = result.getItemMeta();
		meta.setLore(Collections.singletonList(ChatColor.GREEN + "Click here to select a source Wallet"));
		result.setItemMeta(meta);
		PersistentDataManager.setLink(result, SOURCE_INPUT_LINK);
		return result;
	}


	private static ItemStack createColored(Object nullable) {
		Material material = Objects.isNull(nullable) ? Material.GREEN_WOOL : Material.RED_WOOL;
		return new ItemStack(material, 1);
	}

	private static void openSourceInput(Player player) {
		TransactionGuiState actualStage = stateOf(player);
		InputStageManager.openStringInput(player, actualStage::retrieveSource, () -> actualStage.build(player));
	}

	private static void openAmountInput(Player player) {
		TransactionGuiState actualStage = stateOf(player);
		InputStageManager.openNumberInput(player, actualStage::setAmount, () -> actualStage.build(player));
	}

	private static void openTargetInput(Player player) {
		TransactionGuiState actualStage = stateOf(player);
		InputStageManager.openStringInput(player, actualStage::retrieveSource, () -> actualStage.build(player));
	}

	private static TransactionGuiState stateOf(Player player) {
		return (TransactionGuiState) Coinvestors.getManager().getStateOf(player).getActualStage();
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
