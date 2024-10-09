package net.undertaker.grimtales.item.custom.adamantite;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.undertaker.grimtales.item.ModToolTiers;

public class UnawakenAdamantiteAxeItem extends AxeItem {
    public UnawakenAdamantiteAxeItem() {
        super(ModToolTiers.UNAWAKEN_ADAMANTITE, 4, -3f, new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }
}
