package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;

public class SafeWalk extends Module {
	
	public ModeSetting mode = new ModeSetting("Mode", "Eagle", "Eagle", "NoShift");
	
	public SafeWalk() {
		super("SafeWalk", Keyboard.KEY_U, Category.PLAYER);
		this.addSettings(mode);
	}
	
	public void onEnable() {
    }
    
    public void onDisable() {
    	mc.thePlayer.setSneaking(false);
    	mc.gameSettings.keyBindSneak.pressed = false;
    }
    
    public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			
			if(mode.getMode() == "Eagle") {
				ItemStack i = mc.thePlayer.getCurrentEquippedItem();
				BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1D, mc.thePlayer.posZ);
				if(i != null) {
					
					if(i.getItem() instanceof ItemBlock) {
						mc.gameSettings.keyBindSneak.pressed = false;
						if(mc.theWorld.getBlockState(bp).getBlock() == Blocks.air) {
						mc.gameSettings.keyBindSneak.pressed = true;
						}
					}
				}
			} else if(mode.getMode() == "NoShift") {
				Client.addChatMessage("Currently being worked on!");
			}
		}
    }
}
        