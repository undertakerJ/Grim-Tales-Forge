package net.undertaker.grimtales.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.networking.ModNetworkPackets;
import net.undertaker.grimtales.networking.packet.AnvilS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(AnvilBlock.class)
public class ModAnvilOnLand {
  @Inject(method = "onLand", at = @At("HEAD"))
  protected void onLandMethod(
      Level pLevel,
      BlockPos pPos,
      BlockState pState,
      BlockState pReplaceableState,
      FallingBlockEntity pFallingBlock,
      CallbackInfo info) {

    BlockPos blockBelowPos = pPos.below();
    BlockState blockBelowState = pLevel.getBlockState(blockBelowPos);
    if (blockBelowState.is(ModBlocks.CEBBITE_BLOCK.get())) {
      List<ItemEntity> nearbyItems =
          pLevel.getEntitiesOfClass(ItemEntity.class, new AABB(pPos, pPos.offset(1, 1, 1)));
      Map<Item, Integer> requiredItems = new HashMap<>();
      requiredItems.put(ModItems.CEBBITE_INGOT.get(), 4);
      requiredItems.put(ModItems.HELL_ESSENCE.get(), 1);
      requiredItems.put(Items.NETHERITE_SCRAP, 4);

      Map<Item, Integer> itemCounts = new HashMap<>();
      for (Item item : requiredItems.keySet()) {
        itemCounts.put(item, 0);
      }

      for (ItemEntity itemEntity : nearbyItems) {
        ItemStack itemStack = itemEntity.getItem();
        Item item = itemStack.getItem();
        if (itemCounts.containsKey(item)) {
          itemCounts.put(item, itemCounts.get(item) + itemStack.getCount());
        }
      }

      int maxIngots = Integer.MAX_VALUE;
      for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
        Item item = entry.getKey();
        int requiredCount = entry.getValue();
        int availableCount = itemCounts.get(item);
        maxIngots = Math.min(maxIngots, availableCount / requiredCount);
      }

      if (maxIngots > 0) {
        for (Map.Entry<Item, Integer> entry : requiredItems.entrySet()) {
          Item item = entry.getKey();
          int requiredCountPerStar = entry.getValue();
          int totalRequiredCount = requiredCountPerStar * maxIngots;

          for (ItemEntity itemEntity : nearbyItems) {
            ItemStack itemStack = itemEntity.getItem();
            if (itemStack.getItem() == item && totalRequiredCount > 0) {
              int availableCount = itemStack.getCount();

              if (availableCount <= totalRequiredCount) {
                totalRequiredCount -= availableCount;
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
              } else {
                itemStack.shrink(totalRequiredCount);
                totalRequiredCount = 0;
              }
            }
          }
        }
        ServerPlayer player = (ServerPlayer) pLevel.getNearestPlayer(pFallingBlock, 20);
        if (player != null) {
          ModNetworkPackets.sendToPlayer(
                  new AnvilS2CPacket(pPos.getX(), pPos.getY(), pPos.getZ()), player);
        }
        ItemStack newItem = new ItemStack(ModItems.ASTRALITE_INGOT.get(), maxIngots);
        ItemEntity newItemEntity =
            new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), newItem);
        pLevel.addFreshEntity(newItemEntity);

        }
      }
    }
  }

