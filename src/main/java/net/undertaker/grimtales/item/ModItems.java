package net.undertaker.grimtales.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.custom.CebbiteAxeItem;
import net.undertaker.grimtales.item.custom.CebbitePickaxeItem;
import net.undertaker.grimtales.item.custom.CebbiteSwordItem;
import net.undertaker.grimtales.item.custom.AstralEdgeItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GrimTales.MOD_ID);

    public static final RegistryObject<Item> RAW_CEBBITE_ORE = ITEMS.register("raw_cebbite_ore",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CEBBITE_INGOT = ITEMS.register("cebbite_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CEBBITE_PICKAXE = ITEMS.register("cebbite_pickaxe",
            CebbitePickaxeItem::new);
    public static final RegistryObject<Item> CEBBITE_SWORD = ITEMS.register("cebbite_sword",
            CebbiteSwordItem::new);
    public static final RegistryObject<Item> CEBBITE_AXE = ITEMS.register("cebbite_axe",
            CebbiteAxeItem::new);
    public static final RegistryObject<Item> ASTRAL_EDGE = ITEMS.register("astral_edge",
            AstralEdgeItem::new);
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
