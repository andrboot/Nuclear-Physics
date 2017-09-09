package org.halvors.nuclearphysics.client.render.block.reactor;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.halvors.nuclearphysics.client.utility.RenderUtility;
import org.halvors.nuclearphysics.common.tile.reactor.TileThermometer;
import org.halvors.nuclearphysics.common.utility.position.Position;
import org.halvors.nuclearphysics.common.utility.type.Color;

@SideOnly(Side.CLIENT)
public class RenderThermometer extends TileEntitySpecialRenderer<TileThermometer> {
    @Override
    public void render(TileThermometer tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        GlStateManager.pushMatrix();
        RenderUtility.enableLightmap();

        RenderUtility.renderText((tile.isOverThreshold() ? Color.DARK_RED : Color.BLACK) + Integer.toString(Math.round(tile.getDetectedTemperature())) + " K", tile.getFacing(), 0.8F, x, y + 0.1, z);
        RenderUtility.renderText((tile.isOverThreshold() ? Color.DARK_RED : Color.DARK_BLUE) + "Threshold: " + (tile.getThershold()) + " K", tile.getFacing(), 1, x, y - 0.1, z);

        Position trackCoordinate = tile.getTrackCoordinate();

        if (tile.getTrackCoordinate() != null) {
            RenderUtility.renderText(trackCoordinate.getIntX() + ", " + trackCoordinate.getIntY() + ", " + trackCoordinate.getIntZ(), tile.getFacing(), 0.5F, x, y - 0.3, z);
        }

        GlStateManager.popMatrix();
    }
}
