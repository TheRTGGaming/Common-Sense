package net.mcreator.commonsenseneo.block;

import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

public class QuickStepBlock extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	private static final VoxelShape SHAPE_NORTH = Shapes.or(box(1, 0.5, 0, 15, 1.5, 1), box(1, 2.5, 2, 15, 3.5, 3), box(1, 4.5, 4, 15, 5.5, 5), box(1, 6.5, 6, 15, 7.5, 7), box(1, 8.5, 8, 15, 9.5, 9), box(1, 10.5, 10, 15, 11.5, 11),
			box(1, 12.5, 12, 15, 13.5, 13), box(1, 14.5, 14, 15, 15.5, 15), box(0, 0, 0, 1, 1, 16), box(15, 0, 0, 16, 1, 16), box(0, 0, 15, 16, 16, 16));
	private static final VoxelShape SHAPE_SOUTH = Shapes.or(box(1, 0.5, 15, 15, 1.5, 16), box(1, 2.5, 13, 15, 3.5, 14), box(1, 4.5, 11, 15, 5.5, 12), box(1, 6.5, 9, 15, 7.5, 10), box(1, 8.5, 7, 15, 9.5, 8), box(1, 10.5, 5, 15, 11.5, 6),
			box(1, 12.5, 3, 15, 13.5, 4), box(1, 14.5, 1, 15, 15.5, 2), box(15, 0, 0, 16, 1, 16), box(0, 0, 0, 1, 1, 16), box(0, 0, 0, 16, 16, 1));
	private static final VoxelShape SHAPE_EAST = Shapes.or(box(15, 0.5, 1, 16, 1.5, 15), box(13, 2.5, 1, 14, 3.5, 15), box(11, 4.5, 1, 12, 5.5, 15), box(9, 6.5, 1, 10, 7.5, 15), box(7, 8.5, 1, 8, 9.5, 15), box(5, 10.5, 1, 6, 11.5, 15),
			box(3, 12.5, 1, 4, 13.5, 15), box(1, 14.5, 1, 2, 15.5, 15), box(0, 0, 0, 16, 1, 1), box(0, 0, 15, 16, 1, 16), box(0, 0, 0, 1, 16, 16));
	private static final VoxelShape SHAPE_WEST = Shapes.or(box(0, 0.5, 1, 1, 1.5, 15), box(2, 2.5, 1, 3, 3.5, 15), box(4, 4.5, 1, 5, 5.5, 15), box(6, 6.5, 1, 7, 7.5, 15), box(8, 8.5, 1, 9, 9.5, 15), box(10, 10.5, 1, 11, 11.5, 15),
			box(12, 12.5, 1, 13, 13.5, 15), box(14, 14.5, 1, 15, 15.5, 15), box(0, 0, 15, 16, 1, 16), box(0, 0, 0, 16, 1, 1), box(15, 0, 0, 16, 16, 16));

	public QuickStepBlock() {
		super(BlockBehaviour.Properties.of().sound(SoundType.SCAFFOLDING).strength(1f, 10f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false).ignitedByLava().instrument(NoteBlockInstrument.BASS));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
			case NORTH -> SHAPE_NORTH;
			case SOUTH -> SHAPE_SOUTH;
			case EAST -> SHAPE_EAST;
			case WEST -> SHAPE_WEST;
			default -> SHAPE_NORTH;
		});
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return super.getStateForPlacement(context).setValue(FACING, context.getHorizontalDirection().getOpposite());
	}

	public BlockState rotate(BlockState state, Rotation rot) {
		return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
	}

	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
}