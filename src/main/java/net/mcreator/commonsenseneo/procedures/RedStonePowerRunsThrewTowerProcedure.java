package net.mcreator.commonsenseneo.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.init.CommonSenseModBlocks;
import net.mcreator.commonsenseneo.CommonSenseMod;

public class RedStonePowerRunsThrewTowerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CommonSenseModBlocks.IRON_TOWER_TOP.get()) {
			if (((world.getBlockState(BlockPos.containing(x, y + 1, z))).getBlock() == Blocks.LIGHTNING_ROD && world instanceof Level _level4
					&& _level4.hasNeighborSignal(BlockPos.containing(x, y + 1, z))) == ((world instanceof Level _lvl_getRedPow ? _lvl_getRedPow.getSignal(BlockPos.containing(x, y, z), Direction.UP) : 0) >= 1)) {
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == CommonSenseModBlocks.IRON_TOWER.get()) {
					{
						BlockPos _bp = BlockPos.containing(x, y - 1, z);
						BlockState _bs = CommonSenseModBlocks.IRON_TOWER_POWERED.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				}
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = CommonSenseModBlocks.IRON_TOWER_TOP_POWERED.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			}
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CommonSenseModBlocks.IRON_TOWER_TOP_POWERED.get()) {
			CommonSenseMod.queueServerWork(20, () -> {
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == CommonSenseModBlocks.IRON_TOWER_POWERED.get()) {
					{
						BlockPos _bp = BlockPos.containing(x, y - 1, z);
						BlockState _bs = CommonSenseModBlocks.IRON_TOWER.get().defaultBlockState();
						BlockState _bso = world.getBlockState(_bp);
						for (Property<?> _propertyOld : _bso.getProperties()) {
							Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
							if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
								try {
									_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
								} catch (Exception e) {
								}
						}
						world.setBlock(_bp, _bs, 3);
					}
				}
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = CommonSenseModBlocks.IRON_TOWER_TOP.get().defaultBlockState();
					BlockState _bso = world.getBlockState(_bp);
					for (Property<?> _propertyOld : _bso.getProperties()) {
						Property _propertyNew = _bs.getBlock().getStateDefinition().getProperty(_propertyOld.getName());
						if (_propertyNew != null && _bs.getValue(_propertyNew) != null)
							try {
								_bs = _bs.setValue(_propertyNew, _bso.getValue(_propertyOld));
							} catch (Exception e) {
							}
					}
					world.setBlock(_bp, _bs, 3);
				}
			});
		}
	}
}