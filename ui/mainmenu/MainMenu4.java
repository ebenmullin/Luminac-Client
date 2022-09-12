//package luminac.ui.mainmenu;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import luminac.ui.ImageButtons;
//import luminac.ui.alts.GuiAltManager;
//import luminac.util.ColorUtil;
//import luminac.util.font.Fonts;
//import luminac.util.font.MCFontRenderer;
//import luminac.util.gui.CustomButton;
//import luminac.util.gui.GuiUtils;
//import net.minecraft.client.gui.FontRenderer;
//import net.minecraft.client.gui.Gui;
//import net.minecraft.client.gui.GuiButton;
//import net.minecraft.client.gui.GuiLanguage;
//import net.minecraft.client.gui.GuiMultiplayer;
//import net.minecraft.client.gui.GuiOptions;
//import net.minecraft.client.gui.GuiScreen;
//import net.minecraft.client.gui.GuiSelectWorld;
//import net.minecraft.client.gui.GuiYesNo;
//import net.minecraft.client.gui.ScaledResolution;
//import net.minecraft.client.resources.I18n;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.world.demo.DemoWorldServer;
//import net.minecraft.world.storage.ISaveFormat;
//import net.minecraft.world.storage.WorldInfo;
//
//public class MainMenu4 extends GuiScreen {
//
//	//public GLSLSandboxShader backgroundShader;
//	public boolean HWID = false;
//
//	public ArrayList<ImageButtons> imageButtons = new ArrayList<ImageButtons>();
//
//	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
//		ScaledResolution sr = new ScaledResolution(mc);
//		FontRenderer fr = mc.fontRendererObj;
//		MCFontRenderer title = Fonts.arialFont(50, true, true);
//		//backgroundShader = new GLSLSandboxShader("/assets/minecraft/Fan/Shaders/noise.fsh");
//		mc.getTextureManager().bindTexture(new ResourceLocation("luminac/mainMenu/wallpaper6.png"));
//
//		drawGradientRect(0, 0, width, height, ColorUtil.getRainbow(5, 0.6f, 1, 50), ColorUtil.getRainbow(5, 0.6f, 1, 5000));
//
//		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
//		if(HWID = true) {
//			mc.displayGuiScreen(new MainMenu());
//		}
//
//		buttonList.add(new GuiButton(0, width / 2 - 100, 50, 98, 20, "menu.singleplayer"));
//        buttonList.add(new GuiButton(1, width / 2 + 2, 50, 98, 20, "menu.multiplayer"));
//        buttonList.add(new GuiButton(2, width / 2 - 100, 50 + 24 * 4, 98, 20, "menu.options"));
//        buttonList.add(new GuiButton(3, width / 2 - 100, 50 + 24 * 4, 98, 20, "Language"));
//        buttonList.add(new GuiButton(4, width / 2 - 100, 50 + 24, 98, 20, "AltManager"));
//        buttonList.add(new GuiButton(5, width / 2 - 100, 50 + 24 * 3, "Quit"));
//
//	}
//
//	public void initGui() {
//
//    }
//
//	protected void actionPerformed(GuiButton button) throws IOException {
//        if (button.id == 0) {
//        	mc.displayGuiScreen(new GuiSelectWorld(this));
//        }
//
//        if (button.id == 1) {
//        	mc.displayGuiScreen(new GuiMultiplayer(this));
//        }
//
//        if (button.id == 2) {
//        	mc.displayGuiScreen(new GuiOptions(this, mc.gameSettings));
//        }
//
//        if (button.id == 3) {
//        	mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
//        }
//
//        if (button.id == 3) {
//        	mc.displayGuiScreen(new GuiLanguage(this, mc.gameSettings, mc.getLanguageManager()));
//        }
//
//        if (button.id == 4) {
//            mc.displayGuiScreen(new GuiAltManager());
//        }
//
//        if (button.id == 5) {
//            mc.shutdown();
//        }
//    }
//
//
//	public void mouseClicked(int mouseX, int mouseY, int button) {
//
//	}
//
//	public void onGuiClosed() {
//
//	}
//}
