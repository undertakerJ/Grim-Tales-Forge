package net.undertaker.grimtales.item.custom.cebbite;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
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

@Mod.EventBusSubscriber(modid = GrimTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CebbiteShovelItem extends ShovelItem {
  public CebbiteShovelItem() {
    super(ModToolTiers.CEBBITE, 1, -3, new Properties().rarity(Rarity.RARE));
  }

  @Override
  public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
    if(Screen.hasShiftDown()){
      pTooltipComponents.add(Component.translatable("tooltip.cebbite_shovel1").withStyle(ChatFormatting.GRAY));
      pTooltipComponents.add(Component.translatable("tooltip.cebbite_shovel2").withStyle(ChatFormatting.GRAY));
    }else {
      pTooltipComponents.add(Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
    }

    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
  }

  @SubscribeEvent
  public static void onBreak(BlockEvent.BreakEvent event) {
    BlockPos blockPos = event.getPos();
    Player player = event.getPlayer();
    if (!player.level().isClientSide && player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.CEBBITE_SHOVEL.get())) {
      if (player.getRandom().nextFloat() <= 0.001f) {
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * 20, 0));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15 * 20, 0));
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15 * 20, 0));
      }
      player.level().destroyBlock(blockPos, false);
    }
  }
  @Override
  public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
    return false;
  }
}
