package luminac.modules.combat;

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

public class AutoDisconnect extends Module {
	
	public NumberSetting health = new NumberSetting("Health", 6, 1, 19, 1);
	
	public Timer timer = new Timer();
	int prevSlot;
	
	public AutoDisconnect() {
		super("AutoQuit", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(health);
	}
	
	public void onEnable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(mc.thePlayer.getHealth() <= health.getValue()) {
				//mc.thePlayer.sendChatMessage("combat logged cause they are bad");
				mc.thePlayer.sendChatMessage("used autoDisconnect cause they are bad");
				//if(mc.thePlayer.isServerWorld()) {
				//}
					//mc.theWorld.sendQuittingDisconnectingPacket();
					
				//}else if(!mc.thePlayer.isServerWorld()) {
					Client.addChatMessage("Made for servers, not singleplayer");
				//}
				
			}
		}
	}
}
