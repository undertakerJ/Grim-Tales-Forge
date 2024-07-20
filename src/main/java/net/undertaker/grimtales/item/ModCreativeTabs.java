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
import net.undertaker.grimtales.item.custom.EssenceCrystalItem;

public class ModCreativeTabs {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
      DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GrimTales.MOD_ID);

  public static final RegistryObject<CreativeModeTab> GRIMTALES_TAB =
      CREATIVE_MODE_TABS.register(
          "grimtales_tab",
          () ->
              CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ModItems.RAW_CEBBITE_ORE.get()))
                  .title(Component.translatable("creativetab.grimtales_tab"))
                  .displayItems(
                      (itemDisplayParameters, output) -> {
                        // items
                        ModItems.ITEMS.getEntries().stream()
                            .map(RegistryObject::get)
                            .forEach(output::accept);
                        for (String type : EssenceCrystalItem.ESSENCE_TYPES) {
                          ItemStack stack = new ItemStack(ModItems.ESSENCE_CRYSTAL.get());
                          EssenceCrystalItem.setEssence(stack, type, 10);
                          output.accept(stack);
                        }
                        // blocks
                        ModBlocks.BLOCKS.getEntries().stream()
                            .map(RegistryObject::get)
                            .forEach(output::accept);
                      })
                  .build());

  public static void register(IEventBus eventBus) {
    CREATIVE_MODE_TABS.register(eventBus);
  }
}
