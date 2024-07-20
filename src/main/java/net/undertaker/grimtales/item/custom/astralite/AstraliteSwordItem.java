package net.undertaker.grimtales.item.custom.astralite;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.undertaker.grimtales.effect.ModEffects;
import net.undertaker.grimtales.item.ModToolTiers;

public class AstraliteSwordItem extends SwordItem {
    public AstraliteSwordItem() {
        super(ModToolTiers.ASTRALITE, 4, -2.4f, new Properties().rarity(Rarity.RARE).fireResistant());
    }
    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            float randomNumber = player.getRandom().nextFloat();
            if (randomNumber < 0.5f) {
                livingEntity.addEffect(new MobEffectInstance(ModEffects.ENTROPY_EFFECT.get(), 4 * 20, 1));
            }


        }
        return super.onLeftClickEntity(stack, player, entity);
    }

}
