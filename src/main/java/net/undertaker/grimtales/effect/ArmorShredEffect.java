package net.undertaker.grimtales.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class ArmorShredEffect extends MobEffect {
    protected ArmorShredEffect() {
        super(MobEffectCategory.HARMFUL, 0x0033FFFF);
        addAttributeModifier(Attributes.ARMOR,"86963195-4a5c-4127-b46f-4dab94caf1e1",-0.05D, AttributeModifier.Operation.MULTIPLY_TOTAL);

    }


}
