package net.undertaker.grimtales.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.data.recipes.ShapedRecipeBuilder.shaped;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
  public ModRecipeProvider(PackOutput pOutput) {
    super(pOutput);
  }

  private static final List<ItemLike> CEBBITE_SMELTABLES =
      List.of(
          ModItems.RAW_CEBBITE_ORE.get(),
          ModBlocks.CEBBITE_ORE.get(),
          ModBlocks.SCULK_CEBBITE_ORE.get(),
          ModBlocks.DEEPSLATE_CEBBITE_ORE.get());

  @Override
  protected void buildRecipes(RecipeOutput recipeOutput) {
    oreBlasting(
        recipeOutput,
        CEBBITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.CEBBITE_INGOT.get(),
        0.25f,
        100,
        "cebbite");
    oreSmelting(
        recipeOutput,
        CEBBITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.CEBBITE_INGOT.get(),
        0.25f,
        200,
        "cebbite");

    shaped(RecipeCategory.MISC, ModBlocks.CEBBITE_BLOCK.get())
        .pattern("CCC")
        .pattern("CCC")
        .pattern("CCC")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_SWORD.get())
        .pattern("C")
        .pattern("C")
        .pattern("S")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .define('S', Items.STICK)
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_PICKAXE.get())
        .pattern("CCC")
        .pattern(" S ")
        .pattern(" S ")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .define('S', Items.STICK)
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_AXE.get())
        .pattern("CC")
        .pattern("CS")
        .pattern(" S")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .define('S', Items.STICK)
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_SHOVEL.get())
        .pattern("C")
        .pattern("S")
        .pattern("S")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .define('S', Items.STICK)
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_HOE.get())
        .pattern("CC")
        .pattern(" S")
        .pattern(" S")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .define('S', Items.STICK)
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_HELMET.get())
        .pattern("CCC")
        .pattern("C C")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_CHESTPLATE.get())
        .pattern("C C")
        .pattern("CCC")
        .pattern("CCC")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_LEGGINGS.get())
        .pattern("CCC")
        .pattern("C C")
        .pattern("C C")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.TOOLS, ModItems.CEBBITE_BOOTS.get())
        .pattern("C C")
        .pattern("C C")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    astraliteSmithing(
        recipeOutput,
        ModItems.CEBBITE_PICKAXE.get(),
        RecipeCategory.TOOLS,
        ModItems.ASTRALITE_PICKAXE.get());
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CEBBITE_INGOT.get(), 9)
        .requires(ModBlocks.CEBBITE_BLOCK.get())
        .unlockedBy(getHasName(ModBlocks.CEBBITE_BLOCK.get()), has(ModBlocks.CEBBITE_BLOCK.get()))
        .save(recipeOutput);
  }

  protected static void astraliteSmithing(
      RecipeOutput recipeOutput, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(new ItemLike[] {ModItems.ASTRALITE_SMITHING_UPGRADE.get()}),
            Ingredient.of(new ItemLike[] {pIngredientItem}),
            Ingredient.of(new ItemLike[] {ModItems.ASTRALITE_INGOT.get()}),
            pCategory,
            pResultItem)
        .unlocks("has_astralite_ingot", has((ItemLike) ModItems.ASTRALITE_INGOT.get()))
        .save(recipeOutput, getItemName(pResultItem) + "_smithing");
  }

  protected static void oreSmelting(
      RecipeOutput recipeOutput,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTIme,
      String pGroup) {
    oreCooking(
        recipeOutput,
        RecipeSerializer.SMELTING_RECIPE,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTIme,
        pGroup,
        "_from_smelting");
  }

  protected static void oreBlasting(
      RecipeOutput recipeOutput,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup) {
    oreCooking(
        recipeOutput,
        RecipeSerializer.BLASTING_RECIPE,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTime,
        pGroup,
        "_from_blasting");
  }

  protected static void oreCooking(
      RecipeOutput recipeOutput,
      RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup,
      String pRecipeName) {
    Iterator var9 = pIngredients.iterator();

    while (var9.hasNext()) {
      ItemLike itemlike = (ItemLike) var9.next();
      SimpleCookingRecipeBuilder.generic(
              Ingredient.of(new ItemLike[] {itemlike}),
              pCategory,
              pResult,
              pExperience,
              pCookingTime,
              pCookingSerializer)
          .group(pGroup)
          .unlockedBy(getHasName(itemlike), has(itemlike))
          .save(
              recipeOutput,
              GrimTales.MOD_ID
                  + ":"
                  + getItemName(pResult)
                  + pRecipeName
                  + "_"
                  + getItemName(itemlike));
    }
  }
}
