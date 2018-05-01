package org.halvors.nuclearphysics.client.event;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.nuclearphysics.common.type.Resource;
import org.halvors.nuclearphysics.common.utility.ResourceUtility;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(Side.CLIENT)
public class TextureEventHandler {
    private static final Map<EnumFluidType, Map<Fluid, TextureAtlasSprite>> fluidTextureMap = new HashMap<>();
    private static final Map<String, TextureAtlasSprite> textureMap = new HashMap<>();
    private static TextureAtlasSprite missingIcon;

    private static final ResourceLocation electricTurbineLarge = ResourceUtility.getResource(Resource.TEXTURE_MODELS, "electric_turbine_large");
    private static final ResourceLocation reactorFissileMaterial = ResourceUtility.getResource(Resource.TEXTURE_MODELS, "reactor_fissile_material");

    @SubscribeEvent
    public static void onPreTextureStitchEvent(final TextureStitchEvent.Pre event) {
        final TextureMap map = event.getMap();

        map.registerSprite(electricTurbineLarge);
        map.registerSprite(reactorFissileMaterial);
        textureMap.put("reactor_fissile_material", map.getTextureExtry(reactorFissileMaterial.toString()));
    }

    @SubscribeEvent
    public static void onPostTextureStitchEvent(final TextureStitchEvent.Post event) {
        final TextureMap map = event.getMap();

        missingIcon = map.getMissingSprite();
        fluidTextureMap.clear();

        for (EnumFluidType type : EnumFluidType.values()) {
            fluidTextureMap.put(type, new HashMap<>());
        }

        for (Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
            if (fluid.getStill() != null) {
                fluidTextureMap.get(EnumFluidType.STILL).put(fluid, map.getTextureExtry(fluid.getStill().toString()));
            }

            if (fluid.getFlowing() != null) {
                fluidTextureMap.get(EnumFluidType.FLOWING).put(fluid, map.getTextureExtry(fluid.getFlowing().toString()));
            }
        }
    }

    public static TextureAtlasSprite getFluidTexture(final Fluid fluid, final EnumFluidType type) {
        final Map<Fluid, TextureAtlasSprite> map = fluidTextureMap.get(type);

        if (fluid == null || type == null) {
            return missingIcon;
        }

        return map.getOrDefault(fluid, missingIcon);
    }

    public static TextureAtlasSprite getTexture(final String texture) {
        return textureMap.getOrDefault(texture, missingIcon);
    }

    public enum EnumFluidType {
        STILL,
        FLOWING
    }
}