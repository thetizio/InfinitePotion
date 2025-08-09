package com.tizio.infinitepotion;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InfinitePotion implements ModInitializer {
	public static final String MOD_ID = "infinitepotion";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Infinite Potion is loading");

	}
}