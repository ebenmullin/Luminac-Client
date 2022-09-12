package luminac.modules.movement;

import java.util.Arrays;
import java.util.List;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovementInput;
public class EntitySpeed extends Module {

	public NumberSetting speed = new NumberSetting("Speed", 1.2, 0.1, 5, 0.1);
	public BooleanSetting fly = new BooleanSetting("Fly", false);
	public BooleanSetting snap = new BooleanSetting("Ground Snap", false);

	public EntitySpeed() {
		super("EntitySpeed", Keyboard.KEY_NONE, Category.MOVEMENT);
		this.addSettings(speed, fly, snap);
	}

	public void onEnable() {
		mc.thePlayer.setAIMoveSpeed((float) speed.getValue());
	}

	public void onEvent(Event e) {
//        if(e instanceof EventMotion) {
//			if(mc.thePlayer.ridingEntity == null) return;
//
//			Entity entity = mc.thePlayer.ridingEntity;
//			double entitySpeed = speed.getValue();
//
//			if(entity instanceof EntityHorse) {
//				entity.rotationYaw = mc.thePlayer.rotationYaw;
//				((EntityHorse) entity).rotationYawHead = mc.thePlayer.rotationYawHead;
//				entity.motionY = 1000;
//			}
//			MovementInput movementInput = mc.thePlayer.movementInput;
//			double forward = movementInput.moveForward;
//			double strafe = movementInput.moveStrafe;
//			float yaw = mc.thePlayer.rotationYaw;
//			if((forward == 0.0D) && (strafe == 0.0D)) {
//				entity.motionX = 0;
//				entity.motionY = entity.motionY;
//				entity.motionZ = 0;
//			}else {
//				if(forward != 0.0D) {
//					if(strafe > 0.0D) { yaw += (forward > 0.0D ? -45 : 45);
//					}else if(strafe < 0.0D) yaw += (forward > 0.0D ? 45 : -45);
//					strafe = 0.0D;
//					if(forward > 0.0D) { forward = 1.0D;
//					}else if(forward < 0.0D) forward = -1.0D;
//				}
//				entity.setVelocity((forward * entitySpeed * Math.cos(Math.toRadians(yaw + 90.0F)) + strafe * entitySpeed * Math.sin(Math.toRadians(yaw + 90.0F))), entity.motionY,
//						forward * entitySpeed * Math.sin(Math.toRadians(yaw + 90.0F)) - strafe * entitySpeed * Math.cos(Math.toRadians(yaw + 90.0F)));
//				if(entity instanceof Entity) {
//					Entity em = (Entity) entity;
//					em.setVelocity((forward * entitySpeed * Math.cos(Math.toRadians(yaw + 90.0F)) + strafe * entitySpeed * Math.sin(Math.toRadians(yaw + 90.0F))), em.motionY, (forward * entitySpeed * Math.sin(Math.toRadians(yaw + 90.0F)) - strafe * entitySpeed * Math.cos(Math.toRadians(yaw + 90.0F))));
//				}
//
//				if(fly.isEnabled())
//					if(mc.gameSettings.keyBindJump.isKeyDown())
//						entity.motionX = entity.motionX;
//						entity.motionY = 0.3;
//						entity.motionZ = entity.motionZ;
//
//
//				if(snap.isEnabled()) {
//					BlockPos p = entity.getPosition().add(0, 0.5, 0);
//					if(mc.theWorld.getBlockState(p.offsetDown()).getBlock().getMaterial() == Material.air &&
//						mc.theWorld.getBlockState(p.offsetDown(2)).getBlock().getMaterial() != Material.air &&
//						!(mc.theWorld.getBlockState(p.offsetDown(2)).getBlock().getMaterial() == Material.water) &&
//						entity.fallDistance > 0.01)
//						entity.motionX = entity.motionX;
//						entity.motionY = -1;
//						entity.motionZ = entity.motionZ;
//				}
//			}
//		}
	}

}