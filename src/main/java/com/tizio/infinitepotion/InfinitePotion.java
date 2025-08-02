package com.tizio.infinitepotion;

import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(InfinitePotion.MODID)
public class InfinitePotion {

    public static final String MODID = "infinitepotion";
    public static final Logger LOGGER = LogUtils.getLogger();

    public InfinitePotion(IEventBus modEventBus, ModContainer modContainer) {

        LOGGER.info("Infinite Potion is loading");
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

    }

}