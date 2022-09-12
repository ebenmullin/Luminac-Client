package luminac.modules.world;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.NumberSetting;

public class FastPlace extends Module{

	public static NumberSetting delay = new NumberSetting("delay", 0.0, 0.0, 4.0, 0.5);
	
	public FastPlace() {
		super("FastPlace", Keyboard.KEY_NONE, Category.WORLD);
		this.addSettings(delay);
	}
 
	
	public void onDisable() {
		mc.rightClickDelayTimer = 6;
	}
	
	public void onEvent(Event e) {
        if(e instanceof EventUpdate) {
            mc.rightClickDelayTimer = (int) delay.getValue();
        }
    }
}