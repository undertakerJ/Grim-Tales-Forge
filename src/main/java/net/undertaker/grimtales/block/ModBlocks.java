package net.undertaker.grimtales.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GrimTales.MOD_ID);
    public static final RegistryObject<Block> CEBBITE_ORE = registerBlock("cebbite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(25f).explosionResistance(1200f)));
    public static final RegistryObject<Block> SCULK_CEBBITE_ORE = registerBlock("sculk_cebbite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(25f).explosionResistance(1200f)));
    public static final RegistryObject<Block> DEEPSLATE_CEBBITE_ORE = registerBlock("deepslate_cebbite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE).destroyTime(25f).explosionResistance(1200f)));
    public static final RegistryObject<Block> CEBBITE_BLOCK = registerBlock("cebbite_block",
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).destroyTime(20f).explosionResistance(1200f)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
