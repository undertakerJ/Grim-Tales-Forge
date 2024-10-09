package net.undertaker.grimtales.fluid;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;

public class ModFluidBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GrimTales.MOD_ID);

    public static final RegistryObject<LiquidBlock> GOD_WATER_BLOCK = BLOCKS.register("god_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_GOD_WATER, BlockBehaviour.Properties.copy(Blocks.LAVA)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
