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

public class AutoEat extends Module {
	
	public NumberSetting hunger = new NumberSetting("Hunger amount", 16, 0, 19, 1);
	
	public int prevSlot = 1;
	
	public AutoEat() {
		super("AutoEat", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(hunger);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {

			if(mc.thePlayer.getFoodStats().getFoodLevel() < hunger.getValue()) {
				if(!mc.thePlayer.isEating()) {
					prevSlot = mc.thePlayer.inventory.currentItem;
				}
				for (int i = 0; i < 9; ++i) {
		            ItemStack item = mc.thePlayer.inventory.mainInventory[i];
		            if (item != null) {
		            	if(item.getItem() instanceof ItemFood) {
		            		mc.thePlayer.inventory.currentItem = i;
		            		mc.gameSettings.keyBindUseItem.pressed = true;
							if(mc.thePlayer.getFoodStats().getFoodLevel() >= hunger.getValue()){
								mc.thePlayer.inventory.currentItem = prevSlot;
								prevSlot = 1;
								
							}
		            	}
		            }
		        }
			}//else Client.addChatMeassage("No food in hotbar");
		}
	}
}
