package net.undertaker.grimtales.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import org.joml.Vector3f;

public class ModFluidTypes {
  public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
  public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
  public static final ResourceLocation SOAP_OVERLAY_RL =
      new ResourceLocation(GrimTales.MOD_ID, "misc/in_soap_water");

  public static final DeferredRegister<FluidType> FLUID_TYPES =
      DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, GrimTales.MOD_ID);

  public static final RegistryObject<FluidType> GOD_WATER_FLUID_TYPE =
      register(
          "god_water_fluid",
          FluidType.Properties.create()
              .density(30)
              .canDrown(true)
              .lightLevel(3)
              .temperature(1000)
              .viscosity(10)
              .canHydrate(false)
              .rarity(Rarity.EPIC));

  private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
    return FLUID_TYPES.register(
        name,
        () ->
            new BaseFluidType(
                WATER_STILL_RL,
                WATER_FLOWING_RL,
                SOAP_OVERLAY_RL,
                0xA999ffdd,
                new Vector3f(153f / 255f, 255f / 255f, 221f / 255f),
                properties));
  }

  public static void register(IEventBus eventBus) {
    FLUID_TYPES.register(eventBus);
  }
}
