package com.biomemapcolors.mixin;

import com.biomemapcolors.BiomeColorUtil;
import net.minecraft.block.BlockState;
import net.minecraft.item.map.MapState;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MapState.class)
public abstract class MapRendererMixin {

    @Inject(
        method = "getColor(Lnet/minecraft/world/BlockRenderView;Lnet/minecraft/util/math/BlockPos;)I",
        at = @At("RETURN"),
        cancellable = true
    )
    private void biomemapcolors$applyBiomeTint(
            BlockRenderView world,
            BlockPos pos,
            CallbackInfoReturnable<Integer> cir) {

        BlockState state = world.getBlockState(pos);

        if (!BiomeColorUtil.isGrassOrFoliageBlock(state)) {
            return;
        }

        RegistryEntry<Biome> biomeEntry = world.getBiome(pos);
        int biomeColor = BiomeColorUtil.getBiomeMapColor(state, biomeEntry, pos);

        if (biomeColor != -1) {
            cir.setReturnValue(biomeColor);
        }
    }
}
