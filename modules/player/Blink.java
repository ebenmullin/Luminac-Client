package luminac.modules.player;

import java.util.ArrayDeque;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import luminac.events.listeners.EventOnOutwardPacket;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.EntityFakePlayer;
import luminac.util.Timer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Blink extends Module {

	/** Credit to TouchPotato 2020 **/
	
	private ArrayList<Packet> savedPackets = new ArrayList<Packet>();
	private EntityFakePlayer fakePlayer;
	
	public Blink() {
		super("Blink", Keyboard.KEY_M, Category.PLAYER);
	}
	
	public void onOutwardPacket(EventOnOutwardPacket event){
		Packet packet = event.getPacket();
		
		if(packet instanceof C03PacketPlayer){
			savedPackets.add(packet);
			event.setCancelled(true);
		}
	}
	
	public void onEnable() {		
		fakePlayer = new EntityFakePlayer();
	}
	
	public void onDisable() {
		
		fakePlayer.despawn();
		for(Packet packet : savedPackets){
			mc.thePlayer.sendQueue.addToSendQueue(packet);
		}
		
		savedPackets.clear();
    }
}