package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class AutoCorrect extends Module {
	
	public AutoCorrect() {
		super("AutoCorrect", Keyboard.KEY_NONE, Category.MISC);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {

		}
	}
	
	public static void Correct(String text) {
		if(text == "im".toLowerCase()) {
			text = "I'm";
			
		}else if(text == "idk".toLowerCase()) {
			text = "I dont know";
			
		}
	}
}
