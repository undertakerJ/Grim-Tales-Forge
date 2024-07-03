package net.undertaker.grimtales.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.undertaker.grimtales.GrimTales;

import java.util.Optional;

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

    protected EntropyEffect() {
        super(MobEffectCategory.HARMFUL,  0x7422AB);
    }
}
