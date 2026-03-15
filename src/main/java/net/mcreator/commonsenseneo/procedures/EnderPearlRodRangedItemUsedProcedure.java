package net.mcreator.commonsenseneo.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.commonsenseneo.init.CommonSenseModItems;

public class EnderPearlRodRangedItemUsedProcedure {
	public static void execute(LevelAccessor world) {
		if (world instanceof ServerLevel _level) {
			new ItemStack(CommonSenseModItems.ENDER_PEARL_ROD.get()).hurtAndBreak(1, _level, null, _stkprov -> {
			});
		}
	}
}