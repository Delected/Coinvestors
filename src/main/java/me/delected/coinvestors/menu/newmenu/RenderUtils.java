package me.delected.coinvestors.menu.newmenu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.model.accounts.Wallet;
import me.delected.coinvestors.util.ItemStackCreator;

public class RenderUtils {

	public static ItemStack renderWalletEventLink(Wallet wallet, String eventLink) {
		return preRenderWallet(wallet)
				.setEventLink(eventLink)
				.build();
	}

	public static ItemStack renderWalletLink(Wallet wallet, String link) {
		return preRenderWallet(wallet)
				.setLink(link)
				.build();
	}

	public static ItemStack renderWalletNoAction(Wallet wallet) {
		return preRenderWallet(wallet)
				.setUnmodifiable()
				.build();
	}

	private static ItemStackCreator preRenderWallet(Wallet wallet) {
		return new ItemStackCreator(Material.SUNFLOWER).setName(wallet.getCrypto() + " Wallet")
				.setLore(ChatColor.YELLOW + "Balance: " + ChatColor.WHITE + wallet.getBalance(),
						ChatColor.YELLOW + "Wallet-ID: " + ChatColor.WHITE + wallet.getCompleteAddress());
	}

	public static ItemStack absentStack(String link, String inputMessage) {
		return new ItemStackCreator(Material.RED_WOOL).setLink(link).setName(ChatColor.RED + inputMessage).build();
	}



}
