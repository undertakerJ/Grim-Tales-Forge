package net.undertaker.grimtales.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EssenceCrystalItem extends Item {
  public EssenceCrystalItem() {
    super(new Properties());
  }

  public static final String[] ESSENCE_TYPES = {
          "fire",
          "water",
          "earth",
          "air"
  };

  @Override
  public void appendHoverText(
      ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
    CompoundTag nbt = stack.getOrCreateTag();
    int essenceAmount = nbt.getInt("EssenceAmount");
    String essenceType = nbt.getString("EssenceType");
    tooltip.add(
        Component.translatable("tooltip.grimtales.essence_amount", essenceAmount)
            .withStyle(ChatFormatting.GRAY));
    tooltip.add(
        Component.translatable("tooltip.grimtales.essence_type", essenceType)
            .withStyle(ChatFormatting.GRAY));
    super.appendHoverText(stack, world, tooltip, flag);
  }

  @Override
  public Component getName(ItemStack stack) {
    CompoundTag nbt = stack.getOrCreateTag();
    if (nbt.contains("EssenceType")) {
      String essenceType = nbt.getString("EssenceType");
      return Component.translatable("item.grimtales.essence_crystal." + essenceType);
    }
    return super.getName(stack);
  }

  public static void setEssence(ItemStack stack, String type, int amount) {
    CompoundTag nbt = stack.getOrCreateTag();
    nbt.putString("EssenceType", type);
    nbt.putInt("EssenceAmount", amount);
  }

  public static String getEssenceType(ItemStack stack) {
    CompoundTag nbt = stack.getOrCreateTag();
    return nbt.getString("EssenceType");
  }
}
