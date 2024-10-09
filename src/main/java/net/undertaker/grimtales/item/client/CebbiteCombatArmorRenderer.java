package net.undertaker.grimtales.item.client;

import net.undertaker.grimtales.item.custom.cebbite.CebbiteCombatArmor;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CebbiteCombatArmorRenderer extends GeoArmorRenderer<CebbiteCombatArmor> {
    public CebbiteCombatArmorRenderer() {
    super(new CebbitCombatArmorModel());
    }
}
