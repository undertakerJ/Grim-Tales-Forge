package net.undertaker.grimtales.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.undertaker.grimtales.GrimTales;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GrimTales.MOD_ID);

    public static final RegistryObject<SoundEvent> TROLL_LAUGH =
            registerSoundEvents("troll_laugh");
    public static final RegistryObject<SoundEvent> NEGRI_PIDORASI =
            registerSoundEvents("negri_pidorasi");
    public static final RegistryObject<SoundEvent> VINE_BOOM =
            registerSoundEvents("vine_boom");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(GrimTales.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
