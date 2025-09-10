package com.tizio.infinitepotion;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = InfinitePotion.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.DoubleValue DRINKMULTIPLIER = BUILDER
            .comment("Duration multiplier for potions when drank")
            .defineInRange("drinkMultiplier", 1, 0.1, 100);

    static final ForgeConfigSpec SPEC = BUILDER.build();

}