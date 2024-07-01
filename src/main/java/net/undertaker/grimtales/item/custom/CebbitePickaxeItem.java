package net.undertaker.grimtales.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.telemetry.events.WorldUnloadEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.sound.ModSounds;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = GrimTales.MOD_ID)
public class CebbitePickaxeItem extends PickaxeItem {
    public static final List<Slime> slimes = new ArrayList<>();
    static List<Slime> highlightSlimes = slimes;

    @Override
    public InteractionResult useOn(UseOnContext context) {

        BlockPos posClicked = context.getClickedPos();
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            int searchRadius = 12;
            for (int x = posClicked.getX() - searchRadius; x <= posClicked.getX() + searchRadius; x++) {
                for (int y = posClicked.getY() - searchRadius; y < posClicked.getY() + searchRadius; y++) {
                    for (int z = posClicked.getZ() - searchRadius; z <= posClicked.getZ() + searchRadius; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        Slime slime = new Slime(EntityType.SLIME, level);
                        BlockState blockState = level.getBlockState(pos);
                        if (isOre(blockState)) {
                            if (highlightSlimes.size() <= 100) {

                                slime.setGlowingTag(true);
                                slime.setSilent(true);
                                slime.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 20 * 5, 0, false, false, false));
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
                                slimes.add(slime);
                                level.addFreshEntity(slime);
                            }

                        }
                    }
                }
            }
        }
        Player player = context.getPlayer();
        player.getCooldowns().addCooldown(this, 20 * 5);
        if (!player.isCreative() || !player.isSpectator() && !level.isClientSide()) {
            if (player.hasEffect(MobEffects.DIG_SLOWDOWN)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 120 * 20, 1));
                player.getCooldowns().addCooldown(this, 20 * 120);
                level.playSound(null, posClicked, ModSounds.TROLL_LAUGH.get(), SoundSource.AMBIENT, 1,1);
            } else {

                player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 15 * 20, 0));
                ItemStack handItem = player.getMainHandItem();
                handItem.hurtAndBreak(50, context.getPlayer(),
                        player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));

            }
        }

        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @org.jetbrains.annotations.Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()){
            components.add(Component.translatable("tooltip.cebbite_pickaxe"));
            components.add(Component.translatable("tooltip.cebbite_pickaxe1"));
            components.add(Component.translatable("tooltip.cebbite_pickaxe2").withStyle(ChatFormatting.DARK_GRAY));

        } else {
            components.add(Component.translatable("tooltip.press_shift").withStyle(ChatFormatting.DARK_GRAY));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
    private static void removeAllSlimes() {
        for (LivingEntity slime : new ArrayList<>(slimes)) {
            if (slime != null) {
                slime.remove(Entity.RemovalReason.DISCARDED);
            }
        }
        highlightSlimes.clear();
        slimes.clear();
    }

    @SubscribeEvent
    public static void livingTickEvent(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof Slime && event.getEntity().getTeam() != null && !event.getEntity().level().isClientSide()) {
            BlockPos pos = event.getEntity().blockPosition();
            Block block = event.getEntity().level().getBlockState(pos).getBlock();
            Slime slime = (Slime) event.getEntity();
            if (!isOre(block.defaultBlockState())) {
                slime.remove(Entity.RemovalReason.DISCARDED);
                highlightSlimes.remove(slime);
            }
            if (slime.getEffect(MobEffects.INVISIBILITY) == null) {
                slime.remove(Entity.RemovalReason.DISCARDED);
                highlightSlimes.remove(slime);
            }
        }
    }


    @SubscribeEvent
    public static void WorldUnloadEvent(LevelEvent.Unload event) {
        removeAllSlimes();
    }

    @SubscribeEvent
    public static void WorldLoadEvent(LevelEvent.Load event) {
        removeAllSlimes();
    }

    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event) {
        if (event.getEntity().getType() == EntityType.SLIME && event.getEntity().getTeam() != null) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onLivingAttacked(LivingAttackEvent event) {
        if (event.getEntity().getType() == EntityType.SLIME && event.getEntity().getTeam() != null) {
            event.setCanceled(true);
            if (event.getSource().is(DamageTypes.PLAYER_ATTACK)) {
                removeAllSlimes();
            }
        }
    }

    private static boolean isOre(BlockState state) {
        return state.is(Blocks.COAL_ORE) ||
                state.is(Blocks.DIAMOND_ORE) ||
                state.is(Blocks.EMERALD_ORE) ||
                state.is(Blocks.GOLD_ORE) ||
                state.is(Blocks.IRON_ORE) ||
                state.is(Blocks.LAPIS_ORE) ||
                state.is(Blocks.REDSTONE_ORE) ||
                state.is(Blocks.COPPER_ORE) ||
                state.is(Blocks.DEEPSLATE_COPPER_ORE) ||
                state.is(Blocks.DEEPSLATE_COAL_ORE) ||
                state.is(Blocks.DEEPSLATE_DIAMOND_ORE) ||
                state.is(Blocks.DEEPSLATE_EMERALD_ORE) ||
                state.is(Blocks.DEEPSLATE_GOLD_ORE) ||
                state.is(Blocks.DEEPSLATE_IRON_ORE) ||
                state.is(Blocks.DEEPSLATE_LAPIS_ORE) ||
                state.is(Blocks.DEEPSLATE_REDSTONE_ORE) ||
                state.is(ModBlocks.CEBBITE_ORE.get()) ||
                state.is(ModBlocks.DEEPSLATE_CEBBITE_ORE.get()) ||
                state.is(ModBlocks.SCULK_CEBBITE_ORE.get()) ||
                state.is(Blocks.NETHER_GOLD_ORE) ||
                state.is(Blocks.NETHER_QUARTZ_ORE)
                ;
    }

    private static PlayerTeam getTeamForOre(Level level, BlockState state) {
        PlayerTeam team = null;
        String name = null;
        ChatFormatting color = null;

        if (state.is(Blocks.COAL_ORE) || state.is(Blocks.DEEPSLATE_COAL_ORE)) {
            name = "Coal";
            color = ChatFormatting.BLACK;
        }

        if (state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.DEEPSLATE_DIAMOND_ORE)) {
            name = "Diamond";
            color = ChatFormatting.AQUA;
        }

        if (state.is(Blocks.EMERALD_ORE) || state.is(Blocks.DEEPSLATE_EMERALD_ORE)) {
            name = "Emerald";
            color = ChatFormatting.DARK_GREEN;
        }

        if (state.is(Blocks.GOLD_ORE) || state.is(Blocks.DEEPSLATE_GOLD_ORE) || state.is(Blocks.NETHER_GOLD_ORE)) {
            name = "Gold";
            color = ChatFormatting.YELLOW;
        }

        if (state.is(Blocks.IRON_ORE) || state.is(Blocks.DEEPSLATE_IRON_ORE)) {
            name = "Iron";
            color = ChatFormatting.GRAY;
        }

        if (state.is(Blocks.LAPIS_ORE) || state.is(Blocks.DEEPSLATE_LAPIS_ORE)) {
            name = "Lapis";
            color = ChatFormatting.DARK_BLUE;
        }

        if (state.is(Blocks.REDSTONE_ORE) || state.is(Blocks.DEEPSLATE_REDSTONE_ORE)) {
            name = "Redstone";
            color = ChatFormatting.DARK_RED;
        }
        if (state.is(Blocks.COPPER_ORE) || state.is(Blocks.DEEPSLATE_COPPER_ORE)) {
            name = "Copper";
            color = ChatFormatting.GOLD;
        }
        if (state.is(ModBlocks.CEBBITE_ORE.get()) || state.is(ModBlocks.DEEPSLATE_CEBBITE_ORE.get()) || state.is(ModBlocks.SCULK_CEBBITE_ORE.get())) {
            name = "Cebbite";
            color = ChatFormatting.DARK_PURPLE;
        }
        if (state.is(Blocks.NETHER_QUARTZ_ORE)) {
            name = "Quartz";
            color = ChatFormatting.WHITE;
        }

        team = level.getScoreboard().getPlayerTeam(name);

        if (team == null && name != null) {
            team = level.getScoreboard().addPlayerTeam(name);
            team.setColor(color);
        }

        return team;
    }

    public CebbitePickaxeItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
}
