package net.mcreator.commonsenseneo.block;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.commonsenseneo.procedures.RightClickLightBlockOffProcedure;
import net.mcreator.commonsenseneo.procedures.LightBulbBlockOnRedstoneOffProcedure;

public class LightBulbBlockOnBlock extends Block implements SimpleWaterloggedBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final EnumProperty<AttachFace> FACE = FaceAttachedHorizontalDirectionalBlock.FACE;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE_NORTH_FLOOR = box(4, 0, 4, 12, 15, 12);
	private static final VoxelShape SHAPE_NORTH_WALL = box(4, 4, 1, 12, 12, 16);
	private static final VoxelShape SHAPE_NORTH_CEILING = box(4, 1, 4, 12, 16, 12);
	private static final VoxelShape SHAPE_SOUTH_FLOOR = box(4, 0, 4, 12, 15, 12);
	private static final VoxelShape SHAPE_SOUTH_WALL = box(4, 4, 0, 12, 12, 15);
	private static final VoxelShape SHAPE_SOUTH_CEILING = box(4, 1, 4, 12, 16, 12);
	private static final VoxelShape SHAPE_EAST_FLOOR = box(4, 0, 4, 12, 15, 12);
	private static final VoxelShape SHAPE_EAST_WALL = box(0, 4, 4, 15, 12, 12);
	private static final VoxelShape SHAPE_EAST_CEILING = box(4, 1, 4, 12, 16, 12);
	private static final VoxelShape SHAPE_WEST_FLOOR = box(4, 0, 4, 12, 15, 12);
	private static final VoxelShape SHAPE_WEST_WALL = box(1, 4, 4, 16, 12, 12);
	private static final VoxelShape SHAPE_WEST_CEILING = box(4, 1, 4, 12, 16, 12);

	public LightBulbBlockOnBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.GLASS).strength(1f, 10f).lightLevel(s -> 15).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).instrument(NoteBlockInstrument.HAT));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL).setValue(WATERLOGGED, false));
	}

	@Override
	public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.getBlock() == this ? true : super.skipRendering(state, adjacentBlockState, side);
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
		return (switch (state.getValue(FACING)) {
			case NORTH -> switch (state.getValue(FACE)) {
				case FLOOR -> SHAPE_NORTH_FLOOR;
				case WALL -> SHAPE_NORTH_WALL;
				case CEILING -> SHAPE_NORTH_CEILING;
			};
			case SOUTH -> switch (state.getValue(FACE)) {
				case FLOOR -> SHAPE_SOUTH_FLOOR;
				case WALL -> SHAPE_SOUTH_WALL;
				case CEILING -> SHAPE_SOUTH_CEILING;
			};
			case EAST -> switch (state.getValue(FACE)) {
				case FLOOR -> SHAPE_EAST_FLOOR;
				case WALL -> SHAPE_EAST_WALL;
				case CEILING -> SHAPE_EAST_CEILING;
			};
			case WEST -> switch (state.getValue(FACE)) {
				case FLOOR -> SHAPE_WEST_FLOOR;
				case WALL -> SHAPE_WEST_WALL;
				case CEILING -> SHAPE_WEST_CEILING;
			};
			default -> switch (state.getValue(FACE)) {
				case FLOOR -> SHAPE_NORTH_FLOOR;
				case WALL -> SHAPE_NORTH_WALL;
				case CEILING -> SHAPE_NORTH_CEILING;
			};
		});
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING, FACE, WATERLOGGED);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		return super.getStateForPlacement(context).setValue(FACE, faceForDirection(context.getNearestLookingDirection())).setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(WATERLOGGED, flag);
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}

	private AttachFace faceForDirection(Direction direction) {
		if (direction.getAxis() == Direction.Axis.Y)
			return direction == Direction.UP ? AttachFace.CEILING : AttachFace.FLOOR;
		else
			return AttachFace.WALL;
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
	public boolean canConnectRedstone(BlockState state, BlockGetter world, BlockPos pos, Direction side) {
		return true;
	}

	@Override
	public void neighborChanged(BlockState blockstate, Level world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
		super.neighborChanged(blockstate, world, pos, neighborBlock, fromPos, moving);
		if (world.getBestNeighborSignal(pos) > 0) {
		} else {
			LightBulbBlockOnRedstoneOffProcedure.execute(world, pos.getX(), pos.getY(), pos.getZ());
		}
	}

	@Override
	public InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
		super.useWithoutItem(blockstate, world, pos, entity, hit);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		double hitX = hit.getLocation().x;
		double hitY = hit.getLocation().y;
		double hitZ = hit.getLocation().z;
		Direction direction = hit.getDirection();
		RightClickLightBlockOffProcedure.execute(world, x, y, z);
		return InteractionResult.SUCCESS;
	}
}