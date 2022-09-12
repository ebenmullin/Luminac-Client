package luminac.modules.world;

import java.util.concurrent.Delayed;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Nuker extends Module{
	
	public NumberSetting big = new NumberSetting("Size", 5, 1, 10, 1);
	
	public Nuker() {
		super("Nuker", Keyboard.KEY_NONE, Category.WORLD);
		this.addSettings(big);
		}
 
	public void onEnable() {	
		
	}
	public void onDisable() {	
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {	
				
				int size = (int) big.getValue();
				int sizeOther = Math.round(size / 2);
				
				if(mc.thePlayer.capabilities.isCreativeMode){
					for(int x = -size; x < size + sizeOther; x++){
						for(int z = -size; z < size + sizeOther; z++){
							for(int y = -size; y < size + sizeOther; y++){
								boolean shouldBreakBlock = true;
				    			int blockX = (int) (mc.thePlayer.posX + x);
				    			int blockY = (int) (mc.thePlayer.posY + y);
				    			int blockZ = (int) (mc.thePlayer.posZ + z);
				    		if (Block.getIdFromBlock(mc.theWorld.getBlockState(new BlockPos(blockX, blockY, blockZ)).getBlock()) != 0){
				    			mc.thePlayer.sendQueue.addToSendQueue(new C07PacketPlayerDigging
				    			(C07PacketPlayerDigging.Action.START_DESTROY_BLOCK, new BlockPos(blockX, blockY, blockZ), EnumFacing.UP));
				     			}
							}
						}
					}
				}
			}
		}
	}
}