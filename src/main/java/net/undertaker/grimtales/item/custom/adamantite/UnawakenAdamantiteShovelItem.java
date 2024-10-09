package net.undertaker.grimtales.item.custom.adamantite;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.undertaker.grimtales.item.ModToolTiers;

public class UnawakenAdamantiteShovelItem extends ShovelItem {
    public UnawakenAdamantiteShovelItem() {
        super(ModToolTiers.UNAWAKEN_ADAMANTITE, 0, -3f, new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }
}
