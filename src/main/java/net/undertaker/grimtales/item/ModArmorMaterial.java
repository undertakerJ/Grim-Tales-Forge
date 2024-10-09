package net.undertaker.grimtales.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;

import java.util.function.Supplier;

public enum ModArmorMaterial implements ArmorMaterial {
  CEBBITE(
      "cebbite",
      30,
      new int[] {3, 7, 6, 3},
      25,
      SoundEvents.ARMOR_EQUIP_IRON,
      1f,
      0.1f,
      () -> Ingredient.of(ModItems.CEBBITE_INGOT.get())),
  CEBBITE_COMBAT(
      "cebbite_combat",
      40,
      new int[] {4, 8, 7, 4},
      35,
      SoundEvents.ARMOR_EQUIP_IRON,
      1.5f,
      0.2f,
      () -> Ingredient.of(ModBlocks.CEBBITE_BLOCK.get())),
  ASTRALITE(
      "astralite",
      20,
      new int[] {4, 8, 7, 4},
      30,
      SoundEvents.ARMOR_EQUIP_IRON,
      1.25f,
      0.1f,
      () -> Ingredient.of(ModItems.ASTRALITE_INGOT.get())),
  ASTRALITE_COMBAT(
      "astralite_combat",
      25,
      new int[] {4, 8, 7, 4},
      25,
      SoundEvents.ARMOR_EQUIP_IRON,
      2f,
      0.1f,
      () -> Ingredient.of(ModBlocks.ASTRALITE_BLOCK.get())),
  UNAWAKEN_ADAMANTITE(
      "unawaken_adamantite",
      25,
      new int[] {3,8,6,3},
      30,
      SoundEvents.ARMOR_EQUIP_IRON,
      1f,
      0.1f,
      () -> Ingredient.of(ModItems.ADAMANTITE_INGOT.get())),
  ADAMANTITE_COMBAT(
      "adamantite_combat",
      60,
      new int[] {5, 10, 9, 5},
      45,
      SoundEvents.ARMOR_EQUIP_IRON,
      3f,
      0.25f,
      () -> Ingredient.of(ModBlocks.ADAMANTITE_BLOCK.get()));

  private final String name;
  private final int durabiltyMultiplier;
  private final int[] protectionAmount;
  private final int enchantmentValue;
  private final SoundEvent equipSound;
  private final float toughness;
  private final float knockbackResistance;
  private final Supplier<Ingredient> repairIngidient;

  private static int[] BASE_DURABILITY = {11, 16, 15, 13};

  ModArmorMaterial(
      String name,
      int durabiltyMultiplier,
      int[] protectionAmount,
      int enchantmentValue,
      SoundEvent equipSound,
      float toughness,
      float knockbackResistance,
      Supplier<Ingredient> repairIngidient) {
    this.name = name;
    this.durabiltyMultiplier = durabiltyMultiplier;
    this.protectionAmount = protectionAmount;
    this.enchantmentValue = enchantmentValue;
    this.equipSound = equipSound;
    this.toughness = toughness;
    this.knockbackResistance = knockbackResistance;
    this.repairIngidient = repairIngidient;
  }

  @Override
  public int getDurabilityForType(ArmorItem.Type type) {
    return BASE_DURABILITY[type.ordinal()] * this.durabiltyMultiplier;
  }

  @Override
  public int getDefenseForType(ArmorItem.Type type) {
    return this.protectionAmount[type.ordinal()];
  }

  @Override
  public int getEnchantmentValue() {
    return enchantmentValue;
  }

  @Override
  public SoundEvent getEquipSound() {
    return this.equipSound;
  }

  @Override
  public Ingredient getRepairIngredient() {
    return this.repairIngidient.get();
  }

  @Override
  public String getName() {
    return GrimTales.MOD_ID + ":" + this.name;
  }

  @Override
  public float getToughness() {
    return toughness;
  }

  @Override
  public float getKnockbackResistance() {
    return knockbackResistance;
  }
}
