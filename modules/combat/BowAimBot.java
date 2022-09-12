package luminac.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class BowAimBot extends Module {

	public Timer timer = new Timer();
	public NumberSetting range = new NumberSetting("Range", 15, 1, 20, 1);
	
	public BowAimBot() {
		super("BowAimBot", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(range);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if(e.isPre()) {
				
			}
		}
	}
}