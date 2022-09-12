package luminac.modules.world;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.NumberSetting;

public class WorldTime extends Module{
	
	public NumberSetting time = new NumberSetting("Time", 10, 1, 20, 1);

    public WorldTime() {
        super("WorldTime", Keyboard.KEY_NONE, Category.WORLD);
        this.addSettings(time);
    }

    public void onEvent(Event e) {
    	if(e instanceof EventUpdate) {
    		mc.theWorld.setWorldTime((long) time.getValue() * 1000);
        }
    }
}
