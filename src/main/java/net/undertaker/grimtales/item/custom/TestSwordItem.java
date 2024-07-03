package net.undertaker.grimtales.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.undertaker.grimtales.effect.ModEffects;
import org.jetbrains.annotations.NotNull;

public class TestSwordItem extends SwordItem {

    public TestSwordItem() {
        super(Tiers.NETHERITE, 4, -2.4f, new Item.Properties().rarity(Rarity.RARE));
    }
    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        CompoundTag positionTag = new CompoundTag();
        positionTag.putDouble("X", player.position().x);
        positionTag.putDouble("Y", player.position().y);
        positionTag.putDouble("Z", player.position().z);
        player.getPersistentData().put("BlinkStrikeHome", positionTag);
        player.addEffect(new MobEffectInstance(ModEffects.BLINKSTRIKE.get(), 200, 0, false, false));
        return super.use(level, player, usedHand);
    }



}
