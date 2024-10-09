package net.undertaker.grimtales.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.entity.ModBlockEntities;
import net.undertaker.grimtales.entity.client.CapybaraModel;
import net.undertaker.grimtales.entity.client.ModModelLayers;
import net.undertaker.grimtales.util.client.PedestalRenderer;

@Mod.EventBusSubscriber(modid = GrimTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(ModModelLayers.CAPYBARA_LAYER, CapybaraModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.PEDESTAL_ENTITY.get(), PedestalRenderer::new);
    }
}
