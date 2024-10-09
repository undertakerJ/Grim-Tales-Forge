package net.undertaker.grimtales.item.custom.adamantite;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.undertaker.grimtales.item.ModToolTiers;

public class UnawakenAdamantiteHoeItem extends HoeItem {
    public UnawakenAdamantiteHoeItem() {
        super(ModToolTiers.UNAWAKEN_ADAMANTITE, -4, -1f, new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }
}
