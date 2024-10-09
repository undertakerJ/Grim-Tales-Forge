package net.undertaker.grimtales.item.custom.adamantite;

import java.util.function.Consumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.undertaker.grimtales.item.client.AdamantiteCombatArmorRenderer;
import net.undertaker.grimtales.item.client.CebbiteCombatArmorRenderer;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class AdamantiteCombatArmor extends ArmorItem implements GeoItem {
  public AdamantiteCombatArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
    super(pMaterial, pType, pProperties);
  }

  @Override
  public void initializeClient(Consumer<IClientItemExtensions> consumer) {
    consumer.accept(
        new IClientItemExtensions() {
          private AdamantiteCombatArmorRenderer renderer;

          @Override
          public @NotNull HumanoidModel<?> getHumanoidArmorModel(
              LivingEntity livingEntity,
              ItemStack itemStack,
              EquipmentSlot equipmentSlot,
              HumanoidModel<?> original) {
            if(this.renderer == null) this.renderer = new AdamantiteCombatArmorRenderer();
            this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
            return this.renderer;
          }
        });
  }

  private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

  private PlayState playState(AnimationState animationState) {
    animationState
        .getController()
        .setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
    return PlayState.CONTINUE;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    controllerRegistrar.add(
        new AnimationController<GeoAnimatable>(this, "controller", 0, this::playState));
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return cache;
  }
}
