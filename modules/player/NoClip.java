package luminac.modules.player;

import java.util.ArrayDeque;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventOnOutwardPacket;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.EntityFakePlayer;
import luminac.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoClip extends Module {

	/** Credit to TouchPotato 2020 **/
	
	public NoClip() {
		super("NoClip", Keyboard.KEY_NONE, Category.PLAYER);
	}
	
	public void onDisable() {
        mc.thePlayer.noClip = false;
    }

	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
	        mc.thePlayer.noClip = true;
	   		mc.thePlayer.fallDistance = 0f;
	        mc.thePlayer.onGround = false;
	
	     	mc.thePlayer.capabilities.isFlying = false;
	     	mc.thePlayer.motionX = 0.0;
	        mc.thePlayer.motionY = 0.0;
	        mc.thePlayer.motionZ = 0.0;
	
	        float speed = 0.32f;
	
	        		mc.thePlayer.jumpMovementFactor = speed;
	
	        if (mc.gameSettings.keyBindJump.isKeyDown())
	        	mc.thePlayer.motionY += speed;
	
	        if (mc.gameSettings.keyBindSneak.isKeyDown())
	        	mc.thePlayer.motionY -= speed;
    	}
    }
}