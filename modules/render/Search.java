package luminac.modules.render;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import luminac.util.render.RenderUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockPortal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.tileentity.TileEntitySign;

public class Search extends Module {

	/** Credit to TouchPotato 2020 **/
	
	public static Timer timer = new Timer();

	public NumberSetting width = new NumberSetting("Line width", 1, 0.1, 3, 0.1);
	
	public BooleanSetting diamond = new BooleanSetting("Diamond", true);
	
	public BooleanSetting netherportal = new BooleanSetting("Nether Portal", true);
	public BooleanSetting endportal = new BooleanSetting("End Portal", true);
	
	public Search() {
		super("Search", Keyboard.KEY_RBRACKET, Category.RENDER);
		this.addSettings(diamond, width, netherportal, endportal);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRender3d) {
			for (Object o : mc.theWorld.loadedTileEntityList) {
				if(o instanceof TileEntity && o != mc.thePlayer) {
					TileEntity block = (TileEntity) o;
					
					double playerX = 0;
					double playerY = mc.thePlayer.isSneaking() ? mc.thePlayer.height - 0.26 : mc.thePlayer.height - 0.18;
					double playerZ = 0;
					
					double posX = block.getPos().getX() - mc.getRenderManager().renderPosX;
					double posY = block.getPos().getY() - mc.getRenderManager().renderPosY;
					double posZ = block.getPos().getZ() - mc.getRenderManager().renderPosZ;
					
					if (block instanceof TileEntitySign && diamond.isEnabled()) {
						RenderUtils.drawTracerLine(posX, posY, posZ, playerX, playerY, playerZ, 1, 1, 1, 1, (float) width.getValue());
					}
					
					if (block instanceof TileEntityEndPortal && endportal.isEnabled()) {
						RenderUtils.drawTracerLine(posX, posY, posZ, playerX, playerY, playerZ, 1, 1, 1, 1, (float) width.getValue());
					}
					
					if (block instanceof TileEntity && netherportal.isEnabled()) {
						RenderUtils.drawTracerLine(posX, posY, posZ, playerX, playerY, playerZ, 1, 1, 1, 1, (float) width.getValue());
					}
					
				}
			}
			
			for (Object o : mc.theWorld.getLoadedEntityList()) {
				if(o instanceof Entity && o != mc.thePlayer) {
					Entity entity = (Entity) o;
					
					double posX = entity.posX - mc.getRenderManager().renderPosX;
					double posY = entity.posY - mc.getRenderManager().renderPosY;
					double posZ = entity.posZ - mc.getRenderManager().renderPosZ;
				
				}
			}
		}
	}
}
