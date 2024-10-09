package net.undertaker.grimtales.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BushFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> OVERWORLD_CEBBITE_ORE_KEY = registerKey("cebbite_ore");
    public static final ResourceKey<ConfiguredFeature<?,?>> VELESCIL_TREE_KEY = registerKey("velescil");

    public static void bootsrap(BootstapContext<ConfiguredFeature<?,?>> context){
        RuleTest stonereplacable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslatereplacable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest sculkreplacable = new BlockMatchTest(Blocks.SCULK);

        List<OreConfiguration.TargetBlockState> overworldCebbiteOres = List.of(OreConfiguration.target(stonereplacable,
                        ModBlocks.CEBBITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslatereplacable, ModBlocks.DEEPSLATE_CEBBITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(sculkreplacable, ModBlocks.SCULK_CEBBITE_ORE.get().defaultBlockState()));

        TreeConfiguration.TreeConfigurationBuilder builder = new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.VELESCIL_LOG.get()), // Ствол из VELESCIL_LOG
                new ForkingTrunkPlacer(7, 5, 3), // Ветвистый ствол с ответвлениями
                BlockStateProvider.simple(ModBlocks.VELESCIL_LEAVES.get()), // Листва
                new BushFoliagePlacer(ConstantInt.of(3), ConstantInt.of(1), 5), // Куполообразная форма листвы
                new TwoLayersFeatureSize(2, 0, 3) // Размер дерева
        );

        register(context, VELESCIL_TREE_KEY, Feature.TREE, builder.build());

        register(context, OVERWORLD_CEBBITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldCebbiteOres, 4));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(GrimTales.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
