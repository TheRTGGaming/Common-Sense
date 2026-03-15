package net.mcreator.commonsenseneo.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.Mob;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.procedures.ChainFenceUpdateProcedure;

public class ChainFenceBlock extends Block implements SimpleWaterloggedBlock {
	public static final IntegerProperty BLOCKSTATE = IntegerProperty.create("blockstate", 0, 15);
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE_1 = Shapes.or(box(7.5, 0, 0, 8.5, 20.1, 8.5), box(7.5, 0, 7.5, 8.5, 20.5, 8.5));
	private static final VoxelShape SHAPE_2 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.1, 16), box(7.5, 0, 7.5, 8.5, 20.5, 8.5));
	private static final VoxelShape SHAPE_3 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 7.5, 16, 20.1, 8.5));
	private static final VoxelShape SHAPE_4 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(0, 0, 7.5, 8.5, 20.1, 8.5));
	private static final VoxelShape SHAPE_5 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 0, 8.5, 20.1, 16));
	private static final VoxelShape SHAPE_6 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(0, 0, 7.5, 16, 20.1, 8.5));
	private static final VoxelShape SHAPE_7 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 0, 8.5, 20.1, 8.5), box(0, 0, 7.5, 8.5, 20.1, 8.5));
	private static final VoxelShape SHAPE_8 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 0, 8.5, 20.1, 8.5), box(7.5, 0, 7.5, 16, 20.1, 8.5));
	private static final VoxelShape SHAPE_9 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 7.5, 8.5, 20.1, 16), box(0, 0, 7.5, 8.5, 20.1, 8.5));
	private static final VoxelShape SHAPE_10 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 7.5, 8.5, 20.1, 16), box(7.5, 0, 7.5, 16, 20.1, 8.5));
	private static final VoxelShape SHAPE_11 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 0, 8.5, 20.1, 16), box(0, 0, 7.5, 16, 20.1, 8.5));
	private static final VoxelShape SHAPE_12 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(0, 0, 7.5, 16, 20.1, 8.5), box(7.5, 0, 0, 8.5, 20.1, 8.5));
	private static final VoxelShape SHAPE_13 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 0, 8.5, 20.1, 16), box(0, 0, 7.5, 8.5, 20.1, 8.5));
	private static final VoxelShape SHAPE_14 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(7.5, 0, 0, 8.5, 20.1, 16), box(7.5, 0, 7.5, 16, 20.1, 8.5));
	private static final VoxelShape SHAPE_15 = Shapes.or(box(7.5, 0, 7.5, 8.5, 20.5, 8.5), box(0, 0, 7.5, 16, 20.1, 8.5), box(7.5, 0, 7.5, 8.5, 20.1, 16));
	private static final VoxelShape SHAPE = box(7.5, -1, 7.5, 8.5, 20.5, 8.5);

	public ChainFenceBlock() {
		super(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.METAL).strength(5f, 7f).lightLevel(s -> (new Object() {
			public int getLightLevel() {
				if (s.getValue(BLOCKSTATE) == 1)
					return 0;
				if (s.getValue(BLOCKSTATE) == 2)
					return 0;
				if (s.getValue(BLOCKSTATE) == 3)
					return 0;
				if (s.getValue(BLOCKSTATE) == 4)
					return 0;
				if (s.getValue(BLOCKSTATE) == 5)
					return 0;
				if (s.getValue(BLOCKSTATE) == 6)
					return 0;
				if (s.getValue(BLOCKSTATE) == 7)
					return 0;
				if (s.getValue(BLOCKSTATE) == 8)
					return 0;
				if (s.getValue(BLOCKSTATE) == 9)
					return 0;
				if (s.getValue(BLOCKSTATE) == 10)
					return 0;
				if (s.getValue(BLOCKSTATE) == 11)
					return 0;
				if (s.getValue(BLOCKSTATE) == 12)
					return 0;
				if (s.getValue(BLOCKSTATE) == 13)
					return 0;
				if (s.getValue(BLOCKSTATE) == 14)
					return 0;
				if (s.getValue(BLOCKSTATE) == 15)
					return 0;
				return 0;
			}
		}.getLightLevel())).noOcclusion().pushReaction(PushReaction.DESTROY).isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.IRON_XYLOPHONE));
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return state.getFluidState().isEmpty();
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
		return 0;
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		if (state.getValue(BLOCKSTATE) == 1) {
			return (SHAPE_1);
		}
		if (state.getValue(BLOCKSTATE) == 2) {
			return (SHAPE_2);
		}
		if (state.getValue(BLOCKSTATE) == 3) {
			return (SHAPE_3);
		}
		if (state.getValue(BLOCKSTATE) == 4) {
			return (SHAPE_4);
		}
		if (state.getValue(BLOCKSTATE) == 5) {
			return (SHAPE_5);
		}
		if (state.getValue(BLOCKSTATE) == 6) {
			return (SHAPE_6);
		}
		if (state.getValue(BLOCKSTATE) == 7) {
			return (SHAPE_7);
		}
		if (state.getValue(BLOCKSTATE) == 8) {
			return (SHAPE_8);
		}
		if (state.getValue(BLOCKSTATE) == 9) {
			return (SHAPE_9);
		}
		if (state.getValue(BLOCKSTATE) == 10) {
			return (SHAPE_10);
		}
		if (state.getValue(BLOCKSTATE) == 11) {
			return (SHAPE_11);
		}
		if (state.getValue(BLOCKSTATE) == 12) {
			return (SHAPE_12);
		}
		if (state.getValue(BLOCKSTATE) == 13) {
			return (SHAPE_13);
		}
		if (state.getValue(BLOCKSTATE) == 14) {
			return (SHAPE_14);
		}
		if (state.getValue(BLOCKSTATE) == 15) {
			return (SHAPE_15);
		}
		return (SHAPE);
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(WATERLOGGED, BLOCKSTATE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(WATERLOGGED, flag);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
		if (state.getValue(WATERLOGGED)) {
			world.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
		}
		return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
	}

	@Override
	public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
		return PathType.FENCE;
	}

	@Override
	public void onPlace(BlockState blockstate, Level world, BlockPos pos, BlockState oldState, boolean moving) {
		super.onPlace(blockstate, world, pos, oldState, moving);
		ChainFenceUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		ChainFenceUpdateProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
	}
}