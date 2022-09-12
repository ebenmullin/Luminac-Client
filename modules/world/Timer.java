package luminac.modules.world;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.NumberSetting;

public class Timer extends Module{

	public NumberSetting speed = new NumberSetting("Speed", 2, 0.1, 10, 0.1f);
	
	public Timer() {
		super("Timer", Keyboard.KEY_NONE, Category.WORLD);
		this.addSettings(speed);
		}
 
	public void onEnable() {	
		net.minecraft.util.Timer.timerSpeed = (float) (speed.getValue());
	}
	
	public void onDisable() {	
		net.minecraft.util.Timer.timerSpeed = 1.0f;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {	
			}
		}
	}
}