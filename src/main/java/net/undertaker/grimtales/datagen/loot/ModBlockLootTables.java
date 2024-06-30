package net.undertaker.grimtales.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

        this.add(ModBlocks.CEBBITE_ORE.get(), block ->
                createOreDrop(ModBlocks.CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
        this.add(ModBlocks.SCULK_CEBBITE_ORE.get(), block ->
                createOreDrop(ModBlocks.SCULK_CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
        this.add(ModBlocks.DEEPSLATE_CEBBITE_ORE.get(), block ->
                createOreDrop(ModBlocks.DEEPSLATE_CEBBITE_ORE.get(), ModItems.RAW_CEBBITE_ORE.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
