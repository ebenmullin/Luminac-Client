package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;

public class Zoom extends Module {
	
	public BooleanSetting hud = new BooleanSetting("Show HUD", false);
	public Zoom() {
		super("Zoom", Keyboard.KEY_GRAVE, Category.MISC);
		this.addSettings(hud);
	}
	
	public void onDisable() {
		float FOV = 110;
		mc.gameSettings.fovSetting = FOV ;
		mc.gameSettings.smoothCamera = false;
		mc.gameSettings.hideGUI = false;
	}
	
	public void onEnable() {
			mc.gameSettings.fovSetting = 10;
			mc.gameSettings.keyBindSmoothCamera.pressed = true;
			mc.gameSettings.smoothCamera = true;
			if(hud.isEnabled()) {
				
			} else {
			mc.gameSettings.hideGUI = true;
			
			}
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				

			}
		}
	}
	
}
