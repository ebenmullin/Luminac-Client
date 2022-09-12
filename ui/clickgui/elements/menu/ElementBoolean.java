package luminac.ui.clickgui.elements.menu;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import luminac.settings.Setting;
import luminac.ui.clickgui.elements.Element;
import luminac.ui.clickgui.elements.ModuleButton;
import luminac.util.ColorUtil;

public class ElementBoolean extends Element {

	public ElementBoolean(ModuleButton iparent, Setting iset) {
		parent = iparent;
		set = iset;
		super.setup();
	}

	public void drawScreen(double mouseX, double mouseY, float partialTicks) {
		Minecraft mc = Minecraft.getMinecraft();
		FontRenderer fr = mc.fontRendererObj;

		Color temp = ColorUtil.clickGuiColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 200).getRGB();

		Gui.drawRect(x, y, x + width, y + height, 0xff1a1a1a);

		fr.drawString(setstrg, x + width - fr.getStringWidth(setstrg), y + fr.FONT_HEIGHT / 2 - 0.5, 0xffffffff);
		Gui.drawRect(x + 1, y + 2, x + 12, y + 13, set.getValBoolean() ? color : 0xff000000);
		if (isCheckHovered(mouseX, mouseY))
			Gui.drawRect(x + 1, y + 2, x + 12, y + 13, 0x55111111);
	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0 && isCheckHovered(mouseX, mouseY)) {
			set.setValBoolean(!set.getValBoolean());
			return true;
		}

		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public boolean isCheckHovered(double mouseX, double mouseY) {
		return mouseX >= x + 1 && mouseX <= x + 12 && mouseY >= y + 2 && mouseY <= y + 13;
	}
}
