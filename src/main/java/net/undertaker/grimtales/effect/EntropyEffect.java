package net.undertaker.grimtales.effect;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.undertaker.grimtales.util.ModDamageTypes;

public class EntropyEffect extends MobEffect {

  @Override
  public void applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
    if (!livingEntity.level().isClientSide()) {
    }
    super.applyEffectTick(livingEntity, pAmplifier);
  }

  @Override
  public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
    return true;
  }

  protected EntropyEffect() {
    super(MobEffectCategory.HARMFUL, 0x7422AB);
  }
}
