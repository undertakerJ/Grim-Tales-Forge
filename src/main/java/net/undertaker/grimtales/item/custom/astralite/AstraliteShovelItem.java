package net.undertaker.grimtales.item.custom.astralite;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.undertaker.grimtales.item.ModToolTiers;

public class AstraliteShovelItem extends ShovelItem {
    public AstraliteShovelItem() {
        super(ModToolTiers.ASTRALITE, 1, -3, new Properties().rarity(Rarity.RARE).fireResistant());
    }
}
