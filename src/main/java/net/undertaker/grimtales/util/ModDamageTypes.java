package net.undertaker.grimtales.util;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.util.damagesources.GTDamageTypes;

public class ModDamageTypes{

  public static void bootstrap(BootstapContext<DamageType> context)
  {
    context.register(GTDamageTypes.ENTROPY_KEY, new DamageType(GrimTales.MOD_ID + "_entropy", 0.1f));
  }
}
