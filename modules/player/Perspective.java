package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Perspective extends Module {
	
	public ModeSetting toggle = new ModeSetting("Toggle", "press", "press", "hold");
	
	int previousPerspective = 0;
	float prevYaw = mc.thePlayer.getRotationYawHead();
	float prevPitch = mc.thePlayer.cameraPitch;
	
	public Perspective() {
		super("Perspective", Keyboard.KEY_F, Category.PLAYER);
	}
	
	public void onEnable() {
		previousPerspective = mc.gameSettings.thirdPersonView;
		mc.gameSettings.thirdPersonView = 1;
	}
	
	
	public void onDisable() {
		mc.gameSettings.thirdPersonView = previousPerspective;
		mc.thePlayer.rotationYaw = prevYaw;
		mc.thePlayer.rotationPitch = prevPitch;

	}
}
	 