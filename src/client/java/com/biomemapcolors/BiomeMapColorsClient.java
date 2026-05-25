package com.biomemapcolors;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiomeMapColorsClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("biomemapcolors");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Biome Map Colors loaded!");
    }
}
