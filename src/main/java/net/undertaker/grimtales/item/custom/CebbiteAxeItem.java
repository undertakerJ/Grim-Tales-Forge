package net.undertaker.grimtales.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.item.ModToolTiers;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class CebbiteAxeItem extends AxeItem {
  public CebbiteAxeItem() {
    super(ModToolTiers.CEBBITE, 6, -3.1f, new Properties());
  }

  @SubscribeEvent
  public static void onBreak(BlockEvent.BreakEvent event) {
    BlockPos blockPos = event.getPos();
    BlockState blockState = event.getState();
    Player player = event.getPlayer();
    if (isWood(blockState) && !player.level().isClientSide) {
        for (int y = blockPos.getY(); y <= blockPos.getY() + 50; y++) {
            for (int x = blockPos.getX() - 1; x <= blockPos.getX() + 1; x++) {
                for (int z = blockPos.getZ() - 1; z <= blockPos.getZ() + 1; z++) {
                    BlockPos currentPos = new BlockPos(x, y, z);
                    BlockState state = player.level().getBlockState(currentPos);
                    if (isWood(state)) {
                        player.level().destroyBlock(currentPos, true);
                    }
                }
            }
        }
    }
  }

  private static boolean isWood(BlockState state) {
    return state.is(Blocks.ACACIA_WOOD)
        || state.is(Blocks.BIRCH_WOOD)
        || state.is(Blocks.CHERRY_WOOD)
        || state.is(Blocks.JUNGLE_WOOD)
        || state.is(Blocks.MANGROVE_WOOD)
        || state.is(Blocks.DARK_OAK_WOOD)
        || state.is(Blocks.OAK_WOOD)
        || state.is(Blocks.SPRUCE_WOOD);
  }
}
