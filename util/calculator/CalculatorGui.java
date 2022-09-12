package luminac.util.calculator;

import java.io.IOException;

import luminac.ui.GuiDrag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class CalculatorGui extends GuiScreen {
		
	public static boolean isModeDrag = false;
	public static boolean isModeOpen = false;
	public static int mouseX = 0, mouseY = 0;
	
	public void dragGUI(FontRenderer fr) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution sr = new ScaledResolution(mc);
		
		double width = sr.getScaledWidth()/2;
		double height = sr.getScaledHeight()/2;
			
		CalculatorUtils.drawCalculator(width, height, mouseX, mouseY);
		//fr.drawString("Calculator", width - fr.getStringWidth("Calculator")/2, height - 100, isModeDrag ? -1 : 0);
		
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
		
		if(0 + mouseX < i && 100 + mouseX > i && 0 + mouseY < j && 15 + mouseX > j) {
			isModeDrag = true;
			
		}
		super.mouseClicked(i, j, k);
	}
	
	public void modeDrag(int i, int j) {
		if(isModeDrag) {
			mouseX = i - 50;
			mouseY = j - 5;
		}
	}
}