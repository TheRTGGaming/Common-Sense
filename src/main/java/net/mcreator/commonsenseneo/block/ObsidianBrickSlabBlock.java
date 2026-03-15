package net.mcreator.commonsenseneo.block;

import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class ObsidianBrickSlabBlock extends SlabBlock {
	public ObsidianBrickSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(50f, 1200f).pushReaction(PushReaction.BLOCK).instrument(NoteBlockInstrument.BASEDRUM));
	}
}