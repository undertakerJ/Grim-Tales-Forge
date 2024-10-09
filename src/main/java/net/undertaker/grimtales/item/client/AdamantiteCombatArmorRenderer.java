package net.undertaker.grimtales.item.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.undertaker.grimtales.item.custom.adamantite.AdamantiteCombatArmor;
import net.undertaker.grimtales.item.custom.cebbite.CebbiteCombatArmor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AdamantiteCombatArmorRenderer extends GeoArmorRenderer<AdamantiteCombatArmor> {
  public AdamantiteCombatArmorRenderer() {
    super(new AdamantiteCombatArmorModel());
  }

  @Override
  public RenderType getRenderType(AdamantiteCombatArmor animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
    if (bufferSource != null) {
      return RenderType.entityTranslucent(texture);
    }
    return RenderType.armorCutoutNoCull(texture);
  }
}
