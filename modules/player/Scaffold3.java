package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Scaffold3 extends Module {
	
	public NumberSetting delay = new NumberSetting("Delay (ms)", 0, 0, 2000, 100);
	public BooleanSetting airblocks = new BooleanSetting("AirBlocks", false);
	
	private static boolean cooldown = false;
	public ItemStack blockInSlot;
	public boolean rotated = false;
	private float[] rotations = new float[2];
	public BlockPos currentPos;
    public EnumFacing currentFacing;
    public Timer timer = new Timer();
	
	public Scaffold3() {
		super("Scaffold3", Keyboard.KEY_F, Category.PLAYER);
		this.addSettings(airblocks, delay);
	}

//	public void draw() {
//		ScaledResolution sr = new ScaledResolution(mc);
//		String blocks = String.valueOf(getBlocksAmount()+ "Blocks");
//		mc.fontRendererObj.drawString(airblocks.isEnabled() ? "Airblocks: on " + blocks : blocks, sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(airblocks.isEnabled() ? "Airblocks: on   3" : blocks) + (int) 21.5, sr.getScaledHeight()/2 + 150, -1);
//	}
	
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
//			if(e.isPre()) {
//				BlockPos pos =  new BlockPos(mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ);
//				if(mc.theWorld.getBlockState(pos).getBlock() instanceof BlockLiquid || BlockAir) {
//					setBlockAndFacing(pos);
//
//				}
//			}
		}
//		if(e.isPost()) {
//			if(timer.hasTimeElapsed(delay.getValue(), true)) {
//
//			}
//		}
	}
	
//	public void setBlockAndFacing(BlockPos pos) {
//        if(mc.theWorld.getBlockState(pos.add(0, -1, 0)).getBlock() != Blocks.air) {
//            this.currentPos = pos.add(0, -1, 0);
//            currentFacing = EnumFacing.UP;
//        }else if(mc.theWorld.getBlockState(pos.add(-1, 0, 0)).getBlock() != Blocks.air) {
//            this.currentPos = pos.add(-1, 0, 0);
//            currentFacing = EnumFacing.EAST;
//        }else if(mc.theWorld.getBlockState(pos.add(1, 0, 0)).getBlock() != Blocks.air) {
//            this.currentPos = pos.add(1, 0, 0);
//            currentFacing = EnumFacing.WEST;
//        }else if(mc.theWorld.getBlockState(pos.add(0, 0, -1)).getBlock() != Blocks.air) {
//            this.currentPos = pos.add(0, 0, -1);
//            currentFacing = EnumFacing.SOUTH;
//        }else if(mc.theWorld.getBlockState(pos.add(0, 0, 1)).getBlock() != Blocks.air) {
//            this.currentPos = pos.add(0, 0, 1);
//            currentFacing = EnumFacing.NORTH;
//        }else {
//            currentPos = null;
//            currentFacing = null;
//        }
//    }
}