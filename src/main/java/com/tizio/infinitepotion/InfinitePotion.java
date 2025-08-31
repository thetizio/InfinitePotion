package com.tizio.infinitepotion;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class InfinitePotion implements ModInitializer {

	public static final String MOD_ID = "infinitepotion";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static double durationMultiplier = 1;

	@Override
	public void onInitialize() {

		LOGGER.info("Infinite Potion is loading");

        try {
            File test = new File("config/infinitepotion.txt");
            if (!test.exists()){
                BufferedWriter writer = new BufferedWriter(new FileWriter("config/infinitepotion.txt"));
                writer.write("1\n");
                writer.write("Duration multiplier for potions when drank (changes require restart)\n");
                writer.write("Value must be in range [0.1 - 100]\n");
                writer.write("value must be at the beginning of line 1 with nothing else on that line");
                writer.close();
            }

            BufferedReader reader = new BufferedReader(new FileReader("config/infinitepotion.txt"));
            double testvalue = Double.parseDouble(reader.readLine().replace(" |\n",""));
            reader.close();

            LOGGER.info("infinite potion reads " + Double.toString(testvalue));
            if (testvalue>=0.1 && testvalue<=100) InfinitePotion.durationMultiplier = testvalue;
            else LOGGER.info("Value of infinite potion config was not in range [0.1 - 100], value will be 1");

        } catch (Exception e) {
            LOGGER.info("Something went wrong while reading infinite potion config, value will be 1");
        }

    }
}