package net.undertaker.grimtales.item.custom.astralite;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.undertaker.grimtales.item.ModToolTiers;
import net.undertaker.grimtales.sound.ModSounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AstraliteHoeItem extends HoeItem {
    public AstraliteHoeItem() {
        super(ModToolTiers.ASTRALITE, -4, -1, new Properties().rarity(Rarity.RARE).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.grimtales.astralite_hoe").withStyle(ChatFormatting.GRAY));
        }else{
            pTooltipComponents.add(Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockPos = context.getClickedPos();
        Player player = context.getPlayer();
        if (!player.level().isClientSide && player.isShiftKeyDown()) {
            player.level().explode(player, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 10f, false, Level.ExplosionInteraction.BLOCK);
            player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(10000, player, player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
            player.level().playSound(player, blockPos, ModSounds.TROLL_LAUGH.get(), SoundSource.AMBIENT, 1f,1f);
            player.sendSystemMessage(Component.literal("Oopsie, sowwy~ >///<"));
        }
        player.getCooldowns().addCooldown(this, 5 * 20);
        return InteractionResult.SUCCESS;
    }


}
