package luminac.modules.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.util.render.RenderUtils;

public class BreadCrumbs2 extends Module {
	
	public BreadCrumbs2() {
		super("BreadCrumbs", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(mc.thePlayer.motionX != 0 || mc.thePlayer.motionY != 0 || mc.thePlayer.motionZ != 0){
				//Resilience.getInstance().getValues().breadcrumbPosList.add(new Double[]{mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ});
				
			}
			
		} else if(e instanceof EventRender3d) {
			if(!isEnabled()) return;
			GL11.glPushMatrix();
			RenderUtils.setup3DLightlessModel();
			GL11.glLineWidth(1);
			GL11.glBegin(GL11.GL_LINE_STRIP);
			//for(Double[] pos : Resilience.getInstance().getValues().breadcrumbPosList) {
				GL11.glColor4f(0.0F, 0, 1.0F, 0.7f);
				//GL11.glVertex3d(pos[0] - mc.renderManager.renderPosX, pos[1]  - mc.renderManager.renderPosY - mc.thePlayer.getEntityHeight(Resilience.getInstance().getWrapper().getPlayer()), pos[2] - mc.renderManager.renderPosZ);
			//}
			GL11.glEnd();
			RenderUtils.shutdown3DLightlessModel();
			GL11.glPopMatrix();
		}
	}	
}
