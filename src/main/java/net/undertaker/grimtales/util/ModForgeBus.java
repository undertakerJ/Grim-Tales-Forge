package net.undertaker.grimtales.util;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.fluid.ModFluids;
import net.undertaker.grimtales.util.damagesources.GTDamageTypes;

import java.awt.*;
import java.util.HashMap;
import java.util.UUID;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class ModForgeBus {



  @SubscribeEvent
  public static void onEntityTick(LivingEvent.LivingTickEvent event) {
    LivingEntity entity = event.getEntity();
    if (!event.getEntity().level().isClientSide()) {
      if (entity
              .level()
              .getFluidState(entity.getOnPos().above())
              .is(ModFluids.SOURCE_GOD_WATER.get())
          || entity
              .level()
              .getFluidState(entity.getOnPos().above())
              .is(ModFluids.FlOWING_GOD_WATER.get())
          || entity.level().getFluidState(entity.getOnPos()).is(ModFluids.SOURCE_GOD_WATER.get())
          || entity
              .level()
              .getFluidState(entity.getOnPos())
              .is(ModFluids.FlOWING_GOD_WATER.get())) {
        Holder<DamageType> entropyHolder =
            entity
                .level()
                .registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(GTDamageTypes.ENTROPY_KEY);

        entity.hurt(new DamageSource(entropyHolder), 3);
      }
    }
  }

  // GOD SLAYER
  @SubscribeEvent
  public static void onGodSlayerAttack(LivingAttackEvent event) {
    Level level = event.getEntity().level();
    if (event.getSource().getDirectEntity() instanceof Skeleton skeleton && !level.isClientSide()) {
      if (skeleton.getPersistentData().getBoolean("godSlayerMob")) {
        if (skeleton.getRandom().nextFloat() <= 0.08f) {
          String[] messages = {
            "message.godsvindicator.message_attack1",
            "message.godsvindicator.message_attack2",
            "message.godsvindicator.message_attack3",
            "message.godsvindicator.message_attack4",
            "message.godsvindicator.message_attack5",
            "message.godsvindicator.message_attack6",
            "message.godsvindicator.message_attack7",
            "message.godsvindicator.message_attack8"
          };

          String randomMessage = messages[skeleton.getRandom().nextInt(messages.length)];

          if (event.getEntity() instanceof Player player) {
            player.sendSystemMessage(
                Component.translatable("entity.minecraft.skeleton.godsvindicator")
                    .withStyle(ChatFormatting.AQUA)
                    .append(Component.translatable(randomMessage).withStyle(ChatFormatting.WHITE)));
          }
        }

        Holder<DamageType> entropyHolder =
            level
                .registryAccess()
                .registryOrThrow(Registries.DAMAGE_TYPE)
                .getHolderOrThrow(GTDamageTypes.ENTROPY_KEY);
        DamageSource damageSource = new DamageSource(entropyHolder);
        event.getEntity().hurt(damageSource, event.getEntity().getMaxHealth() * 0.25f);
        event.setCanceled(true);
      }
    }
  }

  private static final int TELEPORT_DELAY = 100; // 100 тиков = 5 секунд
  private static final HashMap<UUID, Integer> lastMoveTime = new HashMap<>();
  private static final HashMap<UUID, double[]> previousPositions = new HashMap<>();

  @SubscribeEvent
  public static void onBossUpdate(LivingEvent.LivingTickEvent event) {
    if (event.getEntity() instanceof Skeleton skeleton) {
      if (skeleton.getPersistentData().getBoolean("godSlayerMob")) {
        UUID bossId = skeleton.getUUID();

        double currentX = skeleton.getX();
        double currentY = skeleton.getY();
        double currentZ = skeleton.getZ();

        CompoundTag data = skeleton.getPersistentData();
        double spawnX = data.getDouble("spawnX");
        double spawnY = data.getDouble("spawnY");
        double spawnZ = data.getDouble("spawnZ");
        double distance = skeleton.distanceToSqr(spawnX, spawnY, spawnZ);

        if (distance > 15 * 15) {
          PathNavigation navigation = skeleton.getNavigation();
          navigation.moveTo(spawnX + 0.5, spawnY, spawnZ + 0.5, 1.0);
        }
        if (!previousPositions.containsKey(bossId)) {
          previousPositions.put(bossId, new double[] {currentX, currentY, currentZ});
          lastMoveTime.put(bossId, skeleton.tickCount);
        }

        double[] previousPos = previousPositions.get(bossId);

        if (Mth.equal(previousPos[0], currentX)
            && Mth.equal(previousPos[1], currentY)
            && Mth.equal(previousPos[2], currentZ)) {

        } else {

          lastMoveTime.put(bossId, skeleton.tickCount);
          previousPositions.put(bossId, new double[] {currentX, currentY, currentZ});
        }
      }
    }
  }

  @SubscribeEvent
  public static void onTryAttackGodSlayer(LivingAttackEvent event) {
    Level level = event.getEntity().level();
    if (event.getSource().getDirectEntity() instanceof Player player && !level.isClientSide()) {
      if (event.getEntity().getPersistentData().getBoolean("godSlayerMob")) {
        Skeleton skeleton = (Skeleton) event.getEntity();
        UUID bossId = skeleton.getUUID();
        int currentTick = skeleton.tickCount;
        int lastMoveTick = lastMoveTime.getOrDefault(bossId, currentTick);

        if (currentTick - lastMoveTick >= TELEPORT_DELAY) {
          double playerX = player.getX();
          double playerY = player.getY();
          double playerZ = player.getZ();
          skeleton.teleportTo(playerX, playerY, playerZ);
          player.sendSystemMessage(
              Component.translatable("entity.minecraft.skeleton.godsvindicator")
                  .withStyle(ChatFormatting.AQUA)
                  .append(
                      Component.translatable("message.godsvindicator.teleport")
                          .withStyle(ChatFormatting.WHITE)));
          lastMoveTime.put(bossId, currentTick);
          previousPositions.put(bossId, new double[] {playerX, playerY, playerZ});
        }
        double y_sk = skeleton.getY();
        double y_pl = player.getY();
        double diff_y = y_pl - y_sk;
        if (diff_y >= 1.25 || diff_y <= -1) {
          event.setCanceled(true);
        }
      }
    }
  }

  @SubscribeEvent
  public static void onBossDeath(LivingDeathEvent event) {
    if (event.getEntity() instanceof Skeleton skeleton && !skeleton.level().isClientSide()) {
      if (skeleton.getPersistentData().getBoolean("godSlayerMob")) {
        CompoundTag data = skeleton.getPersistentData();
        int spawnX = data.getInt("spawnX");
        int spawnY = data.getInt("spawnY");
        int spawnZ = data.getInt("spawnZ");
        BlockPos blockEntityPos = new BlockPos(spawnX, spawnY, spawnZ);
        if (blockEntityPos != null) {
          Level level = skeleton.level();
          level.removeBlock(blockEntityPos, false);
          replaceNearbyBlocks(
              level, blockEntityPos, ModBlocks.ADAMANTITE_ORE.get().defaultBlockState());
        }
      }
    }
  }

  private static void replaceNearbyBlocks(
      Level level, BlockPos centerPos, BlockState oreBlockState) {

    BlockPos startPos = centerPos.below();
    for (int x = -1; x <= 1; x++) {
      for (int z = -1; z <= 1; z++) {
        BlockPos blockToReplace = startPos.offset(x, 0, z);
        level.setBlock(blockToReplace, ModBlocks.ADAMANTITE_ORE.get().defaultBlockState(), 3);
      }
    }
  }
}
