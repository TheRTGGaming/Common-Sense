package net.mcreator.commonsenseneo.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class OrangeConcreteSlabBlock extends SlabBlock {
	public OrangeConcreteSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 1.5f).requiresCorrectToolForDrops());
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}
}