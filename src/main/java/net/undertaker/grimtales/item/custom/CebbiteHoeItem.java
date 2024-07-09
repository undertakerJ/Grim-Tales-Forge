package net.undertaker.grimtales.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.undertaker.grimtales.item.ModToolTiers;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CebbiteHoeItem extends HoeItem {
  public CebbiteHoeItem() {
    super(ModToolTiers.CEBBITE, -4, -1, new Properties());
  }

  @Override
  public void appendHoverText(
      ItemStack pStack,
      @Nullable Level pLevel,
      List<Component> pTooltipComponents,
      TooltipFlag pIsAdvanced) {
    if (Screen.hasShiftDown()) {
      pTooltipComponents.add(
          Component.translatable("tooltip.cebbite_hoe1").withStyle(ChatFormatting.GRAY));
      pTooltipComponents.add(
          Component.translatable("tooltip.cebbite_hoe2").withStyle(ChatFormatting.GRAY));
      pTooltipComponents.add(
          Component.translatable("tooltip.cebbite_hoe3").withStyle(ChatFormatting.GRAY));
    } else {
      pTooltipComponents.add(
          Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
    }

    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
  }

  @Override
  public InteractionResult useOn(UseOnContext context) {
    BlockPos blockPos = context.getClickedPos();
    Player player = context.getPlayer();
    if (!player.level().isClientSide && player.isShiftKeyDown()) {
      breakNearbyGreen(player, blockPos);
    }
    if (player.getRandom().nextFloat() <= 0.1f) {
      player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * 20, 0));
      player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15 * 20, 0));
      player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15 * 20, 0));
    }
    player.getCooldowns().addCooldown(this, 5 * 20);
    return InteractionResult.SUCCESS;
  }

  private static void breakNearbyGreen(Player player, BlockPos blockPos) {
    player
        .getItemInHand(InteractionHand.MAIN_HAND)
        .hurtAndBreak(25, player, player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
    int searchRadius = 5;
    for (int x = blockPos.getX() - searchRadius; x <= blockPos.getX() + searchRadius; x++) {
      for (int y = blockPos.getY() - searchRadius; y < blockPos.getY() + searchRadius; y++) {
        for (int z = blockPos.getZ() - searchRadius; z <= blockPos.getZ() + searchRadius; z++) {
          BlockPos blockPos1 = new BlockPos(x, y, z);
          BlockState blockState1 = player.level().getBlockState(blockPos1);
          if (isGreen(blockState1)) {
            player.level().removeBlock(blockPos1, false);
          }
        }
      }
    }
  }

  private static boolean isGreen(BlockState state) {
    return state.is(BlockTags.LEAVES)
        || state.is(BlockTags.FLOWERS)
        || state.is(Blocks.GRASS)
        || state.is(Blocks.TALL_GRASS)
        || state.is(BlockTags.CROPS);
  }
}
