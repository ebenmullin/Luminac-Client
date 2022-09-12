package luminac.modules.movement;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import net.minecraft.client.Minecraft;

public class AutoWalk extends Module {
	public Minecraft mc = Minecraft.getMinecraft();
	
	public AutoWalk() {
		super("AutoWalk", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
	
	public void onEnable() {
	}
	
	public void onDisable() {
			mc.gameSettings.keyBindForward.pressed = false;
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				mc.gameSettings.keyBindForward.pressed = true;
				
			}
		}
	}
	
}
