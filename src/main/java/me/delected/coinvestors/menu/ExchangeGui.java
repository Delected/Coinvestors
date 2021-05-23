package me.delected.coinvestors.menu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.Coinvestors;
import me.delected.coinvestors.model.currency.Crypto;
import me.delected.coinvestors.util.ItemStackCreator;

public interface ExchangeGui extends Confirmable {

	String SOURCE_CRYPTO_INPUT_LINK = "EXCHANGE_SOURCE_INPUT_LINK";
	String TARGET_CRYPTO_INPUT_LINK = "EXCHANGE_TARGET_INPUT_LINK";

	default ItemStack sourceCryptoStack(Crypto crypto) {
		return crypto == null ? sourceCryptoPrompt() : sourceCryptoInfo(crypto);
	}

	default ItemStack sourceCryptoInfo(Crypto sourceCrypto) {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Source Crypto")
				.setLore(Collections.singletonList(sourceCrypto.getFullName()))
				.setUnmodifiable().build();
	}

	default ItemStack sourceCryptoPrompt() {
		return new ItemStackCreator(Material.RED_WOOL)
				.setName(ChatColor.GREEN + "Click here to select the source Crypto")
				.setLink(SOURCE_CRYPTO_INPUT_LINK).build();
	}

	default ItemStack targetCryptoStack(Crypto crypto) {
		return crypto == null ? targetCryptoPrompt() : targetCryptoInfo(crypto);
	}

	default ItemStack targetCryptoInfo(Crypto sourceCrypto) {
		return new ItemStackCreator(Material.GREEN_WOOL).setName("Source Crypto")
				.setLore(Collections.singletonList(sourceCrypto.getFullName()))
				.setUnmodifiable().build();
	}

	default ItemStack targetCryptoPrompt() {
		return new ItemStackCreator(Material.RED_WOOL)
				.setName(ChatColor.GREEN + "Click here to select the source Crypto")
				.setLink(TARGET_CRYPTO_INPUT_LINK).build();
	}


	static ItemStack createExchangePrizeInfo(Crypto source, BigDecimal amount, Crypto destination) {
		List<String> info = new ArrayList<>();
		BigDecimal exchangePrize = calcExchangeCourse(source, destination);
		info.add(ChatColor.YELLOW + "Exchange prize: " + ChatColor.WHITE + exchangePrize);
		info.add(ChatColor.YELLOW + "Amount sent to destination: " + ChatColor.WHITE + exchangePrize.multiply(amount));
		return new ItemStackCreator(Material.WHITE_STAINED_GLASS_PANE)
				.setName(ChatColor.YELLOW + "Transaction info").setLore(info).build();
	}

	static ExchangeGui prepareInputFor(Player player) {
		Coinvestors.guiManager().setDoingInput(player, true);
		return (ExchangeGui) Coinvestors.guiManager().getStateOf(player).getActualStage();
	}

	static void openSourceInput(Player player) {
		ExchangeGui transactionGui = prepareInputFor(player);
		GuiStage guiStage = (GuiStage) transactionGui;
		InputStageManager.openStringInput(player, transactionGui::retrieveSourceCrypto, guiStage::build);
	}

	static ItemStack walletInputUnavailable() {
		return new ItemStackCreator(Material.GRAY_WOOL).setUnmodifiable()
				.setName(ChatColor.DARK_RED + "Select a Crypto before selecting the wallet!").build();
	}

	void retrieveSourceCrypto(String raw);

	void retrieveTargetCrypto(String raw);

	static Crypto parseCrypto(String s) {
		try {
			return Crypto.valueOf(s.toUpperCase());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	static BigDecimal calcExchangeCourse(Crypto one, Crypto two) {
		return BigDecimal.ONE;
	}
}
