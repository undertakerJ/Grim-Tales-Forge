package net.undertaker.grimtales.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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
    tag(BlockTags.MINEABLE_WITH_PICKAXE)
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get())
        .add(ModBlocks.CEBBITE_BLOCK.get());
    tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get());
    tag(BlockTags.NEEDS_IRON_TOOL)
            .add(ModBlocks.CEBBITE_BLOCK.get());
    tag(Tags.Blocks.ORES)
        .add(ModBlocks.CEBBITE_ORE.get())
        .add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
        .add(ModBlocks.SCULK_CEBBITE_ORE.get());
  }
}
