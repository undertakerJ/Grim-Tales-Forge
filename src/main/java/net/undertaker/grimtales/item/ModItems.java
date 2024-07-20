package net.undertaker.grimtales.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.custom.*;
import net.undertaker.grimtales.item.custom.astralite.AstraliteHoeItem;
import net.undertaker.grimtales.item.custom.astralite.AstraliteSwordItem;
import net.undertaker.grimtales.item.custom.cebbite.*;
import net.undertaker.grimtales.sound.ModSounds;

public class ModItems {
  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, GrimTales.MOD_ID);
  // MATERIALS
  public static final RegistryObject<Item> RAW_CEBBITE_ORE =
      ITEMS.register("raw_cebbite_ore", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> CEBBITE_INGOT =
      ITEMS.register("cebbite_ingot", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> ASTRALITE_INGOT =
      ITEMS.register("astralite_ingot", () -> new Item(new Item.Properties()));

  // TOOLS
  public static final RegistryObject<Item> CEBBITE_PICKAXE =
      ITEMS.register("cebbite_pickaxe", CebbitePickaxeItem::new);
  public static final RegistryObject<Item> CEBBITE_SWORD =
      ITEMS.register("cebbite_sword", CebbiteSwordItem::new);
  public static final RegistryObject<Item> CEBBITE_AXE =
      ITEMS.register("cebbite_axe", CebbiteAxeItem::new);
  public static final RegistryObject<Item> CEBBITE_SHOVEL =
      ITEMS.register("cebbite_shovel", CebbiteShovelItem::new);
  public static final RegistryObject<Item> CEBBITE_HOE =
      ITEMS.register("cebbite_hoe", CebbiteHoeItem::new);
  public static final RegistryObject<Item> ASTRAL_EDGE =
      ITEMS.register("astral_edge", AstralEdgeItem::new);
  public static final RegistryObject<Item> ASTRALITE_SWORD =
      ITEMS.register("astralite_sword", AstraliteSwordItem::new);
  public static final RegistryObject<Item> ASTRALITE_HOE =
      ITEMS.register("astralite_hoe", AstraliteHoeItem::new);

  // ARMOR
  public static final RegistryObject<Item> CEBBITE_BOOTS =
      ITEMS.register(
          "cebbite_boots",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.CEBBITE,
                  ArmorItem.Type.BOOTS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> CEBBITE_LEGGINGS =
      ITEMS.register(
          "cebbite_leggings",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.CEBBITE,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> CEBBITE_CHESTPLATE =
      ITEMS.register(
          "cebbite_chestplate",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.CEBBITE,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> CEBBITE_HELMET =
      ITEMS.register(
          "cebbite_helmet",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.CEBBITE,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().rarity(Rarity.RARE)));

  // MISC
  public static final RegistryObject<Item> NEGRI_PIDORASI_MUSIC_DISK =
      ITEMS.register(
          "negri_pidorasi_music_disc",
          () ->
              new RecordItem(
                  6,
                  ModSounds.NEGRI_PIDORASI.get(),
                  new Item.Properties().stacksTo(1).rarity(Rarity.EPIC),
                  159));

  public static final RegistryObject<Item> ESSENCE_CRYSTAL =
      ITEMS.register("essence_crystal", EssenceCrystalItem::new);

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
