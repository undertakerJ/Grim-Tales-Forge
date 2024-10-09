package net.undertaker.grimtales.effect;


import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class ModEffectsForgeEventBus {

    // BLIND RAGE EFFECT
    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if(event.getEntity() instanceof Player player && !player.level().isClientSide()){
            player.removeEffect(ModEffects.BLIND_RAGE.get());
        }
        TargetingConditions targetingConditions =
                TargetingConditions.forCombat()
                        .ignoreInvisibilityTesting()
                        .selector(
                                entity -> {
                                    if (entity instanceof ArmorStand) {
                                        return false;
                                    }
                                    if (entity instanceof Player player) {

                                        return !player.isSpectator() || player.isCreative();
                                    }
                                    return entity != null && entity.isAlive();
                                });

        if (event.getEntity() instanceof Mob mob && !mob.level().isClientSide()) {
            if (mob.hasEffect(ModEffects.BLIND_RAGE.get())) {
                ServerLevel level = (ServerLevel) mob.level();

                LivingEntity nearestTarget =
                        level.getNearestEntity(
                                LivingEntity.class,
                                targetingConditions,
                                mob,
                                mob.getX(),
                                mob.getY(),
                                mob.getZ(),
                                mob.getBoundingBox().inflate(6));

                if (nearestTarget != null && nearestTarget != mob) {

                    mob.setTarget(nearestTarget);

                    mob.goalSelector.addGoal(
                            0, new NearestAttackableTargetGoal<>(mob, LivingEntity.class, true));
                }
            }
        }
    }
    //CREATIVE CRISIS
   @SubscribeEvent
   public static void onBlockBreak(BlockEvent.BreakEvent event){
       Level level = event.getPlayer().level();
       Player player = event.getPlayer();
       if(!level.isClientSide() && player.hasEffect(ModEffects.CREATIVE_CRISIS.get())){
           event.setCanceled(true);
       }
   }
   @SubscribeEvent
   public static void onBlockPlace(BlockEvent.EntityPlaceEvent event){
       Entity entity =  event.getEntity();
       Level level = entity.level();
       if(entity instanceof Player player && player.hasEffect(ModEffects.CREATIVE_CRISIS.get()) && !level.isClientSide()){
           event.setCanceled(true);
       }
   }

}
