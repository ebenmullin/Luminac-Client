package luminac.modules.render;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;

public class FullBright extends Module {
	
	public FullBright() {
		super("FullBright", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 500;
	}
	
	public void onDisable() {
		mc.gameSettings.gammaSetting = 1;
	}
	
}
