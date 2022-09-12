package luminac.modules.combat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class AimAssist extends Module {

	public Timer timer = new Timer();
	public NumberSetting range = new NumberSetting("Range", 6, 1, 12, 1);
	public BooleanSetting tracker = new BooleanSetting("Tracker", false);
	
	public AimAssist() {
		super("Aimbot", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(range, tracker);
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if(e.isPre()) {
				
				EventMotion event = (EventMotion)e;
				
				List<Entity> targets = mc.theWorld.loadedEntityList.stream().filter(EntityLivingBase.class::isInstance).collect(Collectors.toList());
				
				targets = targets.stream().filter(entity -> entity.getDistanceToEntity(mc.thePlayer) < range.getValue() && entity != mc.thePlayer && !entity.isDead && ((EntityLivingBase) entity).getHealth() > 0).collect(Collectors.toList());
				
				targets.sort(Comparator.comparingDouble(entity -> ((EntityLivingBase)entity).getDistanceToEntity(mc.thePlayer)));
				
				//targets = targets.stream().filter(Entity.class::isInstance).collect(Collectors.toList());
				
				if(!targets.isEmpty()) {
					Entity target = targets.get(0);
					
					if(tracker.isEnabled()) {
						mc.thePlayer.rotationYaw = (getRotations(target)[0]);
						mc.thePlayer.rotationPitch = (getRotations(target)[1]);
					} else {
						mc.thePlayer.swingItem();
					}
					event.setYaw(getRotations(target)[0]);
					event.setPitch(getRotations(target)[1]);
						
				}
			}
		}
	}
	
	public float[] getRotations(Entity e) {
		double deltaX = e.posX + (e.posX - e.lastTickPosX) - mc.thePlayer.posX,
		       deltaY = e.posY - 3.5 + e.getEyeHeight() - mc.thePlayer.posY + mc.thePlayer.getEyeHeight(),
		       deltaZ = e.posZ + (e.posZ - e.lastTickPosZ) - mc.thePlayer.posZ,
		       distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaZ, 2));
		
		float yaw = (float) Math.toDegrees(-Math.atan(deltaX / deltaZ)),
			  pitch = (float) -Math.toDegrees(Math.atan(deltaY / distance));
		
		if(deltaX < 0 && deltaZ < 0) {
			yaw = (float) (90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		}else if(deltaX > 0 && deltaZ < 0) {
			yaw = (float) (-90 + Math.toDegrees(Math.atan(deltaZ - deltaX)));
		}
		
		return new float[] { yaw, pitch };
	}
}
