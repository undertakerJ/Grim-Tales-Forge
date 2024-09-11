package net.undertaker.grimtales.item.custom.astralite;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.item.ModToolTiers;

public class AstralitePickaxeItem extends PickaxeItem {
    public AstralitePickaxeItem() {
        super(ModToolTiers.ASTRALITE, 1, -3, new Properties().rarity(Rarity.RARE).fireResistant());
    }
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos posClicked = context.getClickedPos();
        Level level = context.getLevel();
        Player player = context.getPlayer();
        if (!level.isClientSide() && player.isShiftKeyDown()) {
            int searchRadius = 10;
            for (int x = posClicked.getX() - searchRadius; x <= posClicked.getX() + searchRadius; x++) {
                for (int y = posClicked.getY() - searchRadius; y < posClicked.getY() + searchRadius; y++) {
                    for (int z = posClicked.getZ() - searchRadius;
                         z <= posClicked.getZ() + searchRadius;
                         z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        Slime slime = new Slime(EntityType.SLIME, level);
                        BlockState blockState = level.getBlockState(pos);
                        if (isOre(blockState)) {
                            spawnSlime(slime, level, blockState, pos);
                        }
                    }
                }
            }
            player.getCooldowns().addCooldown(this, 20 * 5);
        }
        if (!player.isCreative() || !player.isSpectator() && !level.isClientSide() && player.isShiftKeyDown()) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 5 * 20, 0));
                ItemStack handItem = player.getMainHandItem();
                handItem.hurtAndBreak(
                        25,
                        context.getPlayer(),
                        player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }

        return super.useOn(context);
    }

    private static void spawnSlime(Slime slime, Level level, BlockState blockState, BlockPos pos) {
        slime.setGlowingTag(true);
        slime.setSilent(true);
        slime.addEffect(
                new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 8, 0, false, false, false));
        slime.setNoAi(true);
        slime.setInvulnerable(true);
        slime.setNoGravity(true);
        PlayerTeam team = getTeamForOre(level, blockState);
        slime.setPos(pos.getX() + 0.5, pos.getY() + 0.25, pos.getZ() + 0.5);
        slime.setDeltaMovement(0D, 0D, 0D);
        slime.setYBodyRot(0F);
        slime.setXRot(0f);
        slime.setYHeadRot(0f);
        slime.setYRot(0f);
        level.getScoreboard().addPlayerToTeam(slime.getScoreboardName(), team);
        level.addFreshEntity(slime);
    }

    @SubscribeEvent
    public static void livingTickEvent(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Slime slime
                && event.getEntity().getTeam() != null
                && !slime.level().isClientSide) {
            BlockPos pos = event.getEntity().blockPosition();
            BlockState blockState = event.getEntity().level().getBlockState(pos);
            if (!isOre(blockState)) {
                slime.remove(Entity.RemovalReason.DISCARDED);
            }
            if (!slime.hasEffect(MobEffects.INVISIBILITY)) {
                slime.remove(Entity.RemovalReason.DISCARDED);
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity().getType() == EntityType.SLIME && event.getEntity().getTeam() != null) {
            event.setCanceled(true);
        }
    }

    private static boolean isOre(BlockState state) {
        return state.is(Tags.Blocks.ORES);
    }

    private static PlayerTeam getTeamForOre(Level level, BlockState state) {
        PlayerTeam team;
        String name;
        ChatFormatting color;

        if (state.is(Tags.Blocks.ORES_COAL)) {
            name = "Coal";
            color = ChatFormatting.BLACK;
        } else if (state.is(Tags.Blocks.ORES_DIAMOND)) {
            name = "Diamond";
            color = ChatFormatting.AQUA;
        } else if (state.is(Tags.Blocks.ORES_EMERALD)) {
            name = "Emerald";
            color = ChatFormatting.DARK_GREEN;
        } else if (state.is(Tags.Blocks.ORES_GOLD)) {
            name = "Gold";
            color = ChatFormatting.YELLOW;
        } else if (state.is(Tags.Blocks.ORES_IRON)) {
            name = "Iron";
            color = ChatFormatting.GRAY;
        } else if (state.is(Tags.Blocks.ORES_LAPIS)) {
            name = "Lapis";
            color = ChatFormatting.DARK_BLUE;
        } else if (state.is(Tags.Blocks.ORES_REDSTONE)) {
            name = "Redstone";
            color = ChatFormatting.DARK_RED;
        } else if (state.is(Tags.Blocks.ORES_COPPER)) {
            name = "Copper";
            color = ChatFormatting.GOLD;
        } else if (state.is(ModBlocks.CEBBITE_ORE.get())
                || state.is(ModBlocks.DEEPSLATE_CEBBITE_ORE.get())
                || state.is(ModBlocks.SCULK_CEBBITE_ORE.get())) {
            name = "Cebbite";
            color = ChatFormatting.DARK_PURPLE;
        } else if (state.is(Blocks.NETHER_QUARTZ_ORE)) {
            name = "Quartz";
            color = ChatFormatting.WHITE;
        } else {
            return null;
        }

        team = level.getScoreboard().getPlayerTeam(name);

        if (team == null) {
            team = level.getScoreboard().addPlayerTeam(name);
            team.setColor(color);
        }

        return team;
    }
}
