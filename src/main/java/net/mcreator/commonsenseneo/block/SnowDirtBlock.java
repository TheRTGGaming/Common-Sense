package net.mcreator.commonsenseneo.block;

import net.neoforged.neoforge.common.util.DeferredSoundType;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.procedures.TurntodirtProcedure;

public class SnowDirtBlock extends Block {
	public SnowDirtBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
				.sound(new DeferredSoundType(1.0f, 1.0f, () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.grass.break")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.snow.step")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.grass.place")), () -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.snow.hit")),
						() -> BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("block.snow.fall"))))
				.strength(1f, 10f));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		TurntodirtProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState blockstate, LivingEntity entity, ItemStack itemstack) {
		super.setPlacedBy(world, pos, blockstate, entity, itemstack);
		TurntodirtProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}