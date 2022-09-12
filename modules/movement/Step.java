package luminac.modules.movement;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.NumberSetting;
import net.minecraft.client.Minecraft;

public class Step extends Module {
	public NumberSetting height = new NumberSetting("Height", 2, 1, 10, 1);
	public Minecraft mc = Minecraft.getMinecraft();
	public Step() {
		super("Step", Keyboard.KEY_K, Category.MOVEMENT);
		this.addSettings(height);
	}
	
	public void onEnable() {
		mc.getMinecraft().thePlayer.stepHeight = (float) height.getValue();
		}
	public void onDisable() {
		mc.getMinecraft().thePlayer.stepHeight = 0.5f;	
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				
			}
		}
	}
	
}
