package net.mcreator.commonsenseneo.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.core.BlockPos;

public class AncientOreBlock extends Block {
	public AncientOreBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GILDED_BLACKSTONE).strength(66f, 1200f).requiresCorrectToolForDrops().pushReaction(PushReaction.BLOCK).instrument(NoteBlockInstrument.BASEDRUM));
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 15;
	}
}