package net.undertaker.grimtales.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.entity.custom.Capybara;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GrimTales.MOD_ID);

public static final RegistryObject<EntityType<Capybara>> CAPYBARA =
        ENTITY_TYPES.register("capybara", () -> EntityType.Builder.of(Capybara::new, MobCategory.CREATURE)
                .sized(1f, 1f).build("capybara"));

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
