package me.delected.coinvestors.menu.newmnu;

import java.math.BigDecimal;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.delected.coinvestors.menu.GuiStage;
import me.delected.coinvestors.menu.InputStageProvider;

public class AmountInputGui extends InputGui<BigDecimal> {

	public AmountInputGui(GuiStage prev) {
		super(prev);
	}

	@Override
	public void open(final Player player) {
		InputStageProvider.openNumberInput(player, this::setData, prev::build);
	}

	@Override
	protected ItemStack presentStack(String link) {
		return null;
	}

	@Override
	protected ItemStack absentStack(String link) {
		return null;
	}
}
