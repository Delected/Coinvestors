package me.delected.coinvestors.menu.newmenu;

import java.math.BigDecimal;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.util.ItemStackCreator;

public class AmountInputGui extends InputGui<BigDecimal> {

	public AmountInputGui(GuiStage prev) {
		super(prev);
	}

	@Override
	public void open(final Player player) {
		InputStageProvider.openNumberInput(player, this::setData, nextGui::build);
	}	

	@Override
	protected ItemStack presentStack(String link) {
		return new ItemStackCreator(Material.WARPED_SIGN)
				.setLink(link)
				.setName(ChatColor.GREEN + "Amount:")
				.setLore(ChatColor.WHITE + getData().toString())
				.build();
	}

	@Override
	protected ItemStack absentStack(String link) {
		return new ItemStackCreator(Material.RED_WOOL)
				.setName(ChatColor.RED + "Click here to choose an amount!")
				.setLink(link)
				.build();
	}
}
