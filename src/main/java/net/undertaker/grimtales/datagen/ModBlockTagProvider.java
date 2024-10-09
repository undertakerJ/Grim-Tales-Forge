package net.undertaker.grimtales.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.util.ModTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
  public ModBlockTagProvider(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, GrimTales.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(@NotNull HolderLookup.Provider provider) {

    tag(BlockTags.LOGS_THAT_BURN)
        .add(ModBlocks.VELESCIL_LOG.get())
        .add(ModBlocks.VELESCIL_WOOD.get())
        .add(ModBlocks.STRIPPED_VELESCIL_LOG.get())
        .add(ModBlocks.STRIPPED_VELESCIL_WOOD.get());

    tag(BlockTags.PLANKS).add(ModBlocks.VELESCIL_PLANKS.get());

    tag(ModTags.Blocks.NEEDS_ASTRALITE_TOOL)
        .add(ModBlocks.ADAMANTITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());

    tag(ModTags.Blocks.NEEDS_CEBBITE_TOOL).add(ModBlocks.ASTRALITE_BLOCK.get());

    tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(ModBlocks.FIRE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.WATER_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.EARTH_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.AIR_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get())
        .add(ModBlocks.CEBBITE_BLOCK.get())
        .add(ModBlocks.ASTRALITE_BLOCK.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get())
        .add(ModBlocks.ADAMANTITE_ORE.get())
            .add(ModBlocks.PEDESTAL_BLOCK.get());

    tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get());

    tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.PEDESTAL_BLOCK.get());

    tag(BlockTags.NEEDS_DIAMOND_TOOL)
        .add(ModBlocks.CEBBITE_BLOCK.get())
        .add(ModBlocks.FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.WATER_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.EARTH_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.AIR_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.FIRE_ESSENCE_CRYSTAL_ORE.get());

    tag(Tags.Blocks.ORES)
        .add(ModBlocks.WATER_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.EARTH_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.AIR_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.FIRE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get())
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get())
        .add(ModBlocks.ADAMANTITE_BLOCK.get());
  }
}
