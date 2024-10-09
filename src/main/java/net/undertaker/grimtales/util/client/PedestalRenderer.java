package net.undertaker.grimtales.util.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.phys.Vec3;
import net.undertaker.grimtales.block.entity.PedestalEntity;

  public class PedestalRenderer implements BlockEntityRenderer<PedestalEntity> {
    public PedestalRenderer(BlockEntityRendererProvider.Context context){

    }
    @Override
    public void render(
        PedestalEntity blockEntity,
        float partialTicks,
        PoseStack matrixStack,
        MultiBufferSource bufferSource,
        int combinedLight,
        int combinedOverlay) {
        ItemStack stack = blockEntity.getItem();
        if (stack.isEmpty()) {
            return;
        }

        matrixStack.pushPose();
        matrixStack.translate(0.5, 1.0, 0.5);
        matrixStack.scale(1f, 1f, 1f);

        long time = System.currentTimeMillis();
        float angle = (time % 7200L) / 20.0f;
        matrixStack.mulPose(Axis.YP.rotationDegrees(angle));

        Minecraft.getInstance()
                .getItemRenderer()
                .renderStatic(
                        stack,
                        ItemDisplayContext.GROUND,
                        getLightLevel(blockEntity.getLevel(), blockEntity.getBlockPos()),
                        OverlayTexture.NO_OVERLAY,
                        matrixStack,
                        bufferSource,
                        blockEntity.getLevel(),
                        1);
        matrixStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos blockPos) {
      int bLight = level.getBrightness(LightLayer.BLOCK, blockPos);
      int sLight = level.getBrightness(LightLayer.SKY, blockPos);
      return LightTexture.pack(bLight, sLight);
    }
  }
