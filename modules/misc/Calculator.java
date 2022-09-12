package luminac.modules.misc;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventRenderGUI;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.ui.CapesGui;
import luminac.ui.GuiDrag;
//import luminac.ui.clickgui.ClickGUI;
import luminac.util.ColorUtil;
import luminac.util.calculator.CalculatorGui;
import luminac.util.calculator.CalculatorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class Calculator extends Module {

	FontRenderer fr = mc.fontRendererObj;
	public static NumberSetting red = new NumberSetting("Red", 255, 0, 255, 1);
	public static NumberSetting green = new NumberSetting("Green", 255, 0, 255, 1);
	public static NumberSetting blue = new NumberSetting("Blue", 255, 0, 255, 1);

	public Calculator() {
		super("Calculator", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(red, green, blue);
	}

	public void onEnable() {
		mc.displayGuiScreen((GuiScreen)new CalculatorGui());
    }

    public void onDisable() {
    	mc.displayGuiScreen(null);
    }

	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {

			//CalculatorUtils.addButton(4, width + 30.75, height - 50.75, width + 60.75, height - 25.75, ColorUtil.getRainbow(5, 0.6f, 1f), "÷");
		}
	}
}