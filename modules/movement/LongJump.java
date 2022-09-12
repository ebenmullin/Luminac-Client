package luminac.modules.movement;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import net.minecraft.util.Timer;

public class LongJump extends Module {
	
	public LongJump() {
		super("LongJump", Keyboard.KEY_H, Category.MOVEMENT);
	}
	
	public void onDisable() {
        Timer.timerSpeed = 1.0F;
        this.mc.thePlayer.speedInAir = 0.02F;
    }
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			//this.mc.thePlayer.motionY += 0.015D;
            Timer.timerSpeed = 0.3F;
            //mc.thePlayer.jumpMovementFactor = 0.26567F;
            mc.thePlayer.jumpMovementFactor = 0.3f;
            if(mc.thePlayer.onGround) {
                mc.thePlayer.jump();
            }
        }
	}
}
