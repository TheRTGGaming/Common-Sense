package net.mcreator.commonsenseneo.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class VerticalChainBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final EnumProperty<AttachFace> FACE = FaceAttachedHorizontalDirectionalBlock.FACE;
	private static final VoxelShape SHAPE_NORTH_FLOOR = Shapes.or(box(6, 0, 0, 10, 16, 8), box(6, 0, 0, 10, 8, 16));
	private static final VoxelShape SHAPE_NORTH_WALL = Shapes.or(box(6, 0, 0, 10, 8, 16), box(6, 0, 8, 10, 16, 16));
	private static final VoxelShape SHAPE_NORTH_CEILING = Shapes.or(box(6, 0, 0, 10, 16, 8), box(6, 8, 0, 10, 16, 16));
	private static final VoxelShape SHAPE_SOUTH_FLOOR = Shapes.or(box(6, 0, 8, 10, 16, 16), box(6, 0, 0, 10, 8, 16));
	private static final VoxelShape SHAPE_SOUTH_WALL = Shapes.or(box(6, 0, 0, 10, 8, 16), box(6, 0, 0, 10, 16, 8));
	private static final VoxelShape SHAPE_SOUTH_CEILING = Shapes.or(box(6, 0, 8, 10, 16, 16), box(6, 8, 0, 10, 16, 16));
	private static final VoxelShape SHAPE_EAST_FLOOR = Shapes.or(box(8, 0, 6, 16, 16, 10), box(0, 0, 6, 16, 8, 10));
	private static final VoxelShape SHAPE_EAST_WALL = Shapes.or(box(0, 0, 6, 16, 8, 10), box(0, 0, 6, 8, 16, 10));
	private static final VoxelShape SHAPE_EAST_CEILING = Shapes.or(box(8, 0, 6, 16, 16, 10), box(0, 8, 6, 16, 16, 10));
	private static final VoxelShape SHAPE_WEST_FLOOR = Shapes.or(box(0, 0, 6, 8, 16, 10), box(0, 0, 6, 16, 8, 10));
	private static final VoxelShape SHAPE_WEST_WALL = Shapes.or(box(0, 0, 6, 16, 8, 10), box(8, 0, 6, 16, 16, 10));
	private static final VoxelShape SHAPE_WEST_CEILING = Shapes.or(box(0, 0, 6, 8, 16, 10), box(0, 8, 6, 16, 16, 10));

	public VerticalChainBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.CHAIN).strength(1f, 10f).requiresCorrectToolForDrops().noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.WALL));
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
		return true;
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
		builder.add(FACING, FACE);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACE, faceForDirection(context.getNearestLookingDirection())).setValue(FACING, context.getHorizontalDirection().getOpposite());
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
}