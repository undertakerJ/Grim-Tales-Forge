package net.undertaker.grimtales.item.custom;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;

import static net.undertaker.grimtales.item.ModItems.ESSENCE_CRYSTAL;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = GrimTales.MOD_ID,  value = Dist.CLIENT)
public class ItemColorRegistry {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        ItemColors itemColors = event.getItemColors();
        itemColors.register(new ItemColor() {
            @Override
            public int getColor(ItemStack stack, int tintIndex) {
                String essenceType = EssenceCrystalItem.getEssenceType(stack);
                return switch (essenceType) {
                    case "fire" -> 0xFF4500; // оранжевый
                    case "water" -> 0x0077ff ; // синий
                    case "earth" -> 0x23ad49 ; // коричневый
                    case "air" -> 0xfeffc1; // белый
                    default -> 0xFFFFFF; // белый
                };
            }
        }, ESSENCE_CRYSTAL.get());
    }
}

