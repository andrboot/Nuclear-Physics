package org.halvors.nuclearphysics.client.render.block.reactor.fusion;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.nuclearphysics.client.render.block.OBJModelContainer;
import org.halvors.nuclearphysics.client.render.block.RenderTaggedTile;
import org.halvors.nuclearphysics.client.utility.RenderUtility;
import org.halvors.nuclearphysics.common.tile.reactor.fusion.TilePlasmaHeater;
import org.halvors.nuclearphysics.common.utility.ResourceUtility;
import org.halvors.nuclearphysics.common.utility.type.ResourceType;

import java.util.Arrays;

@SideOnly(Side.CLIENT)
public class RenderPlasmaHeater extends RenderTaggedTile<TilePlasmaHeater> {
    private static final OBJModelContainer modelPart = new OBJModelContainer(ResourceUtility.getResource(ResourceType.MODEL, "plasma_heater.obj"), Arrays.asList("rrot", "srot"));
    private static final OBJModelContainer model = new OBJModelContainer(ResourceUtility.getResource(ResourceType.MODEL, "plasma_heater.obj"), Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "t", "u", "v", "w", "x", "y"));

    @Override
    public void render(TilePlasmaHeater tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        GlStateManager.pushMatrix();

        // Translate to the location of our tile entity
        GlStateManager.translate(x, y, z);
        GlStateManager.disableRescaleNormal();

        // Rotate block based on direction.
        RenderUtility.rotateBlockBasedOnDirection(tile.getFacing());

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5, 0, 0.5);
        GlStateManager.rotate((float) Math.toDegrees(tile.rotation), 0, 1, 0);
        GlStateManager.translate(-0.5, 0, -0.5);
        modelPart.render();
        GlStateManager.popMatrix();

        model.render();

        GlStateManager.popMatrix();
    }
}