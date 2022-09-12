package luminac.modules.movement;

import java.util.List;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventMotion;
import luminac.events.listeners.EventRenderGUI;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.KeybindSetting;
import luminac.settings.NumberSetting;
import luminac.util.MoveUtil;

public class Glide extends Module {
	
	public NumberSetting height = new NumberSetting("Height", 2, 1, 5, 1);
	
	public Glide() {
        super("Glide", Keyboard.KEY_NONE, Category.MOVEMENT);
        this.addSettings(height);
    }
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			for(Module m : Client.modules) {
				if(m.name.equals("Fly")) {
					if(mc.thePlayer.motionY <= -0.15 && !mc.thePlayer.isInWater() && !mc.thePlayer.onGround && !mc.thePlayer.isOnLadder() && m.toggled == false) {
						mc.thePlayer.motionY = -0.15;
						mc.thePlayer.onGround = true;
					}
				}
			}
		}
	}
}
    
