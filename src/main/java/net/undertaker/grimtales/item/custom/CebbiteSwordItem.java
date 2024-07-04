package net.undertaker.grimtales.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.effect.ModEffects;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.item.ModToolTiers;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class CebbiteSwordItem extends SwordItem {

    public CebbiteSwordItem() {
        super(ModToolTiers.CEBBITE, 4, -2.4f, new Item.Properties().rarity(Rarity.RARE));
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            float randomNumber = player.getRandom().nextFloat();
            if (randomNumber < 0.33f) {
                livingEntity.addEffect(new MobEffectInstance(ModEffects.ENTROPY_EFFECT.get(), 4 * 20, 0));
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }


}
