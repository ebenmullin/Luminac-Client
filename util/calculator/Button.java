package luminac.util.calculator;

import luminac.ui.GuiDrag;
import luminac.ui.alts.GuiAltManager;
import luminac.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;

public class Button extends GuiScreen{
	
	public static double x, y, x1, y1, target;
	
	Button(double target, double x, double y, double x1, double y1, int color, String text) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRendererObj;
		this.target = target;
		this.x = x;
		this.y = y;
		this.x1 = x1;
		this.y1 = y1;
		
		
		double midX = x + 15 - fr.getStringWidth(text)/2;
		double midY = y + 12.5 - fr.FONT_HEIGHT/2;
		
		Gui.drawRect(x, y, x1, y1, color);
		fr.drawString(text, midX, midY, -1);
		
	}
	
	public void onClick(int mouseX, int mouseY) {
		if(hovered(mouseX, mouseY)) {
			FontRenderer fr = mc.fontRendererObj;
				
			if(target == 1) {
				fr.drawString("Calculator", width - fr.getStringWidth("Calculator")/2, height - 100, -1);
				//CalculatorUtils.clear;
				
			} else if(target == 2) {
				mc.displayGuiScreen(new GuiMultiplayer(mc.currentScreen));
				
			} else if(target == 3) {
				//mc.displayGuiScreen(new GuiClientOptions(this, mc.gameSettings));
				mc.displayGuiScreen(new GuiOptions(mc.currentScreen, mc.gameSettings));
				
			} else if(target == 4) {
				mc.displayGuiScreen(new GuiLanguage(mc.currentScreen, mc.gameSettings, mc.getLanguageManager()));
				
			} else if(target == 5) {
				mc.displayGuiScreen(new GuiAltManager());
				
			} else if(target == 6) {
				mc.shutdown();
			}
		}
	}
	
	public static boolean hovered(int mouseX, int mouseY) {
		return mouseX >= x  && mouseY >= y && mouseX < x1 && mouseY < y1;

	}
}