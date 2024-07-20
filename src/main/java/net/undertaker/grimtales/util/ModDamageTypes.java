package net.undertaker.grimtales.util;

import net.minecraft.commands.arguments.ResourceKeyArgument;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;

public class ModDamageTypes {
  public static final DeferredRegister<DamageType> DAMAGE_TYPES =
      DeferredRegister.create(Registries.DAMAGE_TYPE, GrimTales.MOD_ID);
  public static final ResourceKey<DamageType> ENTROPY_KEY =
          ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(GrimTales.MOD_ID, "entropy"));

  public static final RegistryObject<DamageType> ENTROPY =
          DAMAGE_TYPES.register(
                  "entropy",
                  () -> new DamageType("entropy_effect", DamageScaling.ALWAYS, 1f, DamageEffects.HURT));
//
//  public static final DamageSource ENTROPY_SOURCE = new DamageSource(Holder.direct(ENTROPY.get()));

  public static void register(IEventBus eventBus) {
    DAMAGE_TYPES.register(eventBus);
  }
}
