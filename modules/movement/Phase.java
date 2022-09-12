package luminac.modules.movement;

import java.util.stream.Stream;

import javax.swing.Box;

import org.lwjgl.input.Keyboard;

import net.minecraft.util.MathHelper;
import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.MoveUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class Phase extends Module {
	
	public MoveUtil moveUtil;
	ModeSetting phaseMode = new ModeSetting("Phase Mode", "New", "Redesky", "New");
    NumberSetting strength = new NumberSetting("Strength", 0.4, 0.2, 0.4, 0.01);

    public Phase() {
        super("Phase", Keyboard.KEY_NONE, Category.MOVEMENT);
        addSettings(phaseMode, strength);
    }
    
    public void onDisable() {
        mc.thePlayer.stepHeight = 0.625f;
    }

    public void onEvent(Event e) {
        if(e instanceof EventMotion && e.isPre()) {
        	if(phaseMode.getMode() == "Redesky" && mc.thePlayer.isCollidedHorizontally) {
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY + -0.00000001, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, mc.thePlayer.onGround));
                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY - 1, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch, mc.thePlayer.onGround));

            }else if(phaseMode.getMode() == "New") {
                mc.thePlayer.setSneaking(true);
                if(mc.thePlayer.isCollidedHorizontally) {
                    mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(Math.toRadians((mc).thePlayer.rotationYaw)) * 0.01, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(Math.toRadians((mc).thePlayer.rotationYaw)) * 0.01);
                }else if (isInsideBlock()) {
                    mc.thePlayer.setPosition(mc.thePlayer.posX + -Math.sin(Math.toRadians((mc).thePlayer.rotationYaw)) * 0.3, mc.thePlayer.posY, mc.thePlayer.posZ + Math.cos(Math.toRadians((mc).thePlayer.rotationYaw)) * 0.3);
                    //moveUtil.setMotionSpeed(0);
                }
            }
        }
    }

    public boolean isInsideBlock() {
    	Minecraft mc = Minecraft.getMinecraft();
        for(int x = MathHelper.floor_double(mc.thePlayer.boundingBox.minX); x < MathHelper.floor_double(mc.thePlayer.boundingBox.maxX) + 1; x++) {
            for(int y = MathHelper.floor_double(mc.thePlayer.boundingBox.minY); y < MathHelper.floor_double(mc.thePlayer.boundingBox.maxY) + 1; y++) {
                for(int z = MathHelper.floor_double(mc.thePlayer.boundingBox.minZ); z < MathHelper.floor_double(mc.thePlayer.boundingBox.maxZ) + 1; z++) {
                    Block block = mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
                    if(block != null && !(block instanceof BlockAir)) {
                        AxisAlignedBB boundingBox = block.getCollisionBoundingBox(mc.theWorld, new BlockPos(x, y, z), mc.theWorld.getBlockState(new BlockPos(x, y, z)));
                        if(block instanceof BlockHopper)
                            boundingBox = new AxisAlignedBB(x, y, z, x + 1, y + 1, z + 1);
                        if(boundingBox != null && mc.thePlayer.boundingBox.intersectsWith(boundingBox))
                            return true;
                    }
                }
            }
        }
        return false;
    }
}