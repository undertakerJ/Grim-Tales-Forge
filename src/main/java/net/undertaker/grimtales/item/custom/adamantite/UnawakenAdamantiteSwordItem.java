package net.undertaker.grimtales.item.custom.adamantite;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.undertaker.grimtales.item.ModToolTiers;

public class UnawakenAdamantiteSwordItem extends SwordItem {
    public UnawakenAdamantiteSwordItem() {
        super(ModToolTiers.UNAWAKEN_ADAMANTITE, 3, -2.2f, new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }
}
