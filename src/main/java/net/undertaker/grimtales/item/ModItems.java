package net.undertaker.grimtales.item;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.entity.ModEntities;
import net.undertaker.grimtales.fluid.ModFluids;
import net.undertaker.grimtales.item.custom.*;
import net.undertaker.grimtales.item.custom.adamantite.*;
import net.undertaker.grimtales.item.custom.astralite.*;
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
      ITEMS.register("astralite_ingot", () -> new Item(new Item.Properties().fireResistant()));
  public static final RegistryObject<Item> HELL_ESSENCE =
      ITEMS.register("hell_essence", () -> new Item(new Item.Properties().fireResistant()));
  public static final RegistryObject<Item> ASTRALITE_SMITHING_UPGRADE =
      ITEMS.register("astralite_smithing_upgrade", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> ADAMANTITE_INGOT =
      ITEMS.register(
          "adamantite_ingot",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
  public static final RegistryObject<Item> RAW_ADAMANTITE =
      ITEMS.register(
          "raw_adamantite",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));
 public static final RegistryObject<Item> DIAMOND_WORKTOOLS =
      ITEMS.register(
          "diamond_worktools",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).defaultDurability(10)));
 public static final RegistryObject<Item> CEBBITE_WORKTOOLS =
      ITEMS.register(
          "cebbite_worktools",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).defaultDurability(20)));
 public static final RegistryObject<Item> ASTRALITE_WORKTOOLS =
      ITEMS.register(
          "astralite_worktools",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).defaultDurability(25)));
 public static final RegistryObject<Item> ADAMANTITE_WORKTOOLS =
      ITEMS.register(
          "adamantite_worktools",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant().defaultDurability(40)));
public static final RegistryObject<Item> VELESCIL_WOOD_HANDLE =
      ITEMS.register(
          "velescil_wood_handle",
          () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

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
  public static final RegistryObject<Item> ASTRALITE_PICKAXE =
      ITEMS.register("astralite_pickaxe", AstralitePickaxeItem::new);
  public static final RegistryObject<Item> ASTRALITE_AXE =
      ITEMS.register("astralite_axe", AstraliteAxeItem::new);
  public static final RegistryObject<Item> ASTRALITE_SHOVEL =
      ITEMS.register("astralite_shovel", AstraliteShovelItem::new);

  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_SWORD =
      ITEMS.register("unawaken_adamantite_sword", UnawakenAdamantiteSwordItem::new);
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_HOE =
      ITEMS.register("unawaken_adamantite_hoe", UnawakenAdamantiteHoeItem::new);
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_PICKAXE =
      ITEMS.register("unawaken_adamantite_pickaxe", UnawakenAdamantitePickaxeItem::new);
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_AXE =
      ITEMS.register("unawaken_adamantite_axe", UnawakenAdamantiteAxeItem::new);
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_SHOVEL =
      ITEMS.register("unawaken_adamantite_shovel", UnawakenAdamantiteShovelItem::new);

  // ARMOR

  // CEBBITE
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
  // CEBBITE_COMBAT
  public static final RegistryObject<Item> CEBBITE_COMBAT_BOOTS =
      ITEMS.register(
          "cebbite_combat_boots",
          () ->
              new CebbiteCombatArmor(
                  ModArmorMaterial.CEBBITE_COMBAT,
                  ArmorItem.Type.BOOTS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> CEBBITE_COMBAT_LEGGINGS =
      ITEMS.register(
          "cebbite_combat_leggings",
          () ->
              new CebbiteCombatArmor(
                  ModArmorMaterial.CEBBITE_COMBAT,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> CEBBITE_COMBAT_CHESTPLATE =
      ITEMS.register(
          "cebbite_combat_chestplate",
          () ->
              new CebbiteCombatArmor(
                  ModArmorMaterial.CEBBITE_COMBAT,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> CEBBITE_COMBAT_HELMET =
      ITEMS.register(
          "cebbite_combat_helmet",
          () ->
              new CebbiteCombatArmor(
                  ModArmorMaterial.CEBBITE_COMBAT,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().rarity(Rarity.RARE)));

  // ASTRALITE
  public static final RegistryObject<Item> ASTRALITE_BOOTS =
      ITEMS.register(
          "astralite_boots",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.ASTRALITE,
                  ArmorItem.Type.BOOTS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> ASTRALITE_LEGGINGS =
      ITEMS.register(
          "astralite_leggings",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.ASTRALITE,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> ASTRALITE_CHESTPLATE =
      ITEMS.register(
          "astralite_chestplate",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.ASTRALITE,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> ASTRALITE_HELMET =
      ITEMS.register(
          "astralite_helmet",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.ASTRALITE,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().rarity(Rarity.RARE)));
  // ASTRALITE
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_BOOTS =
      ITEMS.register(
          "unawaken_adamantite_boots",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.UNAWAKEN_ADAMANTITE,
                  ArmorItem.Type.BOOTS,
                  new Item.Properties().rarity(Rarity.RARE).fireResistant()));
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_LEGGINGS =
      ITEMS.register(
          "unawaken_adamantite_leggings",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.UNAWAKEN_ADAMANTITE,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().rarity(Rarity.RARE).fireResistant()));
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_CHESTPLATE =
      ITEMS.register(
          "unawaken_adamantite_chestplate",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.UNAWAKEN_ADAMANTITE,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().rarity(Rarity.RARE).fireResistant()));
  public static final RegistryObject<Item> UNAWAKEN_ADAMANTITE_HELMET =
      ITEMS.register(
          "unawaken_adamantite_helmet",
          () ->
              new ModArmorItem(
                  ModArmorMaterial.UNAWAKEN_ADAMANTITE,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().rarity(Rarity.RARE).fireResistant()));
  // ADAMANTITE COMBAT
  public static final RegistryObject<Item> ADAMANTITE_COMBAT_HELMET =
      ITEMS.register(
          "adamantite_combat_helmet",
          () ->
              new AdamantiteCombatArmor(
                  ModArmorMaterial.ADAMANTITE_COMBAT,
                  ArmorItem.Type.HELMET,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> ADAMANTITE_COMBAT_CHESTPLATE =
      ITEMS.register(
          "adamantite_combat_chestplate",
          () ->
              new AdamantiteCombatArmor(
                  ModArmorMaterial.ADAMANTITE_COMBAT,
                  ArmorItem.Type.CHESTPLATE,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> ADAMANTITE_COMBAT_LEGGINGS =
      ITEMS.register(
          "adamantite_combat_leggings",
          () ->
              new AdamantiteCombatArmor(
                  ModArmorMaterial.ADAMANTITE_COMBAT,
                  ArmorItem.Type.LEGGINGS,
                  new Item.Properties().rarity(Rarity.RARE)));
  public static final RegistryObject<Item> ADAMANTITE_COMBAT_BOOTS =
      ITEMS.register(
          "adamantite_combat_boots",
          () ->
              new AdamantiteCombatArmor(
                  ModArmorMaterial.ADAMANTITE_COMBAT,
                  ArmorItem.Type.BOOTS,
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
  public static final RegistryObject<Item> GOD_WATER_BUCKET =
      ITEMS.register(
          "god_water_bucket",
          () ->
              new BucketItem(
                  ModFluids.SOURCE_GOD_WATER,
                  new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

  public static final RegistryObject<Item> ESSENCE_CRYSTAL =
      ITEMS.register("essence_crystal", EssenceCrystalItem::new);
  public static final RegistryObject<Item> CAPYBARA_SPAWN_EGG =
      ITEMS.register("capybara_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.CAPYBARA, 0xb8841d, 0x2b1700,
              new Item.Properties()));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
