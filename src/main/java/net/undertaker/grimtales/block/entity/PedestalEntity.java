package net.undertaker.grimtales.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PedestalEntity extends BlockEntity {

  public ItemStack storedItem = ItemStack.EMPTY;

  public PedestalEntity(BlockPos pPos, BlockState pBlockState) {
    super(ModBlockEntities.PEDESTAL_ENTITY.get(), pPos, pBlockState);
  }


  public ItemStack getItem() {
    return storedItem.isEmpty() ? ItemStack.EMPTY : storedItem;
  }

  public boolean hasItem() {
    return !storedItem.isEmpty();
  }

  public void setItem(ItemStack item) {
    this.storedItem = item.isEmpty() ? ItemStack.EMPTY : item;
    markUpdated();
    if(level != null){
      this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

  }



  @Override
  public void load(CompoundTag tag) {
    super.load(tag);
    this.storedItem = ItemStack.of(tag.getCompound("StoredItem"));
  }

  @Override
  public void saveAdditional(CompoundTag tag) {
    if (!storedItem.isEmpty()) {
      tag.put("StoredItem", storedItem.save(new CompoundTag()));
    }
    super.saveAdditional(tag);
  }

  @Nullable
  @Override
  public Packet<ClientGamePacketListener> getUpdatePacket() {
    return ClientboundBlockEntityDataPacket.create(this);
  }

  @Override
  public CompoundTag getUpdateTag() {
    return saveWithoutMetadata();
  }

  @Override
  public void handleUpdateTag(CompoundTag tag) {
    load(tag);
  }


  @Override
  public void invalidateCaps() {
    super.invalidateCaps();
    CompoundTag tag = new CompoundTag();
    saveAdditional(tag);
  }

  public void markUpdated() {
    setChanged();
    if (level != null) {
      level.markAndNotifyBlock(getBlockPos(), level.getChunkAt(getBlockPos()), getBlockState(), getBlockState(), 3,1);
    }
  }
}
