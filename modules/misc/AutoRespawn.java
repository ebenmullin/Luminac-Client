package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class AutoRespawn extends Module {
	
	public NumberSetting delay = new NumberSetting("Delay (ms)", 500, 0, 2000, 100);
	
	public Timer timer = new Timer();
	int prevSlot;
	
	public AutoRespawn() {
		super("AutoRespawn", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(delay);
	}
	
	public void onEnable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(mc.thePlayer.getHealth() == 0) {
				if(timer.hasTimeElapsed((long) (delay.getValue()), true)) {
					mc.thePlayer.respawnPlayer();
					mc.displayGuiScreen(null);
				}
			}
		}
	}
}
