package net.undertaker.grimtales.item.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.effect.ModEffects;
import net.undertaker.grimtales.item.ModItems;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class CebbiteSwordItem extends SwordItem {

    @SubscribeEvent
    public static void onLivingAttacked(LivingAttackEvent event) {
        LivingEntity attackedEntity = event.getEntity();
        if (!attackedEntity.level().isClientSide && !(attackedEntity == null) && event.getSource().is(DamageTypes.PLAYER_ATTACK)) {
            if (attackedEntity.getLastAttacker() instanceof ServerPlayer player) {
                if (player.getMainHandItem().is(ModItems.CEBBITE_SWORD.get())) {
                    attackedEntity.addEffect(new MobEffectInstance(ModEffects.ENTROPY_EFFECT.get(), 4 * 20, 0));
                }
            }

        }
    }


    public CebbiteSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
