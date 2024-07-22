package net.undertaker.grimtales.util.damagesources;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.undertaker.grimtales.GrimTales;


public class GTDamageTypes{
  public static final ResourceKey<DamageType> ENTROPY_KEY = register("entropy");
  private static ResourceKey<DamageType> register(String name)
  {
    return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(GrimTales.MOD_ID, name));
  }

}
