package luminac.util.calculator;

import java.awt.Color;
import java.util.ArrayList;

import luminac.Client;
import luminac.ui.GuiDrag;
import luminac.ui.ImageButtons;
import luminac.ui.alts.GuiAltManager;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class CalculatorUtils extends GuiScreen {
	
	public static ArrayList<Button> button = new ArrayList<Button>();
	public static int mouseX = 0, mouseY = 0;
	public static boolean hovered = false;
	
	public static void drawCalculator(double centerX, double centerY, int mouseX, int mouseY) {
		double width = centerX;
		double height = centerY;
		
		GuiDrag.drawRoundedRect(width - 60.75, height - 102, width + 60.5, height + 76.5, 0xff2d2d2d);
		drawButtons(width, height);
		
	}
	
	public static void addButton(double target, double x, double y, double x1, double y1, int color, String text) {
		Minecraft mc = Minecraft.getMinecraft();
		TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
		
		double midX = x + 15 - fr.getWidth(text)/2;
		double midY = y + 12.5 - fr.getHeight(text)/2;
		
		Gui.drawRect(x, y, x1, y1, color);
		fr.drawString(text, midX, midY, -1);
		
	}

	public static void drawButtons(double width, double height) {

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution sr = new ScaledResolution(mc);
		
		addButton(1, width - 60.75, height - 50.75, width - 30.75, height - 25.75, hovered ? 0xff606060 : 0xff404040, "AC");
		addButton(2, width - 30.25, height - 50.75, width - 0.25, height - 25.75, hovered ? 0xff606060 : 0xff404040, "idk");
		addButton(3, width + 0.25, height - 50.75, width + 30.25, height - 25.75, hovered ? 0xff606060 : 0xff404040, "%");
		addButton(4, width + 30.75, height - 50.75, width + 60.75, height - 25.75, ColorUtil.getRainbow(5, 0.6f, 1f), "·");
		
		addButton(5, width - 60.75, height - 25.25, width - 30.75, height - 0.25, hovered ? 0xff808080 : 0xff606060, "7");
		addButton(6, width - 30.25, height - 25.25, width - 0.25, height - 0.25, hovered ? 0xff808080 : 0xff606060, "8");
		addButton(7, width + 0.25, height - 25.25, width + 30.25, height - 0.25, hovered ? 0xff808080 : 0xff606060, "9");
		addButton(8, width + 30.75, height - 25.25, width + 60.75, height - 0.25, ColorUtil.getRainbow(5, 0.6f, 1f), "x");
		
		addButton(9, width - 60.75, height + 0.25, width - 30.75, height + 25.25, hovered ? 0xff808080 : 0xff606060, "4");
		addButton(10, width - 30.25, height + 0.25, width - 0.25, height + 25.25, hovered ? 0xff808080 : 0xff606060, "5");
		addButton(11, width + 0.25, height + 0.25, width + 30.25, height + 25.25, hovered ? 0xff808080 : 0xff606060, "6");
		addButton(12, width + 30.75, height + 0.25, width + 60.75, height + 25.25, ColorUtil.getRainbow(5, 0.6f, 1f), "-");
		
		addButton(13, width - 60.75, height + 25.75, width - 30.75, height + 50.75, hovered ? 0xff808080 : 0xff606060, "1");
		addButton(14, width - 30.25, height + 25.75, width - 0.25, height + 50.75, hovered ? 0xff808080 : 0xff606060, "2");
		addButton(15, width + 0.25, height + 25.75, width + 30.25, height + 50.75, hovered ? 0xff808080 : 0xff606060, "3");
		addButton(16, width + 30.75, height + 25.75, width + 60.75, height + 50.75, ColorUtil.getRainbow(5, 0.6f, 1f), "+");
		
		addButton(17, width - 60.75, height + 51.25, width - 0.25, height + 76.25, hovered ? 0xff808080 : 0xff606060, "            0");
		addButton(18, width + 0.25, height + 51.25, width + 30.25, height + 76.25, hovered ? 0xff808080 : 0xff606060, ".");
		addButton(19, width + 30.75, height + 51.25, width + 60.75, height + 76.25, ColorUtil.getRainbow(5, 0.6f, 1f), "=");		
	}
	
	/**
	public void onClick(int mouseX, int mouseY) {
		

		//if(this.isHovered(mouseX, mouseY)) {
			
				
			if(target == 1) {
				//commands().ac = true;
				
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

	*/
	
}