package luminac.modules.render;

import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.events.listeners.EventRenderGUI;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import luminac.util.render.RenderUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;

public class Tracers extends Module {

	/** Credit to TouchPotato 2020 **/
	
	public Timer timer = new Timer();
	public NumberSetting distance = new NumberSetting("distance", 3, 1, 6, 0.5);
	public NumberSetting width = new NumberSetting("Line width", 1, 0.1, 3, 0.1);
	public BooleanSetting animals = new BooleanSetting("Render Animals", false);
	public BooleanSetting mobs = new BooleanSetting("Render Mobs", false);
	public BooleanSetting players = new BooleanSetting("Render Players", true);
		
	public Tracers() {
		super("Tracers", Keyboard.KEY_RBRACKET, Category.RENDER);
		this.addSettings(distance, width, animals, mobs, players);
	}
	
	public void onEnable() {
		mc.gameSettings.viewBobbing = false;
	}
	
	public void onEvent(Event e){
		if(e instanceof EventRender3d) {
			for(Object o : mc.theWorld.getLoadedEntityList()){
				if(o instanceof EntityLivingBase && o != mc.thePlayer) {
					EntityLivingBase entity = (EntityLivingBase) o;
					
					double playerX = 0;
					double playerY = mc.thePlayer.isSneaking() ? mc.thePlayer.height - 0.26 : mc.thePlayer.height - 0.18;
					double playerZ = 0;
					
					double posX = (entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosX;
					double posY = (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosY;
					double posZ = (entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * mc.timer.renderPartialTicks) - mc.getRenderManager().renderPosZ;
					
					
						if ((entity instanceof EntityPlayer) && players.isEnabled()) {
							RenderUtils.drawTracerLine(posX, posY + entity.height / 2, posZ, playerX, playerY, playerZ,
								1, 0.3f, 0.4f, 1, (float) width.getValue());
						}
						
						if ((entity instanceof EntityAnimal) && animals.isEnabled()) {
							RenderUtils.drawTracerLine(posX, posY + entity.height / 2, posZ, playerX, playerY, playerZ,
								0.3f, 1, 0.4f, 1, (float) width.getValue());
						}
						
						if ((entity instanceof EntityMob) && mobs.isEnabled()) {
							RenderUtils.drawTracerLine(posX, posY + entity.height / 2, posZ, playerX, playerY, playerZ,
								0.3f, 0.4f, 1, 1, (float) width.getValue());
					}
				}
			}
		}
	}
}
	
