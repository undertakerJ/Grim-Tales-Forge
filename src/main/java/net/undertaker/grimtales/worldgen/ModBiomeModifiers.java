package net.undertaker.grimtales.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.undertaker.grimtales.GrimTales;

import java.util.Optional;

public class ModBiomeModifiers {

    public static final ResourceKey<BiomeModifier> ADD_CEBBITE_ORE = registerKey("add_cebbite_ore");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderSet.Direct<PlacedFeature> cebbitOreFeatures = HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.CEBBIT_ORE_PLACED));
        Optional<Holder.Reference<Biome>> deepDarkBiome = biomes.get(Biomes.DEEP_DARK);


        context.register(ADD_CEBBITE_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(deepDarkBiome.get()),
                cebbitOreFeatures,
                GenerationStep.Decoration.UNDERGROUND_ORES));

    }



    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(GrimTales.MOD_ID, name));
    }
}
