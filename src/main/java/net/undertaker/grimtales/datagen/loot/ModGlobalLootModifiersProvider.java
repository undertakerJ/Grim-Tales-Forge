package net.undertaker.grimtales.datagen.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.lootmodifiers.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
  public ModGlobalLootModifiersProvider(PackOutput output) {
    super(output, GrimTales.MOD_ID);
  }

  @Override
  protected void start() {
    add(
        "hell_essence_from_wither_boss",
        new AddItemModifier(
            new LootItemCondition[] {
              LootTableIdCondition.builder(new ResourceLocation("entities/wither")).build()
            },
            new ItemStack(ModItems.HELL_ESSENCE.get())));
  add(
        "astralite_upgrade_from_warden",
        new AddItemModifier(
            new LootItemCondition[] {
              LootTableIdCondition.builder(new ResourceLocation("entities/warden")).build()
            },
            new ItemStack(ModItems.ASTRALITE_SMITHING_UPGRADE.get())));



  }

}
