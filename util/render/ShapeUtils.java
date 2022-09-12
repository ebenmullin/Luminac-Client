package luminac.util.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;

public class ShapeUtils {

	public static void drawRoundedRect(int left, int top, int right, int bottom, int radius, int color) {
        if(left < right) {
            int i = left;
            left = right - radius;
            right = i + radius;
        }
        
        if(top < bottom) {
            int j = top;
            top = bottom - radius;
            bottom = j + radius;
        }
        
        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        
        for(int i = 0; i < 4; i++) {
            double x = i == 2 || i == 3 ? right : left;
            double y = i == 1 || i == 2 ? bottom : top;
            for(int a = 0; a <= 10.0; a++) {
                double angle = Math.PI / 2 * (i + a / 9.0);
                worldrenderer.pos(x + Math.sin(angle) * radius, (y + Math.cos(angle) * radius), 0.0).endVertex();
            }
        }
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawFillRectangle(double x, double y, double width, double height) {
        GlStateManager.enableBlend();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2d(x, y + height);
        GL11.glVertex2d(x + width, y + height);
        GL11.glVertex2d(x + width, y);
        GL11.glVertex2d(x, y);
        GL11.glEnd();
    }

    public static void drawCirclePart(double x, double y, float fromAngle, float toAngle, float radius, int slices) {
        GlStateManager.enableBlend();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        GL11.glVertex2d(x, y);
        final float increment = (toAngle - fromAngle) / slices;

        for(int i = 0; i <= slices; i++) {
            final float angle = fromAngle + i * increment;

            final float dX = MathHelper.sin(angle);
            final float dY = MathHelper.cos(angle);

            GL11.glVertex2d(x + dX * radius, y + dY * radius);
        }
        GL11.glEnd();
    }
    
	public static void drawHollowRect(double x, double y, double x1, double y1, int color, int color2) {
		
        Gui.drawRect(x + 1, y + 1, x1 - 1, y1 - 1, color2);
        Gui.drawRect(x, y + 1, x + 1, y1 - 1, color); //left
        Gui.drawRect(x1 - 1, y + 1, x1, y1 - 1, color); //right
        Gui.drawRect(x, y, x1, y + 1, color); //top
        Gui.drawRect(x, y1, x1, y1 - 1, color); //bottom
    }

    public static void color(int color) {
        float red = (color & 255) / 255f,
                green = (color >> 8 & 255) / 255f,
                blue = (color >> 16 & 255) / 255f,
                alpha = (color >> 24 & 255) / 255f;

        GlStateManager.color(red, green, blue, alpha);
    }

    public static void colorRGBA(int color) {
        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;

        GlStateManager.color(r, g, b, a);
    }

}
