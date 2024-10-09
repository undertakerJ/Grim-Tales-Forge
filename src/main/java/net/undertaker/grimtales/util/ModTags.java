package net.undertaker.grimtales.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.effect.EntropyEffect;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_CEBBITE_TOOL = tag("needs_cebbite_tool");
        public static final TagKey<Block> NEEDS_ASTRALITE_TOOL = tag("needs_astralite_tool");
        public static final TagKey<Block> NEEDS_ADAMANTITE_TOOL = tag("needs_adamantite_tool");

        private static TagKey<Block> tag(String key){
            return BlockTags.create(new ResourceLocation(GrimTales.MOD_ID, key));
        }
    }
    public static class Items{
        public static final TagKey<Item> CEBBITE_TOOLS = tag("cebbite_tools");
        public static final TagKey<Item> ASTRALITE_TOOLS = tag("astralite_tools");
        public static final TagKey<Item> ADAMANTITE_TOOLS = tag("adamantite_tools");

        public static final TagKey<Item> WORKTOOLS = tag("worktools");

        private static TagKey<Item> tag(String key){
            return ItemTags.create(new ResourceLocation(GrimTales.MOD_ID, key));
        }
    }
}
