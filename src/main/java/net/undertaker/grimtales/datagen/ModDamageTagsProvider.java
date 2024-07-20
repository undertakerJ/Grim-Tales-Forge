package net.undertaker.grimtales.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.undertaker.grimtales.GrimTales;
import net.undertaker.grimtales.util.ModDamageTypes;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTagsProvider extends DamageTypeTagsProvider {
  public ModDamageTagsProvider(
      PackOutput p_270719_,
      CompletableFuture<HolderLookup.Provider> p_270256_,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(p_270719_, p_270256_, GrimTales.MOD_ID, existingFileHelper);
  }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(ModDamageTypes.ENTROPY_KEY);
    }
}
