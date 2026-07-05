package net.terriwin.sokm.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties donut = new FoodProperties.Builder().nutrition(50).fast().alwaysEdible()
            .saturationModifier(100f)
            .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 6000, 1, false, false), 1).build();

    public static final FoodProperties cyberpizza = new FoodProperties.Builder().nutrition(12)
            .saturationModifier(0.667f)
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 6000, 0, false, false), 1).build();


}
