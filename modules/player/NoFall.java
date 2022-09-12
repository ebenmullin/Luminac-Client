package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {
	
	public ModeSetting mode = new ModeSetting("Mode", "Default", "Default", "Complex");
	public NumberSetting distance = new NumberSetting("Distance", 0.5, 0.1, 10, 0.1);
	
	public NoFall() {
		super("NoFall", Keyboard.KEY_N, Category.PLAYER);
		this.addSettings(mode, distance);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				if(mode.getMode() == "Complex") {
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + distance.getValue(), mc.thePlayer.lastTickPosZ, true));
					
			}else if(mode.getMode() == "Default") {
				if(mc.thePlayer.fallDistance > 2)
					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
				}
			}
		}
	}
}
	 