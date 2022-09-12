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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class KnockBack extends Module{
	
	public Timer timer = new Timer();
	public NumberSetting hurtTime = new NumberSetting("HurtTime", 10, 0, 10, 1);
	public KnockBack() {
		super("AntiKB", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(hurtTime);
	}
 
	public void onEnable() {	
		
	}
	public void onDisable() {	
		mc.thePlayer.setSprinting(false);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			EntityLivingBase entity = (EntityLivingBase) mc.theWorld.getLoadedEntityList();
			
			if (entity.hurtTime > hurtTime.getValue())
                return;

            if (mc.thePlayer.isSprinting()) {
            	
                //mc.thePlayer.sendQueue.addToSendQueue(C0BPacketEntityAction(mc.thePlayer, ICPacketEntityAction.WAction.STOP_SPRINTING));
                //mc.thePlayer.sendQueue.addToSendQueue(C0BPacketEntityAction(mc.thePlayer, ICPacketEntityAction.WAction.START_SPRINTING));
                mc.thePlayer.setSprinting(true);
            }
		}
	}
}