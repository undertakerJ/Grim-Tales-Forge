package net.undertaker.grimtales.util;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.entity.ModEntities;
import net.undertaker.grimtales.entity.custom.Capybara;

@Mod.EventBusSubscriber(modid = GrimTales.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){
         event.put(ModEntities.CAPYBARA.get(), Capybara.createAttributes().build());
    }
}
