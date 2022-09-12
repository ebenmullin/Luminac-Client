package luminac.ui;

import java.awt.Color;
import luminac.ui.alts.GuiAltManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ImageButtons {
  private ResourceLocation image;
  
  public int x;
  
  public int y;
  
  public int width;
  
  public int height;
  
  public int hoverTransition = 0;
  
  public int target;
  
  public Minecraft mc = Minecraft.getMinecraft();
  
  public String desc;
  
  public ImageButtons(ResourceLocation image, int x, int y, int width, int height, int target) {
    this.image = image;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.target = target;
  }
  
  public static void drawCustomImage(ResourceLocation image, int x, int y, int width, int height, Color color) {
    GL11.glDisable(2929);
    GL11.glEnable(3042);
    GL11.glDepthMask(false);
    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    GL11.glColor4f(color.getRed() / 255.0F, color.getBlue() / 255.0F, color.getGreen() / 255.0F, color.getAlpha());
    Minecraft.getMinecraft().getTextureManager().bindTexture(image);
    Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, width, height, width, height);
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glEnable(2929);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
  }
  
  public void draw(int mouseX, int mouseY, Color i) {
    hoverAnim(mouseX, mouseY);
    if (this.hoverTransition > 0) {
      GL11.glPushMatrix();
      drawCustomImage(this.image, this.x - this.hoverTransition * 5, this.y - this.hoverTransition * 5, 
          this.width + this.hoverTransition * 10, this.height + this.hoverTransition * 10, i);
      GL11.glPopMatrix();
    } else {
      drawCustomImage(this.image, this.x, this.y, this.width, this.height, i);
    } 
  }
  
  public void onClick(int mouseX, int mouseY) {
    if (isHovered(mouseX, mouseY))
      if (this.target == 1) {
        this.mc.displayGuiScreen((GuiScreen)new GuiSelectWorld(this.mc.currentScreen));
      } else if (this.target == 2) {
        this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer(this.mc.currentScreen));
      } else if (this.target == 3) {
        this.mc.displayGuiScreen((GuiScreen)new GuiOptions(this.mc.currentScreen, this.mc.gameSettings));
      } else if (this.target == 4) {
        this.mc.displayGuiScreen((GuiScreen)new GuiLanguage(this.mc.currentScreen, this.mc.gameSettings, this.mc.getLanguageManager()));
      } else if (this.target == 5) {
        this.mc.displayGuiScreen((GuiScreen)new GuiAltManager());
      } else if (this.target == 6) {
        this.mc.shutdown();
      } else if (this.target == 6) {
        this.mc.shutdown();
      }  
  }
  
  public void hoverAnim(int mouseX, int mouseY) {
    if (isHovered(mouseX, mouseY)) {
      if (this.hoverTransition < 4)
        this.hoverTransition++; 
    } else if (this.hoverTransition > 0) {
      this.hoverTransition--;
    } 
  }
  
  public boolean isHovered(int mouseX, int mouseY) {
    return isHovered(this.x, this.y, this.x + this.width, this.y + this.height, mouseX, mouseY);
  }
  
  public int getTargetButton() {
    return this.target;
  }
  
  public boolean isHovered(int x, int y, int width, int height, int mouseX, int mouseY) {
    return (mouseX > x && mouseY > y && mouseX < width && mouseY < height);
  }
}
