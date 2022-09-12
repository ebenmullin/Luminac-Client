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

public class Scaffold2 extends Module {
	
	public BooleanSetting airblocks = new BooleanSetting("AirBlocks", false);
	
	private static boolean cooldown = false;
	public ItemStack blockInSlot;
	public boolean rotated = false;
	private float[] rotations = new float[2];
	public BlockPos currentPos;
    public EnumFacing currentFacing;
    public Timer timer = new Timer();
	
	public Scaffold2() {
		super("Scaffold", Keyboard.KEY_F, Category.PLAYER);
		this.addSettings(airblocks);
	}

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		String blocks = String.valueOf(getBlocksAmount()+ "Blocks");
		mc.fontRendererObj.drawString(airblocks.isEnabled() ? "Airblocks: on " + blocks : blocks, sr.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth(airblocks.isEnabled() ? "Airblocks: on   3" : blocks) + (int) 21.5, sr.getScaledHeight()/2 + 150, -1);
	}
	
	public StopWatch stopwatch = new StopWatch();
	public void onEvent(Event e) {
		if(e instanceof EventMotion) {
			this.draw();
				
	        BlockPos playBlockPos =  new BlockPos(mc.thePlayer.posX, mc.thePlayer.getEntityBoundingBox().minY, mc.thePlayer.posZ);
	        if(mc.theWorld.isAirBlock(playBlockPos.add(0, -1, 0) )  ) {
	        	
	    	if(isValidBlock(playBlockPos.add(0, -2, 0)))
	    		place(playBlockPos.add(0, -1, 0), EnumFacing.UP);
	    	
	    	else if(isValidBlock(playBlockPos.add(-1, -1, 0)))
	    		place(playBlockPos.add(0, -1, 0), EnumFacing.EAST);
	    	
	    	else if(isValidBlock(playBlockPos.add(1, -1, 0)))
	    		place(playBlockPos.add(0, -1, 0), EnumFacing.WEST);
	    	
	    	else if(isValidBlock(playBlockPos.add(0, -1, -1)))
	    		place(playBlockPos.add(0, -1, 0), EnumFacing.SOUTH);
	    	
	    	else if(isValidBlock(playBlockPos.add(0, -1, 1)))
	    		place(playBlockPos.add(0, -1, 0), EnumFacing.NORTH);
	    	
	    	else if(isValidBlock(playBlockPos.add(1, -1, 1))) {
	
	    		if(isValidBlock(playBlockPos.add(0, -1, 1)))place(playBlockPos.add(0, -1, 1), EnumFacing.NORTH);place(playBlockPos.add(1, -1, 1), EnumFacing.EAST);
	    		
	    	}else if(isValidBlock(playBlockPos.add(-1, -1, 1))) {
	    		if(isValidBlock(playBlockPos.add(-1, -1, 0)))
	    			place(playBlockPos.add(0, -1, 1), EnumFacing.WEST);place(playBlockPos.add(-1, -1, 1), EnumFacing.SOUTH);
	    				
	    	}else if(isValidBlock(playBlockPos.add(-1, -1, -1))) {
	    		if(isValidBlock(playBlockPos.add(0, -1, -1)))
	    			place(playBlockPos.add(0, -1, 1), EnumFacing.SOUTH);place(playBlockPos.add(-1, -1, 1), EnumFacing.WEST);
	    			
	    	}else if(isValidBlock(playBlockPos.add(1, -1, -1))) {
	    		if(isValidBlock(playBlockPos.add(1, -1, 0)))
	    			place(playBlockPos.add(1, -1, 0), EnumFacing.EAST);place(playBlockPos.add(1, -1, -1), EnumFacing.NORTH);
	    		}
	        }    
		}
	}
				
	public void onEnable() {
		this.draw();
	}
				
		public int lastItem;
		
		private void place(BlockPos pos, EnumFacing face) {
			
		cooldown = true;
		if(face == EnumFacing.UP)
			pos = pos.add(0, -1, 0);
		
		else if(face == EnumFacing.NORTH)
			pos = pos.add(0, 0, 1);
		
		else if(face == EnumFacing.EAST)
			pos = pos.add(-1, 0, 0);
		
		else if(face == EnumFacing.SOUTH)
			pos = pos.add(0, 0, -1);
		
		else if(face == EnumFacing.WEST)
			pos = pos.add(1, 0, 0);
		
		else if(face == EnumFacing.DOWN)
			pos = pos.add(0, 0, 0);
		
		int slot = -1;
		int blockCount = 0;
		int amount = 0;
		for (int i = 0; i < 9; i++) {
			ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);
			if(itemStack != null) {
			    int stackSize = itemStack.stackSize;
			    if(itemStack.getItem() instanceof ItemBlock && stackSize > blockCount) {
			        blockCount = stackSize;
			        slot = i;
			        
			        amount++;
			    	} 
				} 
			}
		   
			if(amount != 0) { 
				
				int last = mc.thePlayer.inventory.currentItem;
				mc.thePlayer.inventory.currentItem = slot;
				mc.thePlayer.swingItem();
			
				mc.playerController.onPlayerRightClick(mc.thePlayer, mc.theWorld, mc.thePlayer.getCurrentEquippedItem(),mc.gameSettings.keyBindUseItem.pressed ? pos.down() :  pos, face, new Vec3(0.5D, 0.5D,  0.5D));
				
				mc.thePlayer.inventory.currentItem = last;
			}
			
			double var4 = pos.getX() + 0.25 - mc.thePlayer.posX;
			double var6 = pos.getZ() + 0.25 - mc.thePlayer.posZ;
			double var8 = pos.getY() + 0.25 - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
			double var14= MathHelper.sqrt_double(var4 * var4 + var6*var6);
			float yaw = 0;
			
			if(mc.getRenderManager().livingPlayer.getHorizontalFacing() == EnumFacing.SOUTH) {
				yaw = 900;
				
			}else if(mc.getRenderManager().livingPlayer.getHorizontalFacing() == EnumFacing.WEST) {
				yaw = -90;
				
			}else if(mc.getRenderManager().livingPlayer.getHorizontalFacing() == EnumFacing.EAST) {
				yaw = 90;
				
			}else if(mc.getRenderManager().livingPlayer.getHorizontalFacing() == EnumFacing.NORTH) {
				yaw = 0;
				
			}
			
			float pitch = (float) -(Math.atan2(var8, var14) * 180.0 /3.141592653489 ) - 90f;
			
			}
		
		private boolean isValidBlock(BlockPos pos) {
			Block b = mc.theWorld.getBlockState(pos).getBlock();
			if(airblocks.isEnabled()) {
				return true;
				
			}else {
				return !(b instanceof BlockLiquid) && b.getMaterial() != Material.air;
				
			}
		}
		private int getBlocksAmount() {
        int amount = 0;
        
        for (int i = 36; i < 45; i++) {
            final ItemStack itemStack = mc.thePlayer.inventoryContainer.getSlot(i).getStack();

            if(itemStack != null && itemStack.getItem() instanceof ItemBlock) {
                final Block block = ((ItemBlock) itemStack.getItem()).getBlock();
            
        amount += itemStack.stackSize; 
        } 
    }
    return amount;
}
}