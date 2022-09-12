package luminac.modules.misc;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventRenderGUI;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.ui.GuiDrag;
import luminac.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class GuiSettings extends Module {
	
	FontRenderer fr = mc.fontRendererObj;
	public static BooleanSetting menu = new BooleanSetting("Custom Menu", true);
	public BooleanSetting arraylist = new BooleanSetting("Arraylist", true);
	public ModeSetting design = new ModeSetting("Design", "Rounded", "Rounded", "Hollow");
	public static NumberSetting size = new NumberSetting("Gui Size", 1, 0.5, 2, 0.1);
	
	public GuiSettings() {
		super("GuiSettings", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(design, size, menu);
		toggled = true;
	}
		
	public void onEvent(Event e) {	
		if(e instanceof EventRenderGUI) {
			
		}
	}
}