package net.mcreator.commonsenseneo.procedures;

import net.neoforged.neoforge.event.level.block.CropGrowEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.Event;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

@EventBusSubscriber
public class BoneMealCropsProcedure {
	@SubscribeEvent
	public static void onCropGrowPre(CropGrowEvent.Pre event) {
		execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getState());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		execute(null, world, x, y, z, blockstate);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if (blockstate.is(BlockTags.create(ResourceLocation.parse("minecraft:crops")))) {
			if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world.getLevelData().isRaining()) {
				if (Math.random() >= 0.5) {
					if (world instanceof Level _level) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						if (BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), _level, _bp) || BoneMealItem.growWaterPlant(new ItemStack(Items.BONE_MEAL), _level, _bp, null)) {
							if (!_level.isClientSide())
								_level.levelEvent(2005, _bp, 0);
						}
					}
				}
			}
			if (world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && world.getLevelData().isThundering()) {
				if (Math.random() >= 0.8) {
					if (world instanceof Level _level) {
						BlockPos _bp = BlockPos.containing(x, y, z);
						if (BoneMealItem.growCrop(new ItemStack(Items.BONE_MEAL), _level, _bp) || BoneMealItem.growWaterPlant(new ItemStack(Items.BONE_MEAL), _level, _bp, null)) {
							if (!_level.isClientSide())
								_level.levelEvent(2005, _bp, 0);
						}
					}
				}
			}
		}
	}
}