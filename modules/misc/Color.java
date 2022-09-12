package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

public class Color extends Module {
	public ModeSetting moduleList = new ModeSetting("Module List", "Rainbow", "Rainbow", "Blue", "B&W");
	public ModeSetting tabGUI = new ModeSetting("Tab GUI", "Rainbow", "Rainbow", "Blue", "B&W");
	public ModeSetting clickGUI = new ModeSetting("Click GUI", "Rainbow", "Rainbow", "Blue", "B&W");

	public Color() {
		super("Color", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(moduleList, tabGUI, clickGUI);
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				mc.fontRendererObj.drawString("hi", 200, 200, -1);
			}
		}
	}
	
}
