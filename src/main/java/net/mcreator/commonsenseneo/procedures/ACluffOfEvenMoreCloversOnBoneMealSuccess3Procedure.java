package net.mcreator.commonsenseneo.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.init.CommonSenseModBlocks;

public class ACluffOfEvenMoreCloversOnBoneMealSuccess3Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CommonSenseModBlocks.A_CLUFF_OF_EVEN_MORE_CLOVERS.get()) {
			world.setBlock(BlockPos.containing(x, y, z), CommonSenseModBlocks.A_CLUFF_OF_SO_MUCH_CLOVERS.get().defaultBlockState(), 3);
		}
	}
}