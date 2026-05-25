package com.biomemapcolors;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

public final class BiomeColorUtil {

    private BiomeColorUtil() {}

    public static boolean isGrassOrFoliageBlock(BlockState state) {
        return state.isOf(Blocks.GRASS_BLOCK)
            || state.isOf(Blocks.SHORT_GRASS)
            || state.isOf(Blocks.TALL_GRASS)
            || state.isOf(Blocks.FERN)
            || state.isOf(Blocks.LARGE_FERN)
            || state.isOf(Blocks.SUGAR_CANE)
            || state.isOf(Blocks.VINE)
            || state.isOf(Blocks.OAK_LEAVES)
            || state.isOf(Blocks.JUNGLE_LEAVES)
            || state.isOf(Blocks.ACACIA_LEAVES)
            || state.isOf(Blocks.DARK_OAK_LEAVES)
            || state.isOf(Blocks.MANGROVE_LEAVES)
            || state.isOf(Blocks.CHERRY_LEAVES)
            || state.isOf(Blocks.POTTED_FERN);
    }

    private static boolean isFoliageBlock(BlockState state) {
        return state.isOf(Blocks.OAK_LEAVES)
            || state.isOf(Blocks.JUNGLE_LEAVES)
            || state.isOf(Blocks.ACACIA_LEAVES)
            || state.isOf(Blocks.DARK_OAK_LEAVES)
            || state.isOf(Blocks.MANGROVE_LEAVES)
            || state.isOf(Blocks.VINE);
    }

    private static boolean isFixedColorBlock(BlockState state) {
        return state.isOf(Blocks.CHERRY_LEAVES);
    }

    public static int getBiomeMapColor(BlockState state, RegistryEntry<Biome> biomeEntry, BlockPos pos) {
        if (isFixedColorBlock(state)) {
            return -1;
        }

        Biome biome = biomeEntry.value();
        int rawColor;

        if (isFoliageBlock(state)) {
            rawColor = biome.getFoliageColor();
        } else {
            rawColor = biome.getGrassColorAt(pos.getX(), pos.getZ());
        }

        return applyMapShade(rawColor, 1);
    }

    public static int applyMapShade(int rgb, int shade) {
        int[] multipliers = {180, 220, 255, 135};
        int mult = multipliers[Math.max(0, Math.min(3, shade))];

        int r = ((rgb >> 16) & 0xFF) * mult / 255;
        int g = ((rgb >> 8)  & 0xFF) * mult / 255;
        int b = ( rgb        & 0xFF) * mult / 255;

        return 0xFF000000 | (r << 16) | (g << 8) | b;
    }
}
