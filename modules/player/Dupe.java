package luminac.modules.player;

import java.util.ArrayDeque;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;

public class Dupe extends Module {

	public Dupe() {
		super("Dupe", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
    }
	
	public void onEvent(Event e) {
	    if (e instanceof EventMotion) {
	    	
	    }
	}
}