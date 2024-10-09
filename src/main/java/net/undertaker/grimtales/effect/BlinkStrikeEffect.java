package net.undertaker.grimtales.effect;

import java.util.List;
import java.util.UUID;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.undertaker.grimtales.item.ModItems;
import org.jetbrains.annotations.NotNull;

public class BlinkStrikeEffect extends MobEffect {
    public BlinkStrikeEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xffffff);
    }

    @Override
    public void applyEffectTick(@NotNull LivingEntity livingEntity, int amplifier) {
        if (!(livingEntity instanceof Player player)) return;

        float radius = 8f;
        AABB searchArea = livingEntity.getBoundingBox().inflate(radius);
        Level level = livingEntity.level();
        List<Entity> targets =
                level.getEntities(livingEntity, searchArea, target -> shouldTarget(player, target));
        if (targets.isEmpty() || (targets.get(0) instanceof Slime)){

            livingEntity.removeEffect(ModEffects.BLINKSTRIKE.get());
            CompoundTag playerData = livingEntity.getPersistentData();
            CompoundTag positionTag = playerData.getCompound("BlinkStrikeHome");
            double x = positionTag.getDouble("X");
            double y = positionTag.getDouble("Y");
            double z = positionTag.getDouble("Z");
            livingEntity.teleportTo(x, y, z);
            playerData.remove("BlinkStrikeTargets");
            player.getCooldowns().addCooldown(ModItems.ASTRAL_EDGE.get(), 15*20);

            return;
        }
        Entity target = targets.get(0);
        if(target instanceof Slime) return;
        Vec3 tpPos = target.position().add(target.getLookAngle().multiply(-1, 0, -1));
        Vec3 lookPos = target.position().add(0, player.getEyeHeight(), 0);
        livingEntity.teleportTo(tpPos.x, tpPos.y, tpPos.z);
        player.attack(target);
        player.lookAt(EntityAnchorArgument.Anchor.EYES, lookPos);
        player.swing(InteractionHand.MAIN_HAND);
        saveAttackedTarget(player, target);
        level.playSound(null, player, SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1f, 0.3f);
    }

    private void saveAttackedTarget(Player player, Entity target) {
        UUID targetId = target.getUUID();
        ListTag targetsTag =
                player.getPersistentData().getList("BlinkStrikeTargets", ListTag.TAG_COMPOUND);
        CompoundTag targetTag = new CompoundTag();
        targetTag.putString("ID", targetId.toString());
        targetsTag.add(targetTag);
        player.getPersistentData().put("BlinkStrikeTargets", targetsTag);
    }

    private boolean shouldTarget(Player player, Entity target) {
        UUID targetId = target.getUUID();
        ListTag targetsTag =
                player.getPersistentData().getList("BlinkStrikeTargets", ListTag.TAG_COMPOUND);
        for (Tag tag : targetsTag) {
            CompoundTag targetTag = (CompoundTag) tag;
            if (targetTag.getString("ID").equals(targetId.toString())) return false;
        }
        return target instanceof LivingEntity && target.isAlive();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return pDuration % 14 == 0;
    }
}