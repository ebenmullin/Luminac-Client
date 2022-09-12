package luminac.modules.render;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import luminac.util.render.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;

public class ESP extends Module {

	/** Credit to TouchPotato 2020 **/
	
	public static Timer timer = new Timer();
	public static NumberSetting range = new NumberSetting("Range", 3, 1, 6, 0.5);
	public static BooleanSetting animals = new BooleanSetting("Render Animals", false);
	public static BooleanSetting mobs = new BooleanSetting("Render Mobs", false);
	public static BooleanSetting players = new BooleanSetting("Render Players", true);
	
	public ESP() {
		super("ESP", Keyboard.KEY_RBRACKET, Category.RENDER);
		this.addSettings(range, animals, mobs, players);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRender3d) {
			for(Object o : mc.theWorld.getLoadedEntityList()){
				if(o instanceof EntityLivingBase && o != mc.thePlayer) {
					EntityLivingBase entity = (EntityLivingBase) o;
					
					double posX = entity.posX - mc.getRenderManager().renderPosX - entity.width / 2;
					double posY = entity.posY - mc.getRenderManager().renderPosY;
					double posZ = entity.posZ - mc.getRenderManager().renderPosZ - entity.width / 2;
					
					double posX2 = entity.posX - mc.getRenderManager().renderPosX + entity.width / 2;
					double posY2 = entity.posY - mc.getRenderManager().renderPosY + entity.height;
					double posZ2 = entity.posZ - mc.getRenderManager().renderPosZ + entity.width / 2;
				
					if (entity instanceof EntityPlayer && players.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX2, posY2, posZ2,
								1, 0.3, 0.3, 0.3, 1, 0.3, 0.3, 1);
					}
					
					if (entity instanceof EntityAnimal && animals.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX2, posY2, posZ2,
								0.3, 0.5, 1, 0.3, 0.3, 0.5, 1, 1);
					}

					if (entity instanceof EntityMob && mobs.isEnabled()) {
						RenderUtils.drawFilledBox(true, posX, posY, posZ, posX2, posY2, posZ2,
								0.3, 1, 0.6, 0.3, 0.3, 1, 0.6, 1);
					}
				}
			}
		}
	}
}
