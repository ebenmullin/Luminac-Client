package luminac.modules.render;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.EntityFakePlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.play.client.C03PacketPlayer;

public final class FreeCam extends Module {
	
	private EntityFakePlayer fakePlayer;

	public ModeSetting mode =  new ModeSetting("Fly Mode", "Default", "Default", "FreeStyle", "walking");
	public NumberSetting speed = new NumberSetting("Speed", 1, 0.1, 10, 0.1);
	
	public FreeCam() {
		super("FreeCam", Keyboard.KEY_EQUALS, Category.RENDER);
		this.addSettings(speed, mode);
	}
	
	public void onEnable() {
		mc.thePlayer.noClip = true;
		mc.thePlayer.capabilities.disableDamage = true;
		
		fakePlayer = new EntityFakePlayer();
		
		fakePlayer.noClip = true;
		fakePlayer.capabilities.disableDamage = true;
		
		GameSettings gs = mc.gameSettings;
		KeyBinding[] bindings = {gs.keyBindForward, gs.keyBindBack,
			gs.keyBindLeft, gs.keyBindRight, gs.keyBindJump, gs.keyBindSneak};
		for(KeyBinding binding : bindings)
			KeyBinding.unPressAllKeys();
	}
	
	public void onDisable() {
		fakePlayer.resetPlayerPosition();
		fakePlayer.despawn();
		
		mc.renderGlobal.loadRenderers();
		
		mc.thePlayer.motionX = 0;
		mc.thePlayer.motionY = 0;
		mc.thePlayer.motionZ = 0;
    }

	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
		EntityPlayerSP player = mc.thePlayer;
		
		player.noClip = true;
		
		if(mode.getMode() == "walking") {
			
			//player.onGround = true;
			
		} else if(mode.getMode() == "FreeStyle") {
			//player.motionX = 0;
			//player.motionY = 0;
			//player.motionZ = 0;
			
			player.onGround = false;
			player.fallDistance = 0.0f;
			player.jumpMovementFactor = (float)speed.getValue();
			
			if(mc.gameSettings.keyBindJump.isKeyDown())
				player.motionY += speed.getValue();
			
			if(mc.gameSettings.keyBindSneak.isKeyDown())
				player.motionY -= speed.getValue();
			
		} else if(mode.getMode() == "Default") {
			player.motionX = 0;
			player.motionY = 0;
			player.motionZ = 0;
			
			player.onGround = false;
			player.fallDistance = 0.0f;
			player.jumpMovementFactor = (float)speed.getValue();
			
			if(mc.gameSettings.keyBindJump.isKeyDown())
				player.motionY += speed.getValue();
			
			if(mc.gameSettings.keyBindSneak.isKeyDown())
				player.motionY -= speed.getValue();
			
			}
		}
	}
}