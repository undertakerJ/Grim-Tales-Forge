package net.undertaker.grimtales.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.entity.custom.Capybara;

public class CapybaraRenderer extends MobRenderer<Capybara, CapybaraModel<Capybara>> {

  public CapybaraRenderer(EntityRendererProvider.Context pContext) {
    super(pContext, new CapybaraModel<>(pContext.bakeLayer(ModModelLayers.CAPYBARA_LAYER)), 0.5f);
  }

  @Override
  public ResourceLocation getTextureLocation(Capybara capybara) {
    return new ResourceLocation(GrimTales.MOD_ID, "textures/entity/capybara.png");
  }

  @Override
  public void render(
      Capybara pEntity,
      float pEntityYaw,
      float pPartialTicks,
      PoseStack pPoseStack,
      MultiBufferSource pBuffer,
      int pPackedLight) {
      if(pEntity.isBaby()){
        pPoseStack.scale(0.5f,0.5f,0.5f);
      }


    super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
  }
}
