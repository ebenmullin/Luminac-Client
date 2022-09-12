package luminac.modules.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.ui.GuiDrag;
import luminac.util.render.RenderUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.scoreboard.Team.EnumVisible;

import java.awt.Color;

public class NameTags extends Module {
	
	public BooleanSetting health = new BooleanSetting("Health", true);
	public BooleanSetting distance = new BooleanSetting("Distance", false);
	public BooleanSetting armor = new BooleanSetting("Armor", true);
	public BooleanSetting background = new BooleanSetting("Background", true);
	public BooleanSetting item = new BooleanSetting("Held Item", true);
	public BooleanSetting walls = new BooleanSetting("Through Walls", true);
	public NumberSetting scale = new NumberSetting("Scale", 1, 1, 4, 0.1);
		
	public NameTags() {
		super("NameTags", Keyboard.KEY_NONE, Category.RENDER);
		this.addSettings(health, distance, armor, background, item, walls, scale);
	}
	
	public void onEnable() {
		//score.setNameTagVisibility(EnumVisible.NEVER);
	}
	
	public void onDisable() {
		//score.setNameTagVisibility(EnumVisible.ALWAYS);
	}

	public void onEvent(Event e) {
		if(e instanceof EventRender3d) {
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glPushMatrix();

        /**Disable lightning and depth test*/
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        /**Enable blend*/
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        for(EntityPlayer entity : mc.theWorld.playerEntities) {
        	if(entity != mc.thePlayer) {
        		if(!entity.canEntityBeSeen(mc.thePlayer) && !walls.isEnabled()) {
        			
        		}
        		
        		String entityHealth = null;
        		if(health.isEnabled()) {
        			entityHealth = Math.round(entity.getHealth() * 100 / entity.getMaxHealth()) + "%";
        		}
        		RenderUtils.drawNameTag(entity, distance.isEnabled(), health.isEnabled(), armor.isEnabled(), background.isEnabled(), scale.getValue());	
        	}

	       	GL11.glPopMatrix();
	        GL11.glPopAttrib();
	        GL11.glColor4f(1F, 1F, 1F, 1F);
        	}
		}
	}
}