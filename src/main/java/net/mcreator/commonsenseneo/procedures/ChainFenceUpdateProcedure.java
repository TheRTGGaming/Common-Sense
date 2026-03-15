package net.mcreator.commonsenseneo.procedures;

import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.init.CommonSenseModBlocks;

public class ChainFenceUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (!((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) && !((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get())
				&& !((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) && !((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get())) {
			{
				int _value = 0;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		}
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()
				&& (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 11;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()
				&& (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 12;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()
				&& (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 13;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()
				&& (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 14;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()
				&& (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 15;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 10;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 9;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 8;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 7;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 6;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get() && (world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 5;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 4;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 3;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 2;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == CommonSenseModBlocks.CHAIN_FENCE.get()) {
			{
				int _value = 1;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
		}
	}
}