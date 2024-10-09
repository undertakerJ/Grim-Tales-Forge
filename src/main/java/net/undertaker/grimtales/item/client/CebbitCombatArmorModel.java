package net.undertaker.grimtales.item.client;

import net.minecraft.resources.ResourceLocation;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.custom.cebbite.CebbiteCombatArmor;
import software.bernie.geckolib.model.GeoModel;

public class CebbitCombatArmorModel extends GeoModel<CebbiteCombatArmor> {
    @Override
    public ResourceLocation getModelResource(CebbiteCombatArmor cebbiteCombatArmor) {
        return new ResourceLocation(GrimTales.MOD_ID,"geo/cebbite_combat_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CebbiteCombatArmor cebbiteCombatArmor) {
        return new ResourceLocation(GrimTales.MOD_ID, "textures/armor/cebbite_combat_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CebbiteCombatArmor cebbiteCombatArmor) {
        return new ResourceLocation(GrimTales.MOD_ID,"animations/idle.animation.json");
    }
}
