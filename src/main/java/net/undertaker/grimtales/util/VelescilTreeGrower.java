package net.undertaker.grimtales.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.undertaker.grimtales.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class VelescilTreeGrower extends AbstractTreeGrower {
  @Nullable
  @Override
  protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(
      RandomSource randomSource, boolean b) {
    return ModConfiguredFeatures.VELESCIL_TREE_KEY;
  }



}
