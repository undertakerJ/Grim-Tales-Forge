package net.undertaker.grimtales.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GrimTales.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerModel(ModItems.CEBBITE_INGOT, "generated");
        registerModel(ModItems.RAW_CEBBITE_ORE, "generated");
        registerModel(ModItems.CEBBITE_PICKAXE, "handheld");
        registerModel(ModItems.CEBBITE_SWORD, "handheld");
        registerModel(ModItems.CEBBITE_AXE, "handheld");
        registerModel(ModItems.ASTRAL_EDGE, "handheld");

    }
    private ItemModelBuilder registerModel(RegistryObject<Item> item, String parent) {
        ResourceLocation parentModel = new ResourceLocation("item/" + parent);
        ResourceLocation texture =
                new ResourceLocation(GrimTales.MOD_ID, "item/" + item.getId().getPath());
        return withExistingParent(item.getId().getPath(), parentModel).texture("layer0", texture);
    }
}

