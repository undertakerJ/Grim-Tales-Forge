package net.undertaker.grimtales.block.custom;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.block.entity.ModBlockEntities;
import net.undertaker.grimtales.block.entity.WorkstationEntity;
import org.jetbrains.annotations.Nullable;

public class WorkstationBlock extends HorizontalDirectionalBlock implements EntityBlock {
  public WorkstationBlock(Properties pProperties) {
    super(pProperties);
    this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
  }

  public static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 14, 16);

  @Override
  public VoxelShape getShape(
      BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    return SHAPE;
  }

  @Override
  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.MODEL;
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
      BlockPos neighborPos = pPos.relative(facing.getClockWise());
      BlockState neighborState = pLevel.getBlockState(neighborPos);
      if (neighborState.getBlock() instanceof WorkstationLeftSide) {
        pLevel.destroyBlock(neighborPos, false);
      }
      BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
      if (blockEntity instanceof WorkstationEntity) {
        ((WorkstationEntity) blockEntity).drops();
      }
    }
    super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
  }

  @Override
  public InteractionResult use(
      BlockState state,
      Level level,
      BlockPos pos,
      Player player,
      InteractionHand hand,
      BlockHitResult hit) {
    if (!level.isClientSide) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof WorkstationEntity) {
        NetworkHooks.openScreen((ServerPlayer) player, (WorkstationEntity) blockEntity, pos);
        return InteractionResult.CONSUME;
      }
    }
    return InteractionResult.sidedSuccess(level.isClientSide);
  }

  @javax.annotation.Nullable
  protected static <E extends BlockEntity, A extends BlockEntity>
      BlockEntityTicker<A> createTickerHelper(
          BlockEntityType<A> pServerType,
          BlockEntityType<E> pClientType,
          BlockEntityTicker<? super E> pTicker) {
    return pClientType == pServerType ? (BlockEntityTicker<A>) pTicker : null;
  }

  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
      Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
    if (pLevel.isClientSide()) {
      return null;
    }

    return createTickerHelper(
        pBlockEntityType,
        ModBlockEntities.WORKSTATION_BLOCK_ENTITY.get(),
        (level, blockPos, blockState, workstationEntity) ->
            workstationEntity.tick(level, blockPos, blockState));
  }


  @Override
  public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
    super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
    Direction facing = pState.getValue(FACING);
    BlockPos leftBlockPos = pPos.relative(facing.getClockWise());
    if (pLevel.isEmptyBlock(leftBlockPos)) {
      BlockState leftBlockState = ModBlocks.WORKSTATION_BLOCK_LEFT.get().defaultBlockState().setValue(FACING, facing);
      pLevel.setBlock(leftBlockPos, leftBlockState, 3);
    }
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

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext pContext) {
    return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
  }
}
