package luminac.util;

import java.awt.Color;

import luminac.modules.render.ClickGui;
import luminac.modules.render.TabGui;
import luminac.ui.HUD;

public class ColorUtil {

	public static int getRainbow(float seconds, float saturation, float brightness) {
		float hue = (System.currentTimeMillis() % (int)(seconds * 1000)) / (float)(seconds * 1000);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
	
	public static int getStrobe(float seconds, float brightness) {
		float hue = (System.currentTimeMillis() % (int)(seconds * 1000)) / (float)(seconds * 1000);
		int color = Color.HSBtoRGB(hue, 0, brightness);
		return color;
	}
	
	public static int getRainbow(float seconds, float saturation, float brightness, long index) {
		float hue = ((System.currentTimeMillis() + index) % (int)(seconds * 1000)) / (float)(seconds * 1000);
		int color = Color.HSBtoRGB(hue, saturation, brightness);
		return color;
	}
	
	public static Color tabGuiColor() {
		return new Color((int)TabGui.red.getValue(), (int)TabGui.green.getValue(), (int)TabGui.blue.getValue());
	}
	
	public static int primaryColor() {
		int primaryColor = TabGui.color.getMode() == "RainBow" || TabGui.color.getMode() == "Color" ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : new Color(tabGuiColor().getRed(), tabGuiColor().getGreen(), tabGuiColor().getBlue(), 255).getRGB();
		return primaryColor;
	}
	
	public static int secondaryColor() {
		int secondaryColor = TabGui.color.getMode() == "RainBow" || TabGui.color.getMode() == "Color" ? ColorUtil.getRainbow(5, 0.6f, 0.7f) : new Color(tabGuiColor().getRed(), tabGuiColor().getGreen(), tabGuiColor().getBlue(), 255).getRGB() - 25;
		return secondaryColor;
	}
	
	public static Color tabuiColor(){
		//if(HUD.color.getMode() == "RainBow")
		return new Color((int)TabGui.red.getValue(), (int)TabGui.green.getValue(), (int)TabGui.blue.getValue());
	}
	
	public static Color clickGuiColor(){
		return new Color((int)ClickGui.red.getValue(), (int)ClickGui.green.getValue(), (int)ClickGui.blue.getValue());
	}
	
	public static int tabGuiOpacity() {
		return (int) TabGui.opacity.getValue();
		
	}
	
	public static int clickGuiOpacity() {
		return (int) ClickGui.opacity.getValue();
		
	}
	
}
