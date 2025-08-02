package com.tizio.infinitepotion;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.DoubleValue DRINKMULTIPLIER = BUILDER
            .comment("Duration multiplier for potions when drank")
            .defineInRange("drinkMultiplier", 1,0.1,100);

    static final ModConfigSpec SPEC = BUILDER.build();
}
