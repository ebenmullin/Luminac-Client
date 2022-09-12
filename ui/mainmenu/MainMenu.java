package luminac.ui.mainmenu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import luminac.Client;
import luminac.ui.ImageButtons;
import luminac.ui.alts.GuiAltManager;
import luminac.ui.clickgui.ClickGUI;
import luminac.util.ColorUtil;
import luminac.util.ExtraEffectsMenu;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu extends GuiScreen {
	
	public MainMenu() {
		
	}
	public void initGui() {
		
	}
	
	public ArrayList<ImageButtons> imageButtons = new ArrayList<ImageButtons>();
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
		TTFFontRenderer title = Client.fontManager.getFont("comfortaa 128");
		mc.getTextureManager().bindTexture(new ResourceLocation("luminac/mainMenu/wallpaper6.png"));
		
		//if (mc.entityRenderer.theShaderGroup != null) {
		//	mc.entityRenderer.theShaderGroup.deleteShaderGroup();
		//}
		//mc.entityRenderer.func_175069_a(new ResourceLocation("shaders/post/blur.json"));
				
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f, height/2f, 0);
		GlStateManager.scale(1.1, 1.1, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		Gui.drawModalRectWithCustomSizedTexture((mouseX/20) - 18, (mouseY/20) - 20, 0, 0, width, height, width, height);
		GlStateManager.popMatrix();
		
		drawGradientRect(0, height - 150, width, height, 0x0000f0f0, ColorUtil.getRainbow(5, 0.8f, 1));
		//Top//
		drawRect(width, 20, - width, 0, 0xff555555);
		drawRect(width, 20, - width, 18, -1);
		fr.drawString(Client.clientName + "§f v" + Client.clientVersion + " By TouchPotato",
			sr.getScaledWidth() - fr.getWidth(Client.clientName + "§f v" + Client.clientVersion +
				" By TouchPotato") - 6, 9 - fr.getHeight(Client.clientName) / 2, ColorUtil.getRainbow(5, 0.6f, 1));
		
		fr.drawString("User: " + mc.session.getUsername(), 6, 9 - mc.fontRendererObj.FONT_HEIGHT/2, -1);
		
		title.drawString(Client.clientName, width/2 - title.getWidth(Client.clientName)/2, height/2 - title.getHeight(Client.clientName)/2, -1);
		
		//Bottom//
		drawRect(width, height - 50, - width, height, 0xff555555);
		drawRect(width, height - 50, - width, height - 48, -1);
		
		for(ImageButtons imageButton : imageButtons) {
            imageButton.draw(mouseX, mouseY, new Color(255, 255, 255));
        }
			
		
			int scale = sr.getScaledWidth() / 7;
			
			imageButtons.clear();
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/singleplayer.png"),
				scale - 24, sr.getScaledHeight() - 48, 48, 48, 1));
			
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/multiplayer.png"),
					scale * 2 - 24, sr.getScaledHeight() - 48, 48, 48, 2));
			
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/settings.png"),
					scale * 3 - 24, sr.getScaledHeight() - 48, 48, 48, 3));
			
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/language.png"),
					scale * 4 - 24, sr.getScaledHeight() - 48, 48, 48, 4));
			
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/altManager.png"),
					scale * 5 - 24, sr.getScaledHeight() - 48, 48, 48, 5));
			
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/quit.png"),
					scale * 6 - 24, sr.getScaledHeight() - 48, 48, 48, 6));
			
			imageButtons.add(new ImageButtons(new ResourceLocation("luminac/mainMenu/imageButtons/colapse.png"),
					sr.getScaledWidth() / 2 - 8, 1, 16, 16, 7));
			
		/**
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f, height/2f, 0);
		GlStateManager.scale(10, 10, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		fr.drawString(Client.clientName, width/2f - fr.getStringWidth(Client.clientName)/2, height/2f - mc.fontRendererObj.FONT_HEIGHT/2, -1);
		drawCenteredString(mc.fontRendererObj, Client.clientName, width/2f, height/2f - mc.fontRendererObj.FONT_HEIGHT/2, -1);
		GlStateManager.popMatrix();
		*/

	}	
	public void mouseClicked(int mouseX, int mouseY, int button) {
		
		for(ImageButtons imageButton : imageButtons) {
            imageButton.onClick(mouseX, mouseY);
        }
	}
	
	public void onGuiClosed() {
		
	}
}

