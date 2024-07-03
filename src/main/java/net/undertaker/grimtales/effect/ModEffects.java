package net.undertaker.grimtales.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;

public class ModEffects extends MobEffects {
  public static final DeferredRegister<MobEffect> MOB_EFFECTS =
      DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GrimTales.MOD_ID);

  public static final RegistryObject<MobEffect> ENTROPY_EFFECT =
      MOB_EFFECTS.register("entropy", EntropyEffect::new);
  public static final RegistryObject<MobEffect> BLINKSTRIKE =
      MOB_EFFECTS.register("blinkstrike", BlinkStrikeEffect::new);
public static final RegistryObject<MobEffect> ARMORSHRED =
      MOB_EFFECTS.register("armorshred", ArmorShredEffect::new);

  public static void register(IEventBus eventBus) {
    MOB_EFFECTS.register(eventBus);
  }
}
