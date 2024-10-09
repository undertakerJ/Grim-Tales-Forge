package net.undertaker.grimtales.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GrimTales.MOD_ID,exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
    blockWithItem(ModBlocks.CEBBITE_ORE);
    blockWithItem(ModBlocks.DEEPSLATE_CEBBITE_ORE);
    blockWithItem(ModBlocks.SCULK_CEBBITE_ORE);
    blockWithItem(ModBlocks.CEBBITE_BLOCK);
    blockWithItem(ModBlocks.ASTRALITE_BLOCK);
    blockWithItem(ModBlocks.ADAMANTITE_BLOCK);
    blockWithItem(ModBlocks.ADAMANTITE_ORE);
    blockWithItem(ModBlocks.VELESCIL_PLANKS);
    blockWithItem(ModBlocks.FIRE_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.WATER_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.AIR_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.EARTH_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.FIRE_DEEPSLATE_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.WATER_DEEPSLATE_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.AIR_DEEPSLATE_ESSENCE_CRYSTAL_ORE);
    blockWithItem(ModBlocks.EARTH_DEEPSLATE_ESSENCE_CRYSTAL_ORE);

    logBlock(((RotatedPillarBlock)ModBlocks.VELESCIL_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.VELESCIL_WOOD.get()), blockTexture(ModBlocks.VELESCIL_LOG.get()), blockTexture(ModBlocks.VELESCIL_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_VELESCIL_LOG.get()), blockTexture(ModBlocks.STRIPPED_VELESCIL_LOG.get()),
                new ResourceLocation(GrimTales.MOD_ID, "block/stripped_velescil_log_top"));
    axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_VELESCIL_WOOD.get()), blockTexture(ModBlocks.STRIPPED_VELESCIL_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_VELESCIL_LOG.get()));


    saplingBlock(ModBlocks.VELESCIL_SAPLING);

    blockItem(ModBlocks.VELESCIL_LOG);
    blockItem(ModBlocks.VELESCIL_WOOD);
    blockItem(ModBlocks.STRIPPED_VELESCIL_LOG);
    blockItem(ModBlocks.STRIPPED_VELESCIL_WOOD);



    leavesBlock(ModBlocks.VELESCIL_LEAVES);


    simpleBlockWithItem(ModBlocks.ALTAR_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/altar_block")));
    simpleBlockWithItem(ModBlocks.PEDESTAL_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/pedestal_block")));
    horizontalBlock(ModBlocks.WORKSTATION_BLOCK.get(), new ModelFile.UncheckedModelFile(modLoc("block/workstation_block")));
    horizontalBlock(ModBlocks.WORKSTATION_BLOCK_LEFT.get(), new ModelFile.UncheckedModelFile(modLoc("block/workstation_left")));
    }
    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }


    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(GrimTales.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
