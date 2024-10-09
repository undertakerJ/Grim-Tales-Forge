package net.undertaker.grimtales.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.block.entity.WorkstationEntity;
import org.jetbrains.annotations.Nullable;

public class WorkstationLeftSide extends HorizontalDirectionalBlock implements EntityBlock {

    public static final VoxelShape SHAPE = Block.box(0,0,0,16,14, 16);

    public WorkstationLeftSide(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }


    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer) {
            BlockPos rightBlockPos = pos.relative(state.getValue(FACING).getCounterClockWise());
            BlockEntity blockEntity = level.getBlockEntity(rightBlockPos);

            if (blockEntity instanceof WorkstationEntity) {
                NetworkHooks.openScreen((ServerPlayer) player, (WorkstationEntity) blockEntity, rightBlockPos);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WorkstationEntity(blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(FACING);
    }

    @Override
    public void onRemove(
            BlockState pState,
            Level pLevel,
            BlockPos pPos,
            BlockState pNewState,
            boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            Direction facing = pState.getValue(FACING);
            BlockPos neighborPos = pPos.relative(facing.getCounterClockWise());
            BlockState neighborState = pLevel.getBlockState(neighborPos);
            if (neighborState.getBlock() instanceof WorkstationBlock) {
                pLevel.destroyBlock(neighborPos, false);
            }
            BlockEntity blockEntity = pLevel.getBlockEntity(neighborPos);
            if (blockEntity instanceof WorkstationEntity) {
                ((WorkstationEntity) blockEntity).drops();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

}
