package net.undertaker.grimtales.effect;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.undertaker.grimtales.util.damagesources.GTDamageTypes;

public class EntropyEffect extends MobEffect {

  @Override
  public void applyEffectTick(LivingEntity livingEntity, int pAmplifier) {
    Level level = livingEntity.level();
    if (!level.isClientSide()) {
      Holder<DamageType> entropyHolder =
          level
              .registryAccess()
              .registryOrThrow(Registries.DAMAGE_TYPE)
              .getHolderOrThrow(GTDamageTypes.ENTROPY_KEY);

      livingEntity.hurt(new DamageSource(entropyHolder), 1);
    }
    super.applyEffectTick(livingEntity, pAmplifier);
  }

  @Override
  public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
      int i;
      i = 25 >> pAmplifier;
      if (i > 0) {
        return pDuration % i == 0;
      } else return true;

  }

  protected EntropyEffect() {
    super(MobEffectCategory.HARMFUL, 0x7422AB);
  }
}
