package com.tizio.infinitepotion;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InfinitePotion.MODID)
public class InfinitePotion
{

    public static final String MODID = "infinitepotion";

    public InfinitePotion(FMLJavaModLoadingContext context)
    {

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

}