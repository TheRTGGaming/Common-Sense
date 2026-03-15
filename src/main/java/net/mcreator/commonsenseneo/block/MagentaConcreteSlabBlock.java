package net.mcreator.commonsenseneo.block;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SlabBlock;

public class MagentaConcreteSlabBlock extends SlabBlock {
	public MagentaConcreteSlabBlock() {
		super(BlockBehaviour.Properties.of().strength(1f, 1.5f).requiresCorrectToolForDrops());
	}
}