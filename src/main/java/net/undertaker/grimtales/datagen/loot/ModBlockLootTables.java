package net.undertaker.grimtales.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
  public ModBlockLootTables() {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags());
  }

  @Override
  protected void generate() {
    this.dropSelf(ModBlocks.CEBBITE_BLOCK.get());
    this.dropSelf(ModBlocks.ASTRALITE_BLOCK.get());
    this.dropSelf(ModBlocks.ALTAR_BLOCK.get());
    this.dropSelf(ModBlocks.VELESCIL_WOOD.get());
    this.dropSelf(ModBlocks.VELESCIL_LOG.get());
    this.dropSelf(ModBlocks.ADAMANTITE_BLOCK.get());
    this.dropSelf(ModBlocks.STRIPPED_VELESCIL_WOOD.get());
    this.dropSelf(ModBlocks.STRIPPED_VELESCIL_LOG.get());
    this.dropSelf(ModBlocks.VELESCIL_PLANKS.get());
    dropSelf(ModBlocks.WORKSTATION_BLOCK.get());
    dropSelf(ModBlocks.VELESCIL_SAPLING.get());

    this.add(
        ModBlocks.VELESCIL_LEAVES.get(),
        block ->
            createLeavesDrops(
                block, ModBlocks.VELESCIL_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
    this.add(
        ModBlocks.WORKSTATION_BLOCK_LEFT.get(),
        block -> createBannerDrop(ModBlocks.WORKSTATION_BLOCK.get()));
    this.add(
        ModBlocks.CEBBITE_ORE.get(),
        block -> createOreDrop(ModBlocks.CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
    this.add(
        ModBlocks.SCULK_CEBBITE_ORE.get(),
        block -> createOreDrop(ModBlocks.SCULK_CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
    this.add(
        ModBlocks.DEEPSLATE_CEBBITE_ORE.get(),
        block ->
            createOreDrop(ModBlocks.DEEPSLATE_CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
    this.add(
        ModBlocks.ADAMANTITE_ORE.get(),
        block -> createOreDrop(ModBlocks.ADAMANTITE_ORE.get(), ModItems.RAW_ADAMANTITE.get()));

    this.add(
        ModBlocks.FIRE_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.FIRE_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("fire"))));
      this.add(
        ModBlocks.AIR_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.AIR_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("air"))));
      this.add(
        ModBlocks.WATER_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.WATER_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("water"))));
      this.add(
        ModBlocks.EARTH_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.EARTH_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("earth"))));
      this.add(
        ModBlocks.FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("fire"))));
      this.add(
        ModBlocks.AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("air"))));
      this.add(
        ModBlocks.WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("water"))));
      this.add(
        ModBlocks.EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(),
        block ->
            createOreDrop(
                    ModBlocks.EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE.get(), (ModItems.ESSENCE_CRYSTAL.get()))
                .apply(SetNbtFunction.setTag(createEssenceNBT("earth"))));
    this.dropSelf(ModBlocks.PEDESTAL_BLOCK.get());
  }

  private CompoundTag createEssenceNBT(String essenceType) {
    CompoundTag nbt = new CompoundTag();
    nbt.putString("EssenceType", essenceType);
    return nbt;
  }

  @Override
  protected Iterable<Block> getKnownBlocks() {

    return ModBlocks.BLOCKS.getEntries().stream()
            .map(RegistryObject::get)

        ::iterator;
  }
}
