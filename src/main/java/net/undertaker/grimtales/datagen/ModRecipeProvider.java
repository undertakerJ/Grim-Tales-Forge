package net.undertaker.grimtales.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }
    private static final List<ItemLike> CEBBITE_SMELTABLES = List.of(
            ModItems.RAW_CEBBITE_ORE.get(),
            ModBlocks.CEBBITE_ORE.get(),
            ModBlocks.SCULK_CEBBITE_ORE.get(),
            ModBlocks.DEEPSLATE_CEBBITE_ORE.get());
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        oreBlasting(consumer, CEBBITE_SMELTABLES, RecipeCategory.MISC, ModItems.CEBBITE_INGOT.get(), 0.25f, 100, "cebbite");
        oreSmelting(consumer, CEBBITE_SMELTABLES, RecipeCategory.MISC, ModItems.CEBBITE_INGOT.get(), 0.25f, 200, "cebbite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CEBBITE_BLOCK.get())
                .pattern("CCC")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.CEBBITE_INGOT.get())
                .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
                .save(consumer);
        shapedItemStack(RecipeCategory.TOOLS, ModItems.CEBBITE_PICKAXE.get().getDefaultInstance(), 0)
                .pattern("CCC")
                .pattern(" S ")
                .pattern(" S ")
                .define('C', ModItems.CEBBITE_INGOT.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.CEBBITE_INGOT.get()), has(ModItems.CEBBITE_INGOT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CEBBITE_INGOT.get(),9)
                .requires(ModBlocks.CEBBITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CEBBITE_BLOCK.get()), has(ModBlocks.CEBBITE_BLOCK.get()))
                .save(consumer);

    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer, GrimTales.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }


    public static ShapedRecipeBuilder shapedItemStack(RecipeCategory pCategory, ItemStack pResult, int pCount) {
    return new ShapedRecipeBuilder(pCategory, pResult.getItem(), pCount);
    }
}
