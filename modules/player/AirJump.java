package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.player.AirJump;
import luminac.settings.KeybindSetting;
import luminac.util.Timer;

public class AirJump extends Module {
	
	public AirJump() {
		super("AirJump", Keyboard.KEY_Y, Category.PLAYER);
		}
	
	public void onEnable() 
	{
		
	}
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				if (mc.gameSettings.keyBindJump.isPressed()) {
					mc.thePlayer.jump();
				}
			}
		}
	}
}

