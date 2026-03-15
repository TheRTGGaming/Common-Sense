package net.mcreator.commonsenseneo.block;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

import com.mojang.serialization.MapCodec;

public class BlockOfSulfurBlock extends FallingBlock {
	public static final MapCodec<BlockOfSulfurBlock> CODEC = simpleCodec(properties -> new BlockOfSulfurBlock());

	public MapCodec<BlockOfSulfurBlock> codec() {
		return CODEC;
	}

	public BlockOfSulfurBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SAND).strength(10f, 0f).friction(0.8f).instrument(NoteBlockInstrument.SNARE));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}