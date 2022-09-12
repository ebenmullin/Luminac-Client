package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class BetterFPS extends Module {
	
	public static ModeSetting optimization = new ModeSetting("Optimization", "Insane!", "Insane!", "Medium", "Low");
	
	
	public BetterFPS() {
		super("BetterFPS", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(optimization);
	}
}
