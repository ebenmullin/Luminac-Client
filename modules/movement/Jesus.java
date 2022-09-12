package luminac.modules.movement;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import net.minecraft.client.Minecraft;

public class Jesus extends Module {
	
	public Jesus() {
		super("Jesus", Keyboard.KEY_J, Category.MOVEMENT);
	}
	
	public void onDisable() {
		mc.gameSettings.keyBindJump.pressed = false;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {

			}
		}
	}
}
