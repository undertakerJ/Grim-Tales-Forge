package net.undertaker.grimtales.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.effect.ModEffects;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.item.ModToolTiers;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CebbiteSwordItem extends SwordItem {

    public CebbiteSwordItem() {
        super(ModToolTiers.CEBBITE, 4, -2.4f, new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.cebbite_sword1").withStyle(ChatFormatting.GRAY));
            pTooltipComponents.add(Component.translatable("tooltip.cebbite_sword2").withStyle(ChatFormatting.GRAY));
            pTooltipComponents.add(Component.translatable("tooltip.cebbite_sword3").withStyle(ChatFormatting.GRAY));
        }else {
            pTooltipComponents.add(Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            float randomNumber = player.getRandom().nextFloat();
            if (randomNumber <= 0.005f) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * 20, 0));
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 15 * 20, 0));
                player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 15 * 20, 0));
            }
            if (randomNumber < 0.33f) {
                livingEntity.addEffect(new MobEffectInstance(ModEffects.ENTROPY_EFFECT.get(), 4 * 20, 0));
            }


        }
        return super.onLeftClickEntity(stack, player, entity);
    }


}
