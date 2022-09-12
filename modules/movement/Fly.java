package luminac.modules.movement;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.MoveUtil;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Fly extends Module {
	
	public ModeSetting mode = new ModeSetting("Mode", "Vanila", "Vanila", "Hypixel", "Rewi", "AAC Old", "NCP", "JetPack");
	public NumberSetting speed = new NumberSetting("Fly Speed", 2, 0.5f, 20, 0.5f);
	
	public Fly() {
		super("Fly", Keyboard.KEY_R, Category.MOVEMENT);
		this.addSettings(speed, mode);
	}
	
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
		mc.thePlayer.capabilities.setFlySpeed(0.1f);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {	
				
				if(mode.getMode() == "Vanila") {
					mc.thePlayer.capabilities.isFlying = true;
					mc.thePlayer.capabilities.setFlySpeed((float) speed.getValue() / 10);
					
				}else if(mode.getMode() == "AAC Old") {
					if(mc.thePlayer.fallDistance > 0.0F) mc.thePlayer.motionY = mc.thePlayer.ticksExisted % 2 == 0 ? 0.1 : 0.0;
					
				}else if(mode.getMode() == "Rewi") {	
					mc.gameSettings.keyBindJump.pressed = false;
					mc.gameSettings.keyBindSprint.pressed = false;
					mc.thePlayer.setSprinting(false);
					mc.thePlayer.motionY = 0.0D;
					mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1.0E-10D, mc.thePlayer.posZ);
					mc.thePlayer.onGround = true;
					if(mc.thePlayer.ticksExisted % 3 == 0) {
						mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1.0E-10D, mc.thePlayer.posZ, true));
					}
					
				}else if(mode.getMode() == "Hypixel") {
					mc.thePlayer.capabilities.isFlying = true; 
					mc.thePlayer.motionY = 0F;
					//MoveUtil.setMotionSpeed(0.15f);
					mc.thePlayer.onGround = true;
					
				}else if(mode.getMode() == "JetPack") {
					if(mc.gameSettings.keyBindJump.isKeyDown()) {
		    			mc.thePlayer.motionY = 0.5D;
		    		}
					
					/**
					double x = mc.thePlayer.posX;
					double y = mc.thePlayer.posY;
					double z = mc.thePlayer.posZ;
					
					if (!mc.thePlayer.onGround)

			        for (int i = 0; i > -1; i++) {
			        	mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x + 10, y + 10.049, z, false));
			        	mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y, z, false));
			        }

					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(x, y + 10.1, z, true));

			        mc.thePlayer.motionX *= 0.1;
			        mc.thePlayer.motionZ *= 0.1;
			        mc.thePlayer.swingItem();
				 	*/
					
				}
			}
		}
	}
}
