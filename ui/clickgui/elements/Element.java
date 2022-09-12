package luminac.ui.clickgui.elements;

import luminac.settings.Setting;
import luminac.ui.clickgui.ClickGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class Element {

	//OFFICIAL
	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer fr = mc.fontRendererObj;

	public ClickGUI clickgui;
	public ModuleButton parent;
	public Setting set;
	public double offset;
	public double x;
	public double y;
	public double width;
	public double height;

	public String setstrg;

	public boolean comboextended;

	public void setup() {
		clickgui = parent.parent.clickgui;
	}

	public void update() {

		x = parent.x + parent.width + 2;
		y = parent.y + offset;
		width = parent.width + 10;
		height = 15;

		String sname = set.getName();
		if(set.isCheck()){
			setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1, sname.length());
			double textx = x + width - fr.getStringWidth(setstrg);
			if (textx < x + 13) {
				width += (x + 13) - textx + 1;
			}
		}else if(set.isCombo()){
			height = comboextended ? set.getOptions().size() * (fr.FONT_HEIGHT + 2) + 15 : 15;

			setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1, sname.length());
			int longest = fr.getStringWidth(setstrg);
			for (String s : set.getOptions()) {
				int temp = fr.getStringWidth(s);
				if (temp > longest) {
					longest = temp;
				}
			}
			double textx = x + width - longest;
			if (textx < x) {
				width += x - textx + 1;
			}
		}else if(set.isSlider()){
			setstrg = sname.substring(0, 1).toUpperCase() + sname.substring(1, sname.length());
			String displayval = "" + Math.round(set.getValDouble() * 100D)/ 100D;
			String displaymax = "" + Math.round(set.getMax() * 100D)/ 100D;
			double textx = x + width - fr.getStringWidth(setstrg) - fr.getStringWidth(displaymax) - 4;
			if (textx < x) {
				width += x - textx + 1;
			}
		}
	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		return isHovered(mouseX, mouseY);
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {

	}

	public boolean isHovered(int mouseX, int mouseY) {
		System.out.println("Testing inside elements and hovering");

		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
}
