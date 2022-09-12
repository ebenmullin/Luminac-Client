package luminac.modules.player;

import org.apache.commons.lang3.time.StopWatch;
import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.BlockUtil;
import luminac.util.RayCast;
import luminac.util.Timer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

import java.lang.reflect.Field;

public class Scaffold extends Module {
	
	public ModeSetting mode = new ModeSetting("Mode", "Old", "Old", "New");
	public NumberSetting delay = new NumberSetting("Delay (ms)", 0, 0, 2000, 100);
	public BooleanSetting airblocks = new BooleanSetting("AirBlocks", false);
	public BooleanSetting eagle = new BooleanSetting("Eagle", false);
	public BooleanSetting raycast = new BooleanSetting("Raycast", false);
	public BooleanSetting keepRotations = new BooleanSetting("KeepRotations", true);
	
	private static boolean cooldown = false;
	public ItemStack blockInSlot;
	public boolean rotated = false;
	private float[] rotations = new float[2];
	public BlockPos currentPos;
    public EnumFacing currentFacing;
    public Timer timer = new Timer();
	
	public Scaffold() {
		super("Scaffold", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(airblocks, delay, eagle, raycast, keepRotations, mode);
	}

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		//String blocks = String.valueOf(getBlocksAmount()+ "Blocks");
		//mc.fontRendererObj.drawString(airblocks.isEnabled() ? "Airblocks: on " + blocks : blocks, sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(airblocks.isEnabled() ? "Airblocks: on   3" : blocks) + (int) 21.5, sr.getScaledHeight()/2 + 150, -1);
	}
	
	public StopWatch stopwatch = new StopWatch();
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			if(e.isPre()) {
				rotated = false;
				currentPos = null;
				currentFacing = null;
				
				if(eagle.isEnabled()) {
					BlockPos bp = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1D, mc.thePlayer.posZ);
					mc.gameSettings.keyBindSneak.pressed = false;
					if(mc.theWorld.getBlockState(bp).getBlock() == Blocks.air) {
						mc.gameSettings.keyBindSneak.pressed = true;
					}
					
				}else {
					mc.thePlayer.setSneaking(false);
				}
				
				BlockPos pos = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ);
				if(mc.theWorld.getBlockState(pos).getBlock() instanceof BlockAir) {
					setBlockAndFacing(pos);
					
					if(currentPos != null) {
		                float facing[] = BlockUtil.getDirectionToBlock(currentPos.getX(), currentPos.getY(), currentPos.getZ(), currentFacing);

		                float yaw = facing[0];
		                float pitch = Math.min(90, facing[1] + 9);
		                
		                rotated = true;
		                ((EventMotion) e).setYaw(yaw);
		                ((EventMotion) e).setPitch(pitch);
					}
				}
			}
			if(e.isPost()) {
				if(currentPos != null) {
					if(timer.hasTimeElapsed(delay.getValue(), true)) {
						if(mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBlock) {
							if(mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getCurrentEquippedItem(), currentPos, currentFacing, new Vec3(currentPos.getX(), currentPos.getY(),  currentPos.getZ()))) {
								mc.thePlayer.swingItem();
							}
						}
					}
				}
			}
		}
	}
				
	private void setBlockAndFacing(BlockPos var1) {
        if(mc.theWorld.getBlockState(var1.add(0, -1, 0)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(0, -1, 0);
            currentFacing = EnumFacing.UP;
        }else if(mc.theWorld.getBlockState(var1.add(-1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(-1, 0, 0);
            currentFacing = EnumFacing.EAST;
        }else if(mc.theWorld.getBlockState(var1.add(1, 0, 0)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(1, 0, 0);
            currentFacing = EnumFacing.WEST;
        }else if(mc.theWorld.getBlockState(var1.add(0, 0, -1)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(0, 0, -1);
            currentFacing = EnumFacing.SOUTH;
        }else if(mc.theWorld.getBlockState(var1.add(0, 0, 1)).getBlock() != Blocks.air) {
            this.currentPos = var1.add(0, 0, 1);
            currentFacing = EnumFacing.NORTH;
        }else {
            currentPos = null;
            currentFacing = null;
        }
    }
}