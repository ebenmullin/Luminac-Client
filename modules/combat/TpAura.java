package luminac.modules.combat;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0APacketAnimation;

public class TpAura extends Module {
	
	public Timer timer = new Timer();
	public NumberSetting range = new NumberSetting("Range", 20, 1, 50, 1);
	public NumberSetting aps = new NumberSetting("APS", 20, 1, 20, 1);
	public BooleanSetting attack = new BooleanSetting("Attack", true);
	
	public TpAura() {
		super("TP-Aura", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(range, aps, attack);
  }
  
	public void onEvent(Event e) {
		Iterator iterator = this.mc.theWorld.loadedEntityList.iterator();
		while (iterator.hasNext()) {
		Object entity = iterator.next();
		if (entity instanceof EntityLivingBase && entity != this.mc.thePlayer) {
			EntityLivingBase p = (EntityLivingBase) entity;
	        double var1 = p.posX;
	        double var2 = p.posY;
	        double var3 = p.posZ;
	        if (!p.isInvisible() && this.mc.thePlayer.getDistanceToEntity((Entity)p) <= range.getValue()) {
		        this.mc.thePlayer.posX = var1;
			    this.mc.thePlayer.posY = var2;
			    this.mc.thePlayer.posZ = var3;
		        this.mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(var1, var2, var3, this.mc.thePlayer.onGround));
		        this.mc.thePlayer.setPositionAndUpdate(var1, var2, var3);
		        if (this.mc.thePlayer.getDistanceToEntity((Entity)p) <= 6.2173615F && p.isEntityAlive() && p.hurtTime < (0xA ^ 0x0)) {
		        	if(timer.hasTimeElapsed((long) (1000 / aps.getValue()), true)) {
		        		if(attack.isEnabled()) {
		        			this.mc.playerController.attackEntity((EntityPlayer)this.mc.thePlayer, (Entity)p);
		        			this.mc.thePlayer.swingItem();
		        			}
		        		}
		        	} 
	        	} 
			} 
		} 
	}
}
