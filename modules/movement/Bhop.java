package luminac.modules.movement;

import java.util.List;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventMotion;
import luminac.events.listeners.EventRenderGUI;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.KeybindSetting;
import luminac.settings.NumberSetting;
import luminac.util.MoveUtil;
import net.minecraft.util.Timer;

public class Bhop extends Module {
	
	public NumberSetting distance = new NumberSetting("Distance", 0.6, 0.1, 2, 0.1);
	public NumberSetting speed = new NumberSetting("Speed", 1.1, 0.1, 2, 0.1);
	
	public Bhop() {
        super("Bhop", Keyboard.KEY_MINUS, Category.MOVEMENT);
        this.addSettings(distance, speed);
    }
	
	public void onEnable() {

	}
	
	public void onDisable() {
		Timer.timerSpeed = 1;
	}
	
	public void onEvent(Event e) {
        if(e instanceof EventMotion && !mc.gameSettings.keyBindSneak.pressed &&
        		mc.gameSettings.keyBindForward.pressed || !mc.gameSettings.keyBindSneak.pressed &&
        		mc.gameSettings.keyBindBack.pressed || !mc.gameSettings.keyBindSneak.pressed &&
        		mc.gameSettings.keyBindLeft.pressed || !mc.gameSettings.keyBindSneak.pressed &&
        		mc.gameSettings.keyBindRight.pressed) {
        	
            double direction = MoveUtil.instance.getDirection();
            double distance = this.distance.getValue();
            Timer.timerSpeed = (float) this.speed.getValue();
            mc.thePlayer.motionX = -Math.sin(direction) * distance;
            mc.thePlayer.motionZ = Math.cos(direction) * distance;
            if(mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            }
        }
	}         
}
   
    
