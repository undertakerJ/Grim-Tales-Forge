package net.undertaker.grimtales.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
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
    Player player = event.getPlayer();
    if (!player.level().isClientSide) {
      breakNearbyWood(player, blockPos);
    }
  }

  private static void breakNearbyWood(Player player, BlockPos blockPos) {
    BlockState blockState = player.level().getBlockState(blockPos);
    if (!blockState.is(BlockTags.LOGS)) return;
    player.level().destroyBlock(blockPos, true);
    for (int x = -1; x <= 1; x++) {
      for (int y = 0; y <= 1; y++) {
        for (int z = -1; z <= 1; z++) {
          breakNearbyWood(player, blockPos.offset(x, y, z));
        }
      }
    }
  }
}
