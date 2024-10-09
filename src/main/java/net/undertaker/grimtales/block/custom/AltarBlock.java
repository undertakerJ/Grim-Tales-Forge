package net.undertaker.grimtales.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.undertaker.grimtales.block.ModBlocks;
import net.undertaker.grimtales.block.entity.AltarBlockEntity;
import net.undertaker.grimtales.effect.ModEffects;
import net.undertaker.grimtales.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class AltarBlock extends BaseEntityBlock {
  public static final VoxelShape SHAPE = Block.box(0, 0, 1, 16, 13, 15);

  public AltarBlock(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public VoxelShape getShape(
      BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
    return SHAPE;
  }

  @Override
  public RenderShape getRenderShape(BlockState pState) {
    return RenderShape.MODEL;
  }

  @Override
  public void onRemove(
      BlockState pState,
      Level pLevel,
      BlockPos pPos,
      BlockState pNewState,
      boolean pMovedByPiston) {
    if (pState.getBlock() != pNewState.getBlock()) {
      BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
      if (blockEntity instanceof AltarBlockEntity) {
        blockEntity.saveToItem(new ItemStack(ModBlocks.ALTAR_BLOCK.get()));
      }
    }
    super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
  }

  @Override
  public InteractionResult use(
      BlockState state,
      Level level,
      BlockPos pos,
      Player player,
      InteractionHand hand,
      BlockHitResult blockHitResult) {
    if (!level.isClientSide()) {
      spawnSkeleton(level, pos, player);
      player.addEffect(new MobEffectInstance(ModEffects.CREATIVE_CRISIS.get(), 20*600));
    }

    return InteractionResult.sidedSuccess(level.isClientSide());
  }


  private static void spawnSkeleton(Level level, BlockPos pos, Player player) {
    Skeleton skeleton = new Skeleton(EntityType.SKELETON, level);
    ItemStack sword = new ItemStack(ModItems.CEBBITE_SWORD.get());
    sword.enchant(Enchantments.SHARPNESS, 10);
    sword.enchant(Enchantments.UNBREAKING, 5);
    sword.enchant(Enchantments.VANISHING_CURSE, 1);
    ItemStack helmet = new ItemStack(ModItems.CEBBITE_COMBAT_HELMET.get());
    helmet.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
    helmet.enchant(Enchantments.UNBREAKING, 5);
    helmet.enchant(Enchantments.VANISHING_CURSE, 1);
    ItemStack chest = new ItemStack(ModItems.CEBBITE_COMBAT_CHESTPLATE.get());
    chest.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
    chest.enchant(Enchantments.UNBREAKING, 5);
    chest.enchant(Enchantments.VANISHING_CURSE, 0);
    ItemStack legs = new ItemStack(ModItems.CEBBITE_COMBAT_LEGGINGS.get());
    legs.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
    legs.enchant(Enchantments.UNBREAKING, 5);
    legs.enchant(Enchantments.VANISHING_CURSE, 1);
    ItemStack boots = new ItemStack(ModItems.CEBBITE_COMBAT_BOOTS.get());
    boots.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
    boots.enchant(Enchantments.UNBREAKING, 5);
    boots.enchant(Enchantments.VANISHING_CURSE, 1);

    skeleton.setCustomName(Component.translatable("entity.minecraft.skeleton.godsvindicator"));
    skeleton.setCustomNameVisible(true);
    skeleton.fireImmune();
    skeleton.setPos(pos.getX() + 0.5, pos.getY() + 1.25, pos.getZ() + 0.5);
    skeleton.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*600, 0));
    skeleton.addEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 20*600, 4));
    skeleton.addEffect(new MobEffectInstance(ModEffects.BLIND_RAGE.get(), 20*600, 0));

    CompoundTag tag = skeleton.getPersistentData();
    String key = "godSlayerMob";
    tag.putBoolean(key, true );
    tag.putInt("spawnX", pos.getX());
    tag.putInt("spawnY", pos.getY());
    tag.putInt("spawnZ", pos.getZ());
    skeleton.setItemSlot(EquipmentSlot.HEAD, helmet);
    skeleton.setItemSlot(EquipmentSlot.CHEST, chest);
    skeleton.setItemSlot(EquipmentSlot.LEGS, legs);
    skeleton.setItemSlot(EquipmentSlot.FEET, boots);

    skeleton.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(ModItems.CEBBITE_SWORD.get()));

    level.addFreshEntity(skeleton);
    skeleton.setHealth(60f);
    skeleton.addAdditionalSaveData(tag);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
    return new AltarBlockEntity(blockPos, blockState);
  }
}
