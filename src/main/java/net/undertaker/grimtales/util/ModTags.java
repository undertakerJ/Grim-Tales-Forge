package net.undertaker.grimtales.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.undertaker.grimtales.GrimTales;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_CEBBITE_TOOL = tag("needs_cebbite_tool");
        public static final TagKey<Block> CEBBITE_ORES = tag("cebbite_ores");
public static final TagKey<Block> DIRT_LIKE = tag("dirt_like");

        private static TagKey<Block> tag(String key){
            return BlockTags.create(new ResourceLocation(GrimTales.MOD_ID, key));
        }
    }
    public static class Items{
        private static TagKey<Item> tag(String key){
            return ItemTags.create(new ResourceLocation(GrimTales.MOD_ID, key));
        }
    }
}
