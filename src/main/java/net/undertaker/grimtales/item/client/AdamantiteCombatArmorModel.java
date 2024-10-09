package net.undertaker.grimtales.item.client;

import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.custom.adamantite.AdamantiteCombatArmor;
import net.undertaker.grimtales.item.custom.cebbite.CebbiteCombatArmor;
import software.bernie.geckolib.model.GeoModel;

public class AdamantiteCombatArmorModel extends GeoModel<AdamantiteCombatArmor> {
    @Override
    public ResourceLocation getModelResource(AdamantiteCombatArmor adamantiteCombatArmor) {
        return new ResourceLocation(GrimTales.MOD_ID,"geo/adamantite_combat_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AdamantiteCombatArmor adamantiteCombatArmor) {
        return new ResourceLocation(GrimTales.MOD_ID, "textures/armor/adamantite_combat_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AdamantiteCombatArmor adamantiteCombatArmor) {
        return new ResourceLocation(GrimTales.MOD_ID,"animations/idle.animation.json");
    }
}
