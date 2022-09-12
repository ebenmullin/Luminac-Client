package luminac.ui.clickgui.elements.menu;

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.MathHelper;
import luminac.settings.Setting;
import luminac.ui.clickgui.elements.Element;
import luminac.ui.clickgui.elements.ModuleButton;
import luminac.util.ColorUtil;

public class ElementValue extends Element {
	public boolean dragging;

	public ElementValue(ModuleButton iparent, Setting iset) {
		parent = iparent;
		set = iset;
		dragging = false;
		super.setup();
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		String displayval = "" + Math.round(set.getValDouble() * 100D)/ 100D;
		boolean hoveredORdragged = isSliderHovered(mouseX, mouseY) || dragging;
		
		Color temp = ColorUtil.clickGuiColor();
		int color = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 250 : 200).getRGB();
		int color2 = new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), hoveredORdragged ? 255 : 230).getRGB();
		
		double percentBar = (set.getValDouble() - set.getMin())/(set.getMax() - set.getMin());

		Gui.drawRect(x, y, x + width, y + height, 0xff1a1a1a);

		fr.drawString(setstrg, x + 1, y + 2, 0xffffffff);
		fr.drawString(displayval, x + width - fr.getStringWidth(displayval), y + 2, 0xffffffff);

		Gui.drawRect(x, y + 12, x + width, y + 13.5, 0xff101010);
		Gui.drawRect(x, y + 12, x + (percentBar * width), y + 13.5, color);
		
		if(percentBar > 0 && percentBar < 1)
		Gui.drawRect(x + (percentBar * width)-1, y + 12, x + Math.min((percentBar * width), width), y + 13.5, color2);

		if (this.dragging) {
			double diff = set.getMax() - set.getMin();
			double val = set.getMin() + (MathHelper.clamp_double((mouseX - x) / width, 0, 1)) * diff;
			set.setValDouble(val);
		}

	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0 && isSliderHovered(mouseX, mouseY)) {
			this.dragging = true;
			return true;
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {
		this.dragging = false;
	}

	public boolean isSliderHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y + 11 && mouseY <= y + 14;
	}
}