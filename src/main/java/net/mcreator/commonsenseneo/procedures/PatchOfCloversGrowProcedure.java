package net.mcreator.commonsenseneo.procedures;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.init.CommonSenseModBlocks;
import net.mcreator.commonsenseneo.CommonSenseMod;

public class PatchOfCloversGrowProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		CommonSenseMod.queueServerWork(4200, () -> {
			if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CommonSenseModBlocks.A_CLUFF_OF_CLOVERS.get()) {
				{
					BlockPos _bp = BlockPos.containing(x, y, z);
					BlockState _bs = CommonSenseModBlocks.A_CLUFF_OF_MORE_CLOVERS.get().defaultBlockState();
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
				CommonSenseMod.queueServerWork(4200, () -> {
					if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CommonSenseModBlocks.A_CLUFF_OF_MORE_CLOVERS.get()) {
						{
							BlockPos _bp = BlockPos.containing(x, y, z);
							BlockState _bs = CommonSenseModBlocks.A_CLUFF_OF_EVEN_MORE_CLOVERS.get().defaultBlockState();
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
						CommonSenseMod.queueServerWork(4200, () -> {
							if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == CommonSenseModBlocks.A_CLUFF_OF_EVEN_MORE_CLOVERS.get()) {
								{
									BlockPos _bp = BlockPos.containing(x, y, z);
									BlockState _bs = CommonSenseModBlocks.A_CLUFF_OF_SO_MUCH_CLOVERS.get().defaultBlockState();
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
						});
					}
				});
			}
		});
	}
}