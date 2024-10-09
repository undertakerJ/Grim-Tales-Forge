package net.undertaker.grimtales.item.custom.astralite;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
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
public class AstraliteShovelItem extends ShovelItem {
    public AstraliteShovelItem() {
        super(ModToolTiers.ASTRALITE, 0, -3, new Properties().rarity(Rarity.RARE).fireResistant());
    }
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.cebbite_shovel1").withStyle(ChatFormatting.GRAY));
        }else {
            pTooltipComponents.add(Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {
        BlockPos blockPos = event.getPos();
        Player player = event.getPlayer();
        if (!player.level().isClientSide && player.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.ASTRALITE_SHOVEL.get())) {
            event.setCanceled(true);
            player.level().setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
        }
    }
    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return false;
    }

}
