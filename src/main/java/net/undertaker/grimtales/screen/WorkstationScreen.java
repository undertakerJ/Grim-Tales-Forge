package net.undertaker.grimtales.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.undertaker.grimtales.GrimTales;

public class WorkstationScreen extends AbstractContainerScreen<WorkstationMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(GrimTales.MOD_ID, "textures/gui/workstation_gui.png");

    public WorkstationScreen(WorkstationMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
            super.init();
            this.inventoryLabelY = 10000;
            this.titleLabelY = 10000;

    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0f, 1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (width - imageWidth)/2;
        int y = (height - imageHeight)/2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);
    }

    private void renderProgressArrow(GuiGraphics guiGraphics, int x, int y) {
        if(menu.isCrafting()){
            guiGraphics.blit(TEXTURE, x+85, y+30, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
