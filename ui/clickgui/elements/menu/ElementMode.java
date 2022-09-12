package luminac.ui.clickgui.elements.menu;

import java.awt.Color;

import luminac.Client;
import luminac.settings.Setting;
import luminac.ui.clickgui.elements.Element;
import luminac.ui.clickgui.elements.ModuleButton;
import luminac.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class ElementMode extends Element {

	public Minecraft mc = Minecraft.getMinecraft();

	public ElementMode(ModuleButton iparent, Setting iset) {
		parent = iparent;
		set = iset;
		super.setup();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		FontRenderer fr = mc.fontRendererObj;

		Color temp = ColorUtil.clickGuiColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 150).getRGB();

		Gui.drawRect(x, y, x + width, y + height, 0xff1a1a1a);

		fr.drawString(setstrg, x + width / 2, y + 15/2, -1);

		int clr1 = color;
		int clr2 = temp.getRGB();

		Gui.drawRect(x, y + 14, x + width, y + 15, 0x77000000);
		if (comboextended) {
			Gui.drawRect(x, y + 15, x + width, y + height, 0xaa121212);
			double ay = y + 15;
			for (String sld : set.getOptions()) {
				String elementtitle = sld.substring(0, 1).toUpperCase() + sld.substring(1, sld.length());
				fr.drawString(elementtitle, x + width / 2, ay + 2, 0xffffffff);

				if (sld.equalsIgnoreCase(set.getValString())) {
					Gui.drawRect(x, ay, x + 1.5, ay + fr.FONT_HEIGHT + 2, clr1);
				}

				if (mouseX >= x && mouseX <= x + width && mouseY >= ay && mouseY < ay + fr.FONT_HEIGHT + 2) {
					Gui.drawRect(x + width - 1.2, ay, x + width, ay + fr.FONT_HEIGHT + 2, clr2);
				}
				ay += fr.FONT_HEIGHT + 2;
			}
		}
	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			if (isButtonHovered(mouseX, mouseY)) {
				comboextended = !comboextended;
				return true;
			}

			if (!comboextended)return false;
			double ay = y + 15;
			for (String slcd : set.getOptions()) {
				if (mouseX >= x && mouseX <= x + width && mouseY >= ay && mouseY <= ay + fr.FONT_HEIGHT + 2) {
					if(Client.settingsManager.getSettingByName("Sound").getValBoolean())
					Minecraft.getMinecraft().thePlayer.playSound("tile.piston.in", 20.0F, 20.0F);

					if(clickgui != null && clickgui.settingsManager != null)
					clickgui.settingsManager.getSettingByName(set.getName()).setValString(slcd.toLowerCase());
					return true;
				}
				ay += fr.FONT_HEIGHT + 2;
			}
		}

		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public boolean isButtonHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + 15;
	}
}
