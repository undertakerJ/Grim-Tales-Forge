package net.undertaker.grimtales.item.custom.cebbite;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.item.ModToolTiers;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class CebbiteAxeItem extends AxeItem {
  public CebbiteAxeItem() {
    super(ModToolTiers.CEBBITE, 5, -3.1f, new Properties().rarity(Rarity.RARE));
  }

  @Override
  public void appendHoverText(
      ItemStack pStack,
      @Nullable Level pLevel,
      List<Component> pTooltipComponents,
      TooltipFlag pIsAdvanced) {
    if (Screen.hasShiftDown()) {
      pTooltipComponents.add(
          Component.translatable("tooltip.cebbite_axe1").withStyle(ChatFormatting.GRAY));
      pTooltipComponents.add(
          Component.translatable("tooltip.cebbite_axe2").withStyle(ChatFormatting.GRAY));
      pTooltipComponents.add(
          Component.translatable("tooltip.cebbite_axe3").withStyle(ChatFormatting.GRAY));
    } else {
      pTooltipComponents.add(
          Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
    }
    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
  }

  @SubscribeEvent
  public static void onBreak(BlockEvent.BreakEvent event) {
    BlockPos blockPos = event.getPos();
    Player player = event.getPlayer();
    if (!player.level().isClientSide
        && !player.isCreative()
        && player.isShiftKeyDown()
        && player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.CEBBITE_AXE.get())) {
      breakNearbyWood(player, blockPos);
    }
    if (player.getRandom().nextFloat() <= 0.01f
        && player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.CEBBITE_AXE.get())) {
      player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * 20, 0));
      player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15 * 20, 0));
      player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15 * 20, 0));
    }
  }

  private static void breakNearbyWood(Player player, BlockPos blockPos) {
    BlockState blockState = player.level().getBlockState(blockPos);
    if (!blockState.is(BlockTags.LOGS)) return;
    player
        .getItemInHand(InteractionHand.MAIN_HAND)
        .hurtAndBreak(1, player, player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
    player.level().destroyBlock(blockPos, true);
    for (int x = -1; x <= 1; x++) {
      for (int y = 0; y <= 1; y++) {
        for (int z = -1; z <= 1; z++) {
          breakNearbyWood(player, blockPos.offset(x, y, z));
        }
      }
    }
  }
}
