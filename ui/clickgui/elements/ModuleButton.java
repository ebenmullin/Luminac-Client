package luminac.ui.clickgui.elements;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.modules.Module;
import luminac.modules.render.ClickGui;
import luminac.settings.Setting;
import luminac.ui.clickgui.Panel;
import luminac.ui.clickgui.elements.menu.ElementBoolean;
import luminac.ui.clickgui.elements.menu.ElementMode;
import luminac.ui.clickgui.elements.menu.ElementValue;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class ModuleButton {

	public Minecraft mc = Minecraft.getMinecraft();
 	TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");

	public Module module;
	public ArrayList<Element> menuelements;
	public Panel parent;
	public double x;
	public double y;
	public double width;
	public double height;
	public boolean expanded = false;
	public boolean listening = false;

	Color temp = ColorUtil.clickGuiColor();
    int customColor = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 255)).getRGB();

	public ModuleButton(Module imod, Panel pl) {
		System.out.println("ModuleButton Called");
		module = imod;

		parent = pl;
		menuelements = new ArrayList<>();

		if(Client.settingsManager.getSettingsByMod(imod) !=null) {
			for(Setting s : Client.settingsManager.getSettingsByMod(imod)) {
				if(s.isCheck()) {
					menuelements.add(new ElementBoolean(this, s));
				}else if(s.isSlider()) {
					menuelements.add(new ElementValue(this, s));
				}else if(s.isCombo()) {
					menuelements.add(new ElementMode(this, s));
				}
			}
		}

	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		System.out.println("DrawScreen moduielbutton");

		if(ClickGui.theme.getMode() == "Rainbow") {
			if(module.toggled) {
				Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, ColorUtil.getRainbow(5, 0.6f, 0.9f));
			}

			if(isHovered(mouseX, mouseY)) {
				Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, ColorUtil.getRainbow(5, 0.6f, 0.7f));
			}

		}else if(ClickGui.theme.getMode() == "Discord") {
			if(module.toggled) {
				Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, 0xff7289DA);
			}

			if(isHovered(mouseX, mouseY)) {
				Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, 0xff4a61b2);
			}

		}else if(ClickGui.theme.getMode() == "Custom") {
			if(module.toggled) {
				Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, customColor);
			}

			if(isHovered(mouseX, mouseY)) {
				Gui.drawRect(x - 2, y, x + width + 2, y + height + 1, customColor);
			}
		}

		fr.drawString(module.name, x + 1, y + height - 10, -1);
		mc.fontRendererObj.drawString(module.name, x + 1, y + height - 10, -1);
		if(height < 14) {
			height++;
		}
	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		System.out.println("mouse CXlicked ModuleButton");
		if(!isHovered(mouseX, mouseY))
			return false;

		if(mouseButton == 0) {
			module.toggled();
			System.out.println("Trying to toggle");

			if(ClickGui.sound.isEnabled()) {
				mc.thePlayer.playSound("random.click", 1, 1);
			}

		} else if(mouseButton == 1) {

			if(menuelements != null && menuelements.size() > 0) {
				boolean b = !this.expanded;
				Client.clickgui.closeAllSettings();
				this.expanded = b;

				if(ClickGui.sound.isEnabled()) {
					mc.thePlayer.playSound("random.click", 1, 1);
				}
			}
		}else if(mouseButton == 2) {
			System.out.println("Its Listening");
			listening = true;
		}
		return true;
	}

	public boolean keyTyped(char typedChar, int keyCode) throws IOException {
  		if(listening) {
  			if(keyCode != 1) {
  				Client.addChatMessage("Bound " + module.name + " to " + Keyboard.getKeyName(keyCode));
  				module.keyPressed.setKeyCode(keyCode);
  			}else {
  				Client.addChatMessage("Unbound " + module.name);
  				module.keyPressed.setKeyCode(Keyboard.KEY_NONE);

  			}
  			listening = false;
  			return true;

  		}
  		return false;

  	}

	public boolean isHovered(int mouseX, int mouseY) {
		System.out.println("ModuleButton IShovered");
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}

}

