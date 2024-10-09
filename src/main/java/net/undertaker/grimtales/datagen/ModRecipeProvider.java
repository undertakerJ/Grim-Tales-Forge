package net.undertaker.grimtales.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.registries.ForgeRegistries;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;
import net.undertaker.grimtales.recipe.WorkstationRecipe;
import net.undertaker.grimtales.util.ModTags;

import javax.annotation.Nullable;
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
  protected void buildRecipes(Consumer<FinishedRecipe> recipeOutput) {
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
    shaped(RecipeCategory.MISC, ModBlocks.ASTRALITE_BLOCK.get())
        .pattern("CCC")
        .pattern("CCC")
        .pattern("CCC")
        .define('C', ModItems.ASTRALITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.MISC, ModBlocks.ADAMANTITE_BLOCK.get())
        .pattern("CCC")
        .pattern("CCC")
        .pattern("CCC")
        .define('C', ModItems.ADAMANTITE_INGOT.get())
        .unlockedBy(
            getHasName(ModItems.ADAMANTITE_INGOT.get()), has(ModItems.ADAMANTITE_INGOT.get()))
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
    shaped(RecipeCategory.COMBAT, ModItems.CEBBITE_HELMET.get())
        .pattern("CCC")
        .pattern("C C")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.CEBBITE_CHESTPLATE.get())
        .pattern("C C")
        .pattern("CCC")
        .pattern("CCC")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.CEBBITE_LEGGINGS.get())
        .pattern("CCC")
        .pattern("C C")
        .pattern("C C")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.CEBBITE_BOOTS.get())
        .pattern("C C")
        .pattern("C C")
        .define('C', ModItems.CEBBITE_INGOT.get())
        .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.UNAWAKEN_ADAMANTITE_HELMET.get())
        .pattern("CCC")
        .pattern("C C")
        .define('C', ModItems.ADAMANTITE_INGOT.get())
        .unlockedBy(
            getHasName(ModItems.ADAMANTITE_INGOT.get()), has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.UNAWAKEN_ADAMANTITE_CHESTPLATE.get())
        .pattern("C C")
        .pattern("CCC")
        .pattern("CCC")
        .define('C', ModItems.ADAMANTITE_INGOT.get())
        .unlockedBy(
            getHasName(ModItems.ADAMANTITE_INGOT.get()), has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.UNAWAKEN_ADAMANTITE_LEGGINGS.get())
        .pattern("CCC")
        .pattern("C C")
        .pattern("C C")
        .define('C', ModItems.ADAMANTITE_INGOT.get())
        .unlockedBy(
            getHasName(ModItems.ADAMANTITE_INGOT.get()), has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    shaped(RecipeCategory.COMBAT, ModItems.UNAWAKEN_ADAMANTITE_BOOTS.get())
        .pattern("C C")
        .pattern("C C")
        .define('C', ModItems.ADAMANTITE_INGOT.get())
        .unlockedBy(
            getHasName(ModItems.ADAMANTITE_INGOT.get()), has(ModItems.ADAMANTITE_INGOT.get()))
        .save(recipeOutput);
    WorkstationRecipeBuilder.build(
        recipeOutput,
        new ItemStack(ModItems.VELESCIL_WOOD_HANDLE.get()),
        "velescil_wood_handle_from_velescil_log_and_tool",
        Ingredient.of(ModBlocks.VELESCIL_LOG.get()),
        Ingredient.of(ModItems.ADAMANTITE_WORKTOOLS.get()));

    astraliteSmithing(
        recipeOutput,
        ModItems.CEBBITE_PICKAXE.get(),
        RecipeCategory.TOOLS,
        ModItems.ASTRALITE_PICKAXE.get());

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CEBBITE_INGOT.get(), 9)
        .requires(ModBlocks.CEBBITE_BLOCK.get())
        .unlockedBy(getHasName(ModBlocks.CEBBITE_BLOCK.get()), has(ModBlocks.CEBBITE_BLOCK.get()))
        .save(recipeOutput);

    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ASTRALITE_INGOT.get(), 9)
        .requires(ModBlocks.ASTRALITE_BLOCK.get())
        .unlockedBy(
            getHasName(ModBlocks.ASTRALITE_BLOCK.get()), has(ModBlocks.ASTRALITE_BLOCK.get()))
        .save(recipeOutput);
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ADAMANTITE_INGOT.get(), 9)
        .requires(ModBlocks.ADAMANTITE_BLOCK.get())
        .unlockedBy(
            getHasName(ModBlocks.ADAMANTITE_BLOCK.get()), has(ModBlocks.ADAMANTITE_BLOCK.get()))
        .save(recipeOutput);
  }

  protected static void astraliteSmithing(
      Consumer<FinishedRecipe> recipeOutput,
      Item pIngredientItem,
      RecipeCategory pCategory,
      Item pResultItem) {
    SmithingTransformRecipeBuilder.smithing(
            Ingredient.of(ModItems.ASTRALITE_SMITHING_UPGRADE.get()),
            Ingredient.of(pIngredientItem),
            Ingredient.of(ModItems.ASTRALITE_INGOT.get()),
            pCategory,
            pResultItem)
        .unlocks("has_astralite_ingot", has(ModItems.ASTRALITE_INGOT.get()))
        .save(recipeOutput, getItemName(pResultItem) + "_smithing");
  }

  protected static void oreSmelting(
      Consumer<FinishedRecipe> recipeOutput,
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
      Consumer<FinishedRecipe> recipeOutput,
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
      Consumer<FinishedRecipe> recipeOutput,
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

    public static class WorkstationRecipeBuilder {
        public static void build(
                Consumer<FinishedRecipe> consumer, ItemStack output, String recipeName, Ingredient... inputs) {
            consumer.accept(
                    new FinishedRecipe() {
                        @Override
                        public void serializeRecipeData(JsonObject json) {
                            JsonArray ingredients = new JsonArray();
                            for (Ingredient input : inputs) {
                                ingredients.add(input.toJson());
                            }
                            json.add("ingredients", ingredients);

                            JsonObject result = new JsonObject();
                            result.addProperty("item", ForgeRegistries.ITEMS.getKey(output.getItem()).toString());
                            json.add("output", result);
                        }

                        @Override
                        public ResourceLocation getId() {
                            return new ResourceLocation(GrimTales.MOD_ID, recipeName);
                        }

                        @Override
                        public RecipeSerializer<?> getType() {
                            return WorkstationRecipe.Serializer.INSTANCE;
                        }

                        @Nullable
                        @Override
                        public JsonObject serializeAdvancement() {
                            return null;
                        }

                        @Nullable
                        @Override
                        public ResourceLocation getAdvancementId() {
                            return null;
                        }
                    });
        }
    }
}
