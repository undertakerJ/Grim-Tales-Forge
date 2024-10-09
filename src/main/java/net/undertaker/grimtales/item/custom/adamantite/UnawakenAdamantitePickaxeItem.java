package net.undertaker.grimtales.item.custom.adamantite;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.undertaker.grimtales.item.ModToolTiers;

public class UnawakenAdamantitePickaxeItem extends PickaxeItem {
    public UnawakenAdamantitePickaxeItem() {
        super(ModToolTiers.UNAWAKEN_ADAMANTITE, 1, -3f, new Item.Properties().rarity(Rarity.RARE).fireResistant());
    }
}
