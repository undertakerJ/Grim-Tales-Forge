package net.undertaker.grimtales.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrimTales.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GRIMTALES_TAB =
            CREATIVE_MODE_TABS.register("grimtales_tab",
                    () -> CreativeModeTab.builder()
                            .icon(() -> new ItemStack(ModItems.RAW_CEBBITE_ORE.get()))
                            .title(Component.translatable("creativetab.grimtales_tab"))
                            .displayItems((itemDisplayParameters, output) -> {
                                //items
                                output.accept(ModItems.RAW_CEBBITE_ORE.get());
                                output.accept(ModItems.CEBBITE_INGOT.get());
                                output.accept(ModItems.CEBBITE_PICKAXE.get());
                                //blocks
                                output.accept(ModBlocks.CEBBITE_ORE.get());
                                output.accept(ModBlocks.DEEPSLATE_CEBBITE_ORE.get());
                                output.accept(ModBlocks.SCULK_CEBBITE_ORE.get());
                                output.accept(ModBlocks.CEBBITE_BLOCK.get());
                            })
                            .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
