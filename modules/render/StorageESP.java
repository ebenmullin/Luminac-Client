package luminac.modules.render;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.render.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.tileentity.TileEntityEnderChest;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;

public class StorageESP extends Module {
	
	public BooleanSetting chests = new BooleanSetting("Chests", true);
	public BooleanSetting endchests = new BooleanSetting("Ender Chests", true);
	public BooleanSetting furnaces = new BooleanSetting("Furnaces", false);
	public BooleanSetting dispensers = new BooleanSetting("Dispensers", false);
	public BooleanSetting hoppers = new BooleanSetting("Hoppers", false);
	public BooleanSetting brewstands = new BooleanSetting("Brewing Stands", false);
	public BooleanSetting chestcarts = new BooleanSetting("Chest MineCarts", true);
	public BooleanSetting furnacecarts = new BooleanSetting("FurnaceCarts", false);
	public BooleanSetting hoppercarts = new BooleanSetting("HopperCarts", false);
	public BooleanSetting armorstands = new BooleanSetting("ArmorStands", true);
	
	public static NumberSetting red = new NumberSetting("Red", 255, 0, 255, 1);
	public static NumberSetting green = new NumberSetting("Green", 255, 0, 255, 1);
	public static NumberSetting blue = new NumberSetting("Blue", 255, 0, 255, 1);
	
	
	public StorageESP() {
		super("StorageESP", Keyboard.KEY_RBRACKET, Category.RENDER);
		this.addSettings(red, green, blue, chests, endchests, furnaces, dispensers, hoppers, brewstands, chestcarts, furnacecarts, hoppercarts, armorstands);

	}
	public void onEvent(Event e) {
		if(e instanceof EventRender3d) {
			for (Object o : mc.theWorld.loadedTileEntityList) {
				if(o instanceof TileEntity && o != mc.thePlayer) {
					TileEntity entity = (TileEntity) o;
					
					double posX = entity.getPos().getX() - mc.getRenderManager().renderPosX;
					double posY = entity.getPos().getY() - mc.getRenderManager().renderPosY;
					double posZ = entity.getPos().getZ() - mc.getRenderManager().renderPosZ;
					
					if ((entity instanceof TileEntityChest) && chests.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1,
							1, 0.3, 0.3, 0.3, 1, 0.3, 0.3, 1);
					}
					
					if (entity instanceof TileEntityEnderChest && endchests.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1,
								1, 0.4, 1, 0.3, 1, 0.4, 1, 1);
					}
					
					if ((entity instanceof TileEntityFurnace) && furnaces.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}
					
					if (entity instanceof TileEntityDispenser && dispensers.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}
					
					if (entity instanceof TileEntityHopper && hoppers.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}
					
					if (entity instanceof TileEntityBrewingStand && brewstands.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}
				}
			}
			
			for (Object o : mc.theWorld.getLoadedEntityList()) {
				if(o instanceof Entity && o != mc.thePlayer) {
					Entity entity = (Entity) o;
					
					double posX = entity.posX - mc.getRenderManager().renderPosX;
					double posY = entity.posY - mc.getRenderManager().renderPosY;
					double posZ = entity.posZ - mc.getRenderManager().renderPosZ;
				
					if (entity instanceof EntityMinecartChest && chestcarts.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX - 0.3, posY + 0.3, posZ - 0.3, posX + 0.3, posY + 1, posZ + 0.3,
								1, 0.3, 0.3, 0.3, 1, 0.3, 0.3, 1);
					}
					
					if (entity instanceof EntityMinecartFurnace && furnacecarts.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX - 0.4, posY + 0.3, posZ - 0.4, posX + 0.4, posY + 1, posZ + 0.4,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}
					
					if (entity instanceof EntityMinecartHopper && hoppercarts.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX - 0.3, posY + 0.3, posZ - 0.3, posX + 0.3, posY + 1, posZ + 0.3,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}
					
					if (entity instanceof EntityArmorStand && armorstands.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX - 0.3, posY, posZ - 0.3, posX + 0.3, posY + 2, posZ + 0.3,
								0.3, 1, 0.6, 0.3, 0.3, 1, 0.6, 1);
					}
				}
			}
		}
	}
}