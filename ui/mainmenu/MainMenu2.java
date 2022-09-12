package luminac.ui.mainmenu;

import java.awt.Color;
import java.util.ArrayList;

import luminac.Client;
import luminac.ui.ImageButtons;
import luminac.util.ColorUtil;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MainMenu2 extends GuiScreen {
	
	public MainMenu2() {
		
	}
	public void initGui() {
		
	}
	
	public ArrayList<ImageButtons> imageButtons = new ArrayList<ImageButtons>();
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		mc.getTextureManager().bindTexture(new ResourceLocation("mainmenu/wallpaper10.jpg"));
		
		//if (mc.entityRenderer.theShaderGroup != null) {
		//	mc.entityRenderer.theShaderGroup.deleteShaderGroup();
		//}
		//mc.entityRenderer.func_175069_a(new ResourceLocation("shaders/post/blur.json"));
				
		GlStateManager.pushMatrix();
		GlStateManager.translate(width/2f, height/2f, 0);
		GlStateManager.scale(1.1, 1.1, 1);
		GlStateManager.translate(-(width/2f), -(height/2f), 0);
		this.drawModalRectWithCustomSizedTexture((mouseX/20) - 18, (mouseY/20) - 20, 0, 0, this.width, this.height, this.width, this.height);
		GlStateManager.popMatrix();
		
		/**
		GlStateManager.pushMatrix();
		GlStateManager.scale(0.5, 0.5, 1);
		drawString(fontRendererObj, "Singleplayer", sr.getScaledWidth() / 2 - 20, sr.getScaledHeight() / 2, -1);
		drawString(fontRendererObj, "Multiplayer", sr.getScaledWidth() / 2 + 200, sr.getScaledHeight() / 2, -1);
		drawString(fontRendererObj, "Settings", sr.getScaledWidth() / 2 + 200, sr.getScaledHeight() / 2, -1);
		drawString(fontRendererObj, "Language", sr.getScaledWidth() / 2 + 200, sr.getScaledHeight() / 2, -1);
		drawString(fontRendererObj, "AltManager", sr.getScaledWidth() / 2 + 200, sr.getScaledHeight() / 2, -1);
		GlStateManager.popMatrix();
		*/
		
		this.drawGradientRect(0, height - 150, width, height, 0x0000f0f0, ColorUtil.getRainbow(5, 0.8f, 1));
		//Top//
		this.drawRect(width, 20, - width, 0, 0xff555555);
		this.drawRect(width, 20, - width, 18, -1);
		this.drawString(fontRendererObj, Client.clientName + "§f v" + Client.clientVersion + " By TouchPotato",
			sr.getScaledWidth() - mc.fontRendererObj.getStringWidth(Client.clientName + "§f v" + Client.clientVersion +
				" By TouchPotato") - 6, 9 - mc.fontRendererObj.FONT_HEIGHT/2, ColorUtil.getRainbow(5, 0.6f, 1));
		
		this.drawString(fontRendererObj, "User: " + this.mc.session.getUsername(), 6, 9 - mc.fontRendererObj.FONT_HEIGHT/2, -1);
		
		//Bottom//
		this.drawRect(width, height - 50, - width, height, 0xff555555);
		this.drawRect(width, height - 50, - width, height - 48, -1);
		
		for(ImageButtons imageButton : this.imageButtons) {
            imageButton.draw(mouseX, mouseY, new Color(255, 255, 255));
        }
			
		
			int scale = sr.getScaledWidth() / 7;
			
			this.imageButtons.clear();
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Singleplayer.png"),
				sr.getScaledWidth() / 2 - 96, sr.getScaledHeight() / 2 - 32, 32, 32, 1));
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Multiplayer.png"),
				sr.getScaledWidth() / 2 - 56, sr.getScaledHeight() / 2 - 32, 32, 32, 2));
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Settings.png"),
				sr.getScaledWidth() / 2 - 16, sr.getScaledHeight() / 2 - 32, 32, 32, 3));
			
			//this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Language.png"),
				//sr.getScaledWidth() / 2 + 32, sr.getScaledHeight() / 2 - 32, 32, 32, 4));
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/AltManager.png"),
					sr.getScaledWidth() / 2 + 24, sr.getScaledHeight() / 2 - 32, 32, 32, 5));
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Quit.png"),
					sr.getScaledWidth() / 2 + 64, sr.getScaledHeight() / 2 - 32, 32, 32, 6));
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Colapse.png"),
				sr.getScaledWidth() / 2 - 8, 1, 16, 16, 7));
			
			this.imageButtons.add(new ImageButtons(new ResourceLocation("MainMenu/ImageButtons/Title.png"),
					sr.getScaledWidth() / 2 - 100, sr.getScaledHeight() / 2 - 100, 200, 100, 7));
			
	}	
	public void mouseClicked(int mouseX, int mouseY, int button) {
		
		for(ImageButtons imageButton : this.imageButtons) {
            imageButton.onClick(mouseX, mouseY);
        }
	}
	
	public void onGuiClosed() {
		
	}
}



