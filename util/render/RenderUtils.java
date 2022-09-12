package luminac.util.render;

import java.awt.Color;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import luminac.Client;
import luminac.modules.render.TabGui;
import luminac.ui.GuiDrag;
import luminac.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

public class RenderUtils {
	public static Minecraft mc = Minecraft.getMinecraft();
	public static FontRenderer fr = mc.fontRendererObj;
	
	public static void setup3DLightlessModel(){
		GL11.glEnable(3042);
		GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
	}
	
	public static void shutdown3DLightlessModel() {
		GL11.glDisable(3042);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
	}
	
	public static void drawOutlinedBoundingBox(AxisAlignedBB aa) {
		Tessellator tessellator = Tessellator.getInstance();
	    WorldRenderer worldRenderer = tessellator.getWorldRenderer();
//		worldRenderer.startDrawing(3);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		tessellator.draw();
//		worldRenderer.startDrawing(3);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		tessellator.draw();
//		worldRenderer.startDrawing(1);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
	}
	
	public static void drawBoundingBox(AxisAlignedBB aa) {
		Tessellator tessellator = Tessellator.getInstance();
	    WorldRenderer worldRenderer = tessellator.getWorldRenderer();
	    worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		tessellator.draw();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		tessellator.draw();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		tessellator.draw();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
		worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.maxZ);
		worldRenderer.pos(aa.minX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.minX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.minZ);
		worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ);
		worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ);
		tessellator.draw();
	}
	
	public static void drawEntityESP(double x, double y, double z, double width, double height, float red, float green, float blue, float alpha, float lineRed, float lineGreen, float lineBlue, float lineAlpha, float lineWdith) {
		GL11.glPushMatrix();
		setup3DLightlessModel();
	    GL11.glEnable(1608 + 2301 - 1599 + 732);
	    GL11.glBlendFunc(87 + 227 - -184 + 272, 454 + 212 - 58 + 163);
	    GL11.glDisable(3412 + 1736 - 2209 + 614);
	    GL11.glEnable(1101 + 2188 - 1506 + 1065);
	    GL11.glDisable(2375 + 2004 - 3952 + 2502);
	    GL11.glColor4f(red, green, blue, 0.0F);
	    drawBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
	    GL11.glLineWidth(lineWdith);
	    GL11.glColor4f(lineRed, lineGreen, lineBlue, lineAlpha);
	    drawOutlinedBoundingBox(new AxisAlignedBB(x - width, y, z - width, x + width, y + height, z + width));
	    GL11.glDisable(2153 + 1355 - 2676 + 2016);
	    GL11.glEnable(2650 + 2210 - 4008 + 2701);
	    GL11.glEnable(2298 + 2083 - 3412 + 1960);
	    GL11.glDisable(1508 + 22 - -392 + 1120);
	    shutdown3DLightlessModel();
	    GL11.glPopMatrix();
	}
	
	public static void quickDrawRect(final float x, final float y, final float x2, final float y2) {
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);

        GL11.glEnd();
    }

	public static void drawFilledBox(boolean crossed, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, double red, double green, double blue, double alpha, double red2, double green2, double blue2, double alpha2){
		GL11.glPushMatrix();
		setup3DLightlessModel();
		//mc.theWorld.setEntityLight(false);
		GL11.glColor4d(red, green, blue, alpha);
		drawBoundingBox(new AxisAlignedBB(posX, posY, posZ, posX2, posY2, posZ2));
		GL11.glColor4d(red2, green2, blue2, alpha2);
		GL11.glLineWidth(0.5F);
		if(crossed) {
			drawOutlinedBoundingBox(new AxisAlignedBB(posX, posY, posZ, posX2, posY2, posZ2));
		}else{
			drawOutlinedBoundingBox(new AxisAlignedBB(posX, posY, posZ, posX2, posY2, posZ2));
		}
		//invoker.setEntityLight(true);
		shutdown3DLightlessModel();
		GL11.glPopMatrix();
	}

	public static void drawTracerLine(double entityX, double entityY, double entityZ, double playerX, double playerY, double playerZ, float red, float green, float blue, float alpha, float lineWdith) {
        GL11.glPushMatrix();
        setup3DLightlessModel();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(lineWdith);
        GL11.glColor4f(red, green, blue, alpha);
        GL11.glBegin(2);
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        GL11.glVertex3d(playerX, playerY, playerZ);
        GL11.glVertex3d(entityX, entityY, entityZ);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        shutdown3DLightlessModel();
        GL11.glPopMatrix();
    }
	
	
	public static void drawNameTag(EntityPlayer entity, boolean distance, boolean health, boolean armor, boolean background, double scale) {
        String distanceText = distance ? mc.thePlayer.getDistanceToEntity(entity) + "blocks" : "";
        String healthText = health ? entity.getHealth() + " HP" : "";
        String text = distanceText + healthText;

        GL11.glPushMatrix();
        GL11.glTranslated(
        	entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks - mc.renderManager.renderPosX,
            entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks - mc.renderManager.renderPosY + entity.getEyeHeight() + 0.55,
            entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks - mc.renderManager.renderPosZ
        );

        GL11.glRotatef(-mc.renderManager.playerViewY, 0F, 1F, 0F);
        GL11.glRotatef(mc.renderManager.playerViewX, 1F, 0F, 0F);

        float range = mc.thePlayer.getDistanceToEntity(entity) * 0.25f;

        if(range < 1F)
        	range = 1F;

        double size = range / 100f * scale;
        double width = fr.getStringWidth(text) * 0.5f;
        GL11.glScaled(-size, -size, size);

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);

        if(background) {
        	GuiDrag.drawRoundedRect(-width - 2, -2, width + 4, 4, 0x90000000);
        }
        
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        fr.drawString(text, 1 + -width, 1.5, 0xffffff);

        if(armor && entity instanceof EntityPlayer) {
            mc.renderItem.zLevel = -147F;

            GL11.glEnable(GL11.GL_ALPHA);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
        }
        GL11.glPopMatrix();
    }
}