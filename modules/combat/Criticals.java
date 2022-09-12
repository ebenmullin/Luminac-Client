package luminac.modules.combat;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C00PacketKeepAlive;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C0APacketAnimation;
import net.minecraft.util.BlockPos;
import org.lwjgl.input.Keyboard;

public class Criticals extends Module {

    public Timer timer = new Timer();
    public NumberSetting critTime = new NumberSetting("Crit Time", 250, 10, 600, 10);

    public Criticals() {
        super("Criticals", Keyboard.KEY_NONE, Category.COMBAT);
        addSettings(critTime);
    }


    public void onEvent(Event e) {
        if(e instanceof EventMotion) {	
			for(int i = 0; i < 10; i++) {
				//mc.thePlayer.onCriticalHit(entity);
				mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging());
				mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement());
				//mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 5, mc.thePlayer.posZ, true));
        	}
        }
    }
}
