package net.undertaker.grimtales.effect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class EntropyEffect extends MobEffect {

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
        if (!livingEntity.level().isClientSide()) {
            livingEntity.hurt(livingEntity.damageSources().fellOutOfWorld(), 1);
        }
        super.applyEffectTick(livingEntity, pAmplifier);
    }
    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }



    protected EntropyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
}
