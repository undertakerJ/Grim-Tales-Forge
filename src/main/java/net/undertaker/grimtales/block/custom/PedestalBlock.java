package net.undertaker.grimtales.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.undertaker.grimtales.block.entity.PedestalEntity;
import org.jetbrains.annotations.Nullable;

public class PedestalBlock extends BaseEntityBlock {
  public static final VoxelShape SHAPE = Block.box(3, 0, 3, 13, 14, 13);

  public PedestalBlock(Properties pProperties) {
    super(pProperties);
  }

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
    if (!pState.is(pNewState.getBlock())) {
      BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
      if (blockEntity instanceof PedestalEntity && ((PedestalEntity) blockEntity).hasItem()) {
        pLevel.addFreshEntity(
            new ItemEntity(
                pLevel,
                pPos.getX(),
                pPos.getY(),
                pPos.getZ(),
                ((PedestalEntity) blockEntity).getItem()));
        ((PedestalEntity) blockEntity).storedItem = ItemStack.EMPTY;
        blockEntity.invalidateCaps();
        pLevel.removeBlockEntity(pPos);
      }
    }
    super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
  }

  @Override
  public InteractionResult use(
      BlockState pState,
      Level pLevel,
      BlockPos pPos,
      Player pPlayer,
      InteractionHand pHand,
      BlockHitResult pHit) {
    BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
    if (blockEntity instanceof PedestalEntity pedestalEntity) {

      ItemStack heldItem = pPlayer.getItemInHand(pHand);

      if (!pedestalEntity.hasItem() && !heldItem.isEmpty()) {
        pedestalEntity.setItem(new ItemStack(heldItem.getItem(), 1));
        if (!pLevel.isClientSide()) {
          pPlayer.setItemInHand(pHand, new ItemStack(heldItem.getItem(), heldItem.getCount() - 1));
        }
      } else if (pedestalEntity.hasItem()) {
        if (!pLevel.isClientSide()) {
          pPlayer.addItem(pedestalEntity.getItem());
        }
        ((PedestalEntity) blockEntity).storedItem = ItemStack.EMPTY;
      }
      pLevel.sendBlockUpdated(pPos, pState, pState, 3);
    }

    return InteractionResult.SUCCESS;
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new PedestalEntity(blockPos, blockState);
  }
}
