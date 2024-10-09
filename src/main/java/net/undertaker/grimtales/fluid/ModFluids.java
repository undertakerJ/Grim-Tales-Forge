package net.undertaker.grimtales.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, GrimTales.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_GOD_WATER = FLUIDS.register("soap_water_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.GOD_WATER_PROPERTIES));
public static final RegistryObject<FlowingFluid> FlOWING_GOD_WATER = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.GOD_WATER_PROPERTIES));

public static final ForgeFlowingFluid.Properties GOD_WATER_PROPERTIES = new ForgeFlowingFluid.Properties(
  ModFluidTypes.GOD_WATER_FLUID_TYPE, SOURCE_GOD_WATER, FlOWING_GOD_WATER).slopeFindDistance(1).levelDecreasePerBlock(2)
        .block(ModFluidBlocks.GOD_WATER_BLOCK).bucket(ModItems.GOD_WATER_BUCKET);


    public static void register(IEventBus eventBus){
        FLUIDS.register(eventBus);
    }
}
