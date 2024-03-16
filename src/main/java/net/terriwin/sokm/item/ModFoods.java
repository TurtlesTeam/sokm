package net.terriwin.sokm.item;


import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties donut = new FoodProperties.Builder().nutrition(50).fast().alwaysEat()
            .saturationMod(100f).effect(()-> new MobEffectInstance(MobEffects.SATURATION, 6000, 1, false, false), 1).build();


}