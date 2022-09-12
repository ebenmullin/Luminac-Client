package luminac.modules.combat;

import java.util.concurrent.Delayed;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.MoveUtil;
import luminac.util.Timer;

public class AntiKB extends Module{
	
	public Timer timer = new Timer();
	//public NumberSetting knockback = new NumberSetting("Knock-Back", 0, 0, 1, 0.1);
	
	public AntiKB() {
		super("AntiKB", Keyboard.KEY_NONE, Category.COMBAT);
		//this.addSettings(knockback);
		}
 
	public void onEnable() {	
		
	}
	public void onDisable() {	
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {	
				/**double direction = MoveUtil.instance.getDirection();
				double knockback = this.knockback.getValue();
				mc.thePlayer.motionX = Math.sin(direction) * knockback;
				mc.thePlayer.motionZ = -Math.cos(direction) * knockback;
			*/
				if(mc.thePlayer.hurtTime > 0.0f) {
					mc.thePlayer.onGround = true;
					mc.thePlayer.motionX = - 0.0f;
					mc.thePlayer.motionY = - 0.1f;
					mc.thePlayer.motionZ = - 0.0f;
		            
		            
				}
			}
		}
	}
}