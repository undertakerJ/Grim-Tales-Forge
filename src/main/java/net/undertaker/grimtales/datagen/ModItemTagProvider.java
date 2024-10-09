package net.undertaker.grimtales.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
  public ModItemTagProvider(
      PackOutput p_275343_,
      CompletableFuture<HolderLookup.Provider> p_275729_,
      CompletableFuture<TagLookup<Block>> p_275322_,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(p_275343_, p_275729_, p_275322_, GrimTales.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider provider) {
    tag(ItemTags.TRIMMABLE_ARMOR)
        .add(ModItems.CEBBITE_HELMET.get())
        .add(ModItems.CEBBITE_CHESTPLATE.get())
        .add(ModItems.CEBBITE_LEGGINGS.get())
        .add(ModItems.CEBBITE_BOOTS.get())
        .add(ModItems.ASTRALITE_BOOTS.get())
        .add(ModItems.ASTRALITE_LEGGINGS.get())
        .add(ModItems.ASTRALITE_CHESTPLATE.get())
        .add(ModItems.ASTRALITE_HELMET.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_BOOTS.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_LEGGINGS.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_CHESTPLATE.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_HELMET.get());

    tag(ItemTags.LOGS_THAT_BURN)
        .add(ModBlocks.VELESCIL_LOG.get().asItem())
        .add(ModBlocks.VELESCIL_WOOD.get().asItem())
        .add(ModBlocks.STRIPPED_VELESCIL_LOG.get().asItem())
        .add(ModBlocks.STRIPPED_VELESCIL_WOOD.get().asItem());

    tag(ItemTags.PLANKS).add(ModBlocks.VELESCIL_PLANKS.get().asItem());

    tag(ItemTags.MUSIC_DISCS).add(ModItems.NEGRI_PIDORASI_MUSIC_DISK.get());

    tag(ModTags.Items.WORKTOOLS)
        .add(ModItems.DIAMOND_WORKTOOLS.get())
        .add(ModItems.CEBBITE_WORKTOOLS.get())
        .add(ModItems.ASTRALITE_WORKTOOLS.get())
        .add(ModItems.ADAMANTITE_WORKTOOLS.get());

    tag(ModTags.Items.ASTRALITE_TOOLS)
        .add(ModItems.ASTRALITE_AXE.get())
        .add(ModItems.ASTRALITE_PICKAXE.get())
        .add(ModItems.ASTRALITE_SHOVEL.get())
        .add(ModItems.ASTRALITE_HOE.get());
    tag(ModTags.Items.CEBBITE_TOOLS)
        .add(ModItems.CEBBITE_PICKAXE.get())
        .add(ModItems.CEBBITE_AXE.get())
        .add(ModItems.CEBBITE_SHOVEL.get())
        .add(ModItems.CEBBITE_HOE.get());
    tag(ModTags.Items.ADAMANTITE_TOOLS)
        .add(ModItems.UNAWAKEN_ADAMANTITE_PICKAXE.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_AXE.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_SHOVEL.get())
        .add(ModItems.UNAWAKEN_ADAMANTITE_HOE.get());
  }
}
