package net.undertaker.grimtales.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier CEBBITE = TierSortingRegistry.registerTier(
            new ForgeTier(5,2254,12f,4f, 30,
                    ModTags.Blocks.NEEDS_CEBBITE_TOOL, () -> Ingredient.of(ModItems.CEBBITE_INGOT.get())),
            new ResourceLocation(GrimTales.MOD_ID, "cebbite"), List.of(Tiers.NETHERITE), List.of());
}
