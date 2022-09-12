package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventRenderGUI;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.settings.GameSettings;

public class Hotbar extends Module {
	

	public static ModeSetting design = new ModeSetting("Hotbar Design", "Simple", "Simple", "Default", "Small");
	public BooleanSetting nether = new BooleanSetting("Nether Coords", false);
	public BooleanSetting world = new BooleanSetting("World Coords", true);
	public BooleanSetting fps = new BooleanSetting("FPS", true);
	
	public GuiChat chat;
	
	public Hotbar() {
		
		super("Hotbar", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(design, world, fps, nether);
		toggled = true;
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventRenderGUI) {
			ScaledResolution sr = new ScaledResolution(mc);
			TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
			
			double posX = Math.round(mc.thePlayer.posX);
			double posY = Math.round(mc.thePlayer.posY);
			double posZ = Math.round(mc.thePlayer.posZ);
			
			double pitch =  Math.round(mc.thePlayer.rotationPitch);
	 		double yaw =  Math.round(mc.thePlayer.rotationYaw);
			if(mc.currentScreen == chat) {
				if(world.isEnabled()) {
					fr.drawStringWithShadow("X: §f" + posX, 2, sr.getScaledHeight() - 21 + 1.5F, ColorUtil.getRainbow(5, 0.6f, 1));
			        fr.drawStringWithShadow("Y: §f" + posY, 8 + mc.fontRendererObj.getStringWidth("X: §f" + posX), sr.getScaledHeight() - 21 + 1.5F, ColorUtil.getRainbow(5, 0.6f, 1));
			        fr.drawStringWithShadow("Z: §f" + posZ, 16 + mc.fontRendererObj.getStringWidth("X: §f" + posX) + mc.fontRendererObj.getStringWidth("Y: §f" + posY), sr.getScaledHeight() - 21 + 1.5F, ColorUtil.getRainbow(5, 0.6f, 1));
				}
				
				if(nether.isEnabled()) {
					fr.drawStringWithShadow("Nether X: §f" + posX, 2, sr.getScaledHeight() - 21 + 1.5F, ColorUtil.getRainbow(5, 0.6f, 1));
			        fr.drawStringWithShadow("Y: §f" + posY, 8 + mc.fontRendererObj.getStringWidth("Nether X: §f" + posX), sr.getScaledHeight() - 21 + 1.5F, ColorUtil.getRainbow(5, 0.6f, 1));
			        fr.drawStringWithShadow("Z: §f" + posZ, 16 + mc.fontRendererObj.getStringWidth("Nether X: §f" + posX) + mc.fontRendererObj.getStringWidth("Y: §f" + posY), sr.getScaledHeight() - 21 + 1.5F, ColorUtil.getRainbow(5, 0.6f, 1));
				}
				
				if(fps.isEnabled()) {
					fr.drawStringWithShadow("FPS: §f" + mc.debugFPS, 2, sr.getScaledHeight() - mc.fontRendererObj.FONT_HEIGHT, ColorUtil.getRainbow(5, 0.6f, 1));
				}
			}
		}
	}
}	