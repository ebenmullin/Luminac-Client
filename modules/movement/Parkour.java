package luminac.modules.movement;

import java.util.stream.Stream;

import javax.swing.Box;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.MathHelper;
import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.util.MoveUtil;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class Parkour extends Module {
	
	public Parkour() {
		super("Parkour", Keyboard.KEY_NONE, Category.MOVEMENT);
		
	}
	
	public void onEnable() {
		
    }
    
    public void onDisable() {
    	mc.gameSettings.keyBindJump.pressed = false;
    }
    
    public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
				/**
				if(!mc.thePlayer.onGround || mc.gameSettings.keyBindJump.isPressed())
					return;
				
				if(mc.thePlayer.isSneaking() || mc.gameSettings.keyBindSneak.isPressed())
					return;
				
				AxisAlignedBB box = mc.thePlayer.getBoundingBox();
				AxisAlignedBB adjustedBox = box.offset(0, -0.5, 0).expand(-0.001, 0, -0.001);
				
				Stream<VoxelShape> blockCollisions = mc.theWorld.checkBlockCollision(adjustedBox);
				
				if(blockCollisions.findAny().isPresent())
					return;
				
				mc.thePlayer.jump();
				
			}
				
			BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1D, mc.thePlayer.posZ);

			mc.gameSettings.keyBindJump.pressed = false;
			if(mc.theWorld.getBlockState(bp).getBlock() == Blocks.air) {
				mc.gameSettings.keyBindJump.pressed = true;
				*/
		}
    }
}
        