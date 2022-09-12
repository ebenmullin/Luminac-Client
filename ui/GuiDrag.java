package luminac.ui;

import java.io.IOException;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class GuiDrag extends GuiScreen{
	
	public static boolean isModeDrag = false;
	public static boolean isModeOpen = false;
	public static int combatX = 0, combatY = 0;
	public static int movementX = 0, movementY = 0;
	public static int playerX = 0, playerY = 0;
	public static int renderX = 0, remderY = 0;
	public static int miscX = 0, miscY = 0;
	public static int worldX = 0, worldY = 0;
	
	public static void drawRoundedRect(double x, double y, double x1, double y1, int color) {
		
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Gui.drawRect(x * 2, y * 2, x1 * 2, y1 * 2, color);
        Gui.drawRect(x * 2 - 1, y * 2 + 1, x * 2, y1 * 2 - 1, color); //left
        Gui.drawRect(x1 * 2, y * 2 + 1, x1 * 2 + 1, y1 * 2 - 1, color); //right
        Gui.drawRect(x * 2 + 1, y * 2 - 1, x1 * 2 - 1, y * 2, color); //top
        Gui.drawRect(x * 2 + 1, y1 * 2, x1 * 2 - 1, y1 * 2 + 1, color); //bottom
        GL11.glScalef(2f, 2f, 2f);
    }
	
	public static void drawHollowRect(double x, double y, double x1, double y1, int color, int color2) {
		
        Gui.drawRect(x + 1, y + 1, x1 - 1, y1 - 1, color2);
        Gui.drawRect(x, y + 1, x + 1, y1 - 1, color); //left
        Gui.drawRect(x1 - 1, y + 1, x1, y1 - 1, color); //right
        Gui.drawRect(x, y, x1, y + 1, color); //top
        Gui.drawRect(x, y1, x1, y1 - 1, color); //bottom
    }
	
	public static void drawClickableRect(double x, double y, double x1, double y1, int color, int color2) {
		
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Gui.drawRect(x * 2 - 1, y * 2, x1 * 2 + 1, y1 * 2, color2);
        Gui.drawRect(x * 2 + 1, y * 2 - 1, x1 * 2 - 1, y * 2, color);
        Gui.drawRect(x * 2, y * 2, x1 * 2, y * 2 + 20, color);
        Gui.drawRect(x * 2 - 1, y * 2, x * 2, y * 2 + 20, color);
        
        Gui.drawRect(x * 2 + 1, y1 * 2, x1 * 2 - 1, y1 * 2 + 1, color); //bottom
        Gui.drawRect(x * 2, y1 * 2 - 10, x1 * 2, y1 * 2, color);
        
        GL11.glScalef(2f, 2f, 2f);
    }

	public static void drawDragableGui(double x, double y, double x1, double y1, int color) {
		
	}
	public static void dragGUI(FontRenderer fr) {
		
		drawRoundedRect(100 + combatX, 50 + combatY, 200 + combatX, 65 + combatY, 0xff5c7bab);
		fr.drawString("Combat", 3 + combatX, 3 + combatY, isModeDrag ? 0xff5c7bab : 0xff7586a1);
		
		drawRoundedRect(200 + movementX, 50 + movementY, 300 + movementX, 65 + movementY, 0xff5c7bab);
		fr.drawString("Movement", 3 + movementX, 3 + movementY, isModeDrag ? 0xff5c7bab : 0xff7586a1);
		
		drawRoundedRect(300 + playerX, 50 + playerY, 400 + playerX, 65 + playerY, 0xff5c7bab);
		fr.drawString("Player", 3 + playerX, 3 + playerY, isModeDrag ? 0xff5c7bab : 0xff7586a1);
	}
	
	public void mouseMoved(int i, int j, int k) {
		if(k == 0) {
			isModeDrag = false;
		}
	}
	
	public void drawScreen(int i, int j, float f) {
		dragGUI(fontRendererObj);
		modeDrag(i, j);
		super.drawScreen(i, j, f);
	}
	
	public void mouseClicked(int i, int j, int k) throws IOException {
		drawRoundedRect(100 + combatX, 50 + combatY, 200 + combatX, 65 + combatY, 0xff5c7bab);
		
		drawRoundedRect(200 + movementX, 50 + movementY, 300 + movementX, 65 + movementY, 0xff5c7bab);
		
		drawRoundedRect(300 + playerX, 50 + playerY, 400 + playerX, 65 + playerY, 0xff5c7bab);
		if(0 + combatX < i && 100 + combatX > i && 0 + combatY < j && 15 + combatX > j) {
			isModeDrag = true;
			
		}else if(0 + movementX < i && 100 + movementX > i && 0 + movementY < j && 15 + movementX > j) {
			isModeDrag = true;
			
		}else if(0 + playerX < i && 100 + playerX > i && 0 + playerY < j && 15 + playerX > j) {
			isModeDrag = true;
			
		//}else if(0 + renderX < i && 100 + renderX > i && 0 + combatY < j && 15 + renderX > j) {
			//isModeDrag = true;
			
		}
		super.mouseClicked(i, j, k);
	}
	
	public void modeDrag(int i, int j) {
		if(isModeDrag) {
			combatX = i - 50;
			combatY = j - 5;
		}
	}
}
