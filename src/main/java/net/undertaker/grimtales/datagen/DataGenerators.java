package net.undertaker.grimtales.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.RegistriesDatapackGenerator;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.util.ModDamageTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import static net.undertaker.grimtales.datagen.ModWorldGenProvider.BUILDER;

@Mod.EventBusSubscriber(modid = GrimTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper helper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
    generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

    generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, helper));
    generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, helper));

    ModBlockTagProvider blockTagProvider =
        generator.addProvider(
            event.includeServer(), new ModBlockTagProvider(packOutput, lookupProvider, helper));
    generator.addProvider(
        event.includeServer(),
        new ModItemTagProvider(
            packOutput, lookupProvider, blockTagProvider.contentsGetter(), helper));
    generator.addProvider(
        event.includeServer(), new ModWorldGenProvider(packOutput, lookupProvider));
  }
}
