package luminac.modules.misc;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventKey;
import luminac.events.listeners.EventRenderGUI;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.ui.GuiDrag;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import luminac.util.render.ShapeUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class KeyStrokes extends Module {
	
	public ModeSetting design = new ModeSetting("Design", "Rounded", "Rounded", "Hollow");
	public NumberSetting size = new NumberSetting("Gui Size", 1, 0.5, 2, 0.1);
	public NumberSetting radius = new NumberSetting("corener radius", 5, 1, 200, 0.5);
	
	public KeyStrokes() {
		super("KeyStrokes", Keyboard.KEY_NONE, Category.MISC);
		this.addSettings(design, size, radius);
	}
		
	public void onEvent(Event e) {	
		if(e instanceof EventRenderGUI) {
			ScaledResolution sr = new ScaledResolution(mc);
			TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
			if(design.getMode() == "Rounded") {
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 67, sr.getScaledHeight() - 160, sr.getScaledWidth() - 37, sr.getScaledHeight() - 135, mc.gameSettings.keyBindForward.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("W", sr.getScaledWidth() - 52 - fr.getWidth("W") / 2, sr.getScaledHeight() - 147.5 - fr.getHeight("W") / 2, mc.gameSettings.keyBindForward.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 133, sr.getScaledWidth() - 69, sr.getScaledHeight() - 108, mc.gameSettings.keyBindLeft.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("A", sr.getScaledWidth() - 84 - fr.getWidth("A") / 2, sr.getScaledHeight() - 120.5 - fr.getHeight("A") / 2, mc.gameSettings.keyBindLeft.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 67, sr.getScaledHeight() - 133, sr.getScaledWidth() - 37, sr.getScaledHeight() - 108, mc.gameSettings.keyBindBack.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("S", sr.getScaledWidth() - 52 - fr.getWidth("S") / 2, sr.getScaledHeight() - 120.5 - fr.getHeight("S") / 2, mc.gameSettings.keyBindBack.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 35, sr.getScaledHeight() - 133, sr.getScaledWidth() - 5, sr.getScaledHeight() - 108, mc.gameSettings.keyBindRight.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("D", sr.getScaledWidth() - 20 - fr.getWidth("D") / 2, sr.getScaledHeight() - 120.5 - fr.getHeight("D") / 2, mc.gameSettings.keyBindRight.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 106, sr.getScaledWidth() - 53, sr.getScaledHeight() - 81, mc.gameSettings.keyBindAttack.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("LMB", sr.getScaledWidth() - 76 - fr.getWidth("LMB") / 2, sr.getScaledHeight() - 93.5 - fr.getHeight("LMB") / 2, mc.gameSettings.keyBindAttack.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 51, sr.getScaledHeight() - 106, sr.getScaledWidth() - 5, sr.getScaledHeight() - 81, mc.gameSettings.keyBindUseItem.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("RMB", sr.getScaledWidth() - 32 - fr.getWidth("RMB") / 2, sr.getScaledHeight() - 93.5 - fr.getHeight("RMB") / 2, mc.gameSettings.keyBindUseItem.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 79, sr.getScaledWidth() - 5, sr.getScaledHeight() - 54, mc.gameSettings.keyBindSneak.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("Sneak", sr.getScaledWidth() - 52 - fr.getWidth("Sneak") / 2, sr.getScaledHeight() - 66.5 - fr.getHeight("Sneak") / 2, mc.gameSettings.keyBindSneak.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawRoundedRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 52, sr.getScaledWidth() - 5, sr.getScaledHeight() - 27, mc.gameSettings.keyBindJump.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000);
				fr.drawString("Jump", sr.getScaledWidth() - 52 - fr.getWidth("Jump") / 2, sr.getScaledHeight() - 39.5 - fr.getHeight("Jump") / 2, mc.gameSettings.keyBindJump.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
					
			} else if(design.getMode() == "Hollow") {
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 67, sr.getScaledHeight() - 160, sr.getScaledWidth() - 37, sr.getScaledHeight() - 135, mc.gameSettings.keyBindForward.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("W", sr.getScaledWidth() - 52 - fr.getWidth("W") / 2, sr.getScaledHeight() - 147.5 - fr.getHeight("W") / 2, mc.gameSettings.keyBindForward.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 133, sr.getScaledWidth() - 69, sr.getScaledHeight() - 108, mc.gameSettings.keyBindLeft.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("A", sr.getScaledWidth() - 84 - fr.getWidth("A") / 2, sr.getScaledHeight() - 120.5 - fr.getHeight("A") / 2, mc.gameSettings.keyBindLeft.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 67, sr.getScaledHeight() - 133, sr.getScaledWidth() - 37, sr.getScaledHeight() - 108, mc.gameSettings.keyBindBack.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("S", sr.getScaledWidth() - 52 - fr.getWidth("S") / 2, sr.getScaledHeight() - 120.5 - fr.getHeight("S") / 2, mc.gameSettings.keyBindBack.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 35, sr.getScaledHeight() - 133, sr.getScaledWidth() - 5, sr.getScaledHeight() - 108, mc.gameSettings.keyBindRight.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("D", sr.getScaledWidth() - 20 - fr.getWidth("D") / 2, sr.getScaledHeight() - 120.5 - fr.getHeight("D") / 2, mc.gameSettings.keyBindRight.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 106, sr.getScaledWidth() - 53, sr.getScaledHeight() - 81, mc.gameSettings.keyBindAttack.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("LMB", sr.getScaledWidth() - 76 - fr.getWidth("LMB") / 2, sr.getScaledHeight() - 93.5 - fr.getHeight("LMB") / 2, mc.gameSettings.keyBindAttack.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 51, sr.getScaledHeight() - 106, sr.getScaledWidth() - 5, sr.getScaledHeight() - 81, mc.gameSettings.keyBindUseItem.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("RMB", sr.getScaledWidth() - 32 - fr.getWidth("RMB") / 2, sr.getScaledHeight() - 93.5 - fr.getHeight("RMB") / 2, mc.gameSettings.keyBindUseItem.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 79, sr.getScaledWidth() - 5, sr.getScaledHeight() - 54, mc.gameSettings.keyBindSneak.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("Sneak", sr.getScaledWidth() - 52 - fr.getWidth("Sneak") / 2, sr.getScaledHeight() - 66.5 - fr.getHeight("Sneak") / 2, mc.gameSettings.keyBindSneak.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
				
				GuiDrag.drawHollowRect(sr.getScaledWidth() - 99, sr.getScaledHeight() - 52, sr.getScaledWidth() - 5, sr.getScaledHeight() - 27, mc.gameSettings.keyBindJump.isKeyDown() ? ColorUtil.getRainbow(5, 0.6f, 0.9f) : 0x90000000, 0x70000000);
				fr.drawString("Jump", sr.getScaledWidth() - 52 - fr.getWidth("Jump") / 2, sr.getScaledHeight() - 39.5 - fr.getHeight("Jump") / 2, mc.gameSettings.keyBindJump.isKeyDown() ? -1 : ColorUtil.getRainbow(5, 0.6f, 0.9f));
			}
		}
	}
}