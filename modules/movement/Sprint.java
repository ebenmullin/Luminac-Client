package luminac.modules.movement;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;

public class Sprint extends Module {
	
	public Sprint() {
		super("Sprint", Keyboard.KEY_Y, Category.MOVEMENT);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.setSprinting(mc.gameSettings.keyBindSprint.isKeyDown());
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				mc.thePlayer.setSprinting(true);
			}
		}
	}	
}
