package net.undertaker.grimtales.item.custom.astralite;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.undertaker.grimtales.item.ModToolTiers;

public class AstraliteHoeItem extends HoeItem {
    public AstraliteHoeItem() {
        super(ModToolTiers.ASTRALITE, -4, -1, new Properties().rarity(Rarity.RARE).fireResistant());
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        if (!player.level().isClientSide && player.isShiftKeyDown()) {
            player.level().explode(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10f, false, Level.ExplosionInteraction.BLOCK);
            player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(10000, player, player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
            player.sendSystemMessage(Component.literal("Oopsie, sowwy~ >///<"));
        }
        player.getCooldowns().addCooldown(this, 5 * 20);
        return InteractionResult.SUCCESS;
    }


}
