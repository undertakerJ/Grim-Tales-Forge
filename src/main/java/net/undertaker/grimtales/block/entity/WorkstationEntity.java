package net.undertaker.grimtales.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.recipe.WorkstationRecipe;
import net.undertaker.grimtales.screen.WorkstationMenu;
import net.undertaker.grimtales.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class WorkstationEntity extends BlockEntity implements MenuProvider {
  private final ItemStackHandler itemStackHandler = new ItemStackHandler(3);
  private static final int INPUT_SLOT = 0;
  private static final int TOOL_SLOT = 1;
  private static final int OUTPUT_SLOT = 2;

  private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

  protected final ContainerData data;
  private int progress = 0;
  private int maxProgress = 120;

  public WorkstationEntity(BlockPos pPos, BlockState pBlockState) {
    super(ModBlockEntities.WORKSTATION_BLOCK_ENTITY.get(), pPos, pBlockState);
    this.data =
        new ContainerData() {
          @Override
          public int get(int i) {
            return switch (i) {
              case 0 -> WorkstationEntity.this.progress;
              case 1 -> WorkstationEntity.this.maxProgress;
              default -> 0;
            };
          }

          @Override
          public void set(int i, int i1) {
            switch (i) {
              case 0 -> WorkstationEntity.this.progress = i1;
              case 1 -> WorkstationEntity.this.maxProgress = i1;
            }
          }

          @Override
          public int getCount() {
            return 2;
          }
        };
  }

  @Override
  public @NotNull <T> LazyOptional<T> getCapability(
      @NotNull Capability<T> cap, @Nullable Direction side) {
    if (cap == ForgeCapabilities.ITEM_HANDLER) {
      return lazyItemHandler.cast();
    }
    return super.getCapability(cap, side);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
  }

  @Override
  public void invalidateCaps() {
    super.invalidateCaps();
    lazyItemHandler.invalidate();
  }

  public void drops() {
    SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
    for (int i = 0; i < itemStackHandler.getSlots(); i++) {
      inventory.setItem(i, itemStackHandler.getStackInSlot(i));
    }

    Containers.dropContents(this.level, this.worldPosition, inventory);
  }

  @Override
  public Component getDisplayName() {
    return Component.translatable("block.grimtales.workstation_block");
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
    return new WorkstationMenu(i, inventory, this, this.data);
  }

  @Override
  protected void saveAdditional(CompoundTag pTag) {
    pTag.put("inventory", itemStackHandler.serializeNBT());
    pTag.putInt("workstation_block.progress", progress);

    super.saveAdditional(pTag);
  }

  @Override
  public void load(CompoundTag pTag) {
    super.load(pTag);
    itemStackHandler.deserializeNBT(pTag.getCompound("inventory"));
    progress = pTag.getInt("workstation_block.progress");
  }

  public void tick(Level level, BlockPos blockPos, BlockState blockState) {
    if (hasRecipe()) {
      increaseCraftingProgress();
      setChanged(level, blockPos, blockState);

      if (hasProgressFinished()) {
        craftItem();
        resetProgress();
      }
    } else {
      resetProgress();
    }
  }

  private void resetProgress() {
    progress = 0;
  }

  private void craftItem() {

    Optional<WorkstationRecipe> recipe = getCurrentRecipe();
    ItemStack result = recipe.get().getResultItem(null);
    ItemStack tool = this.itemStackHandler.getStackInSlot(TOOL_SLOT);

    tool.setDamageValue(tool.getDamageValue() + 1);

    if (tool.getDamageValue() >= tool.getMaxDamage()) {
      this.itemStackHandler.extractItem(TOOL_SLOT, 1, false);
    }

    this.itemStackHandler.extractItem(INPUT_SLOT, 1, false);
    this.itemStackHandler.setStackInSlot(
        OUTPUT_SLOT,
        new ItemStack(
            result.getItem(),
            this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + result.getCount()));


  }

  private boolean hasRecipe() {
    Optional<WorkstationRecipe> recipe = getCurrentRecipe();

    if (recipe.isEmpty()) return false;

    ItemStack result = recipe.get().getResultItem(null);

    return canInserItemIntoOutputSlot(result.getItem())
        && canInsertAmountIntoOutputSlot(result.getCount())
        && haveToolInSlot();
  }

  private Optional<WorkstationRecipe> getCurrentRecipe() {
    SimpleContainer simpleContainer = new SimpleContainer(this.itemStackHandler.getSlots());
    for (int i = 0; i < itemStackHandler.getSlots(); i++) {
      simpleContainer.setItem(i, this.itemStackHandler.getStackInSlot(i));
    }
      return this.level
        .getRecipeManager()
        .getRecipeFor(WorkstationRecipe.Type.INSTANCE, simpleContainer, level);
  }

  private boolean haveToolInSlot() {
    Optional<WorkstationRecipe> recipe = getCurrentRecipe();
    if (recipe.isEmpty()) return false;
    List<ItemStack> tools = recipe.get().getTools();

    for (ItemStack tool : tools) {
      if (this.itemStackHandler.getStackInSlot(TOOL_SLOT).is(tool.getItem())) {
        return true;
      }
    }
    return false;
  }

  private boolean canInserItemIntoOutputSlot(Item item) {
    return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty()
        || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
  }

  private boolean canInsertAmountIntoOutputSlot(int count) {
    return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count
        <= this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
  }

  private boolean hasProgressFinished() {
    return progress >= maxProgress;
  }

  private void increaseCraftingProgress() {
    progress++;
  }
}
