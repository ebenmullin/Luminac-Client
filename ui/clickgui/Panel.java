package luminac.ui.clickgui;

import luminac.Client;
import luminac.modules.render.ClickGui;
import luminac.ui.GuiDrag;
import luminac.ui.clickgui.elements.ModuleButton;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.Color;
import java.util.ArrayList;

public class Panel {

	public Minecraft mc = Minecraft.getMinecraft();

	public String category;
	public double x;
	public double y;
	private double x2;
	private double y2;
	public double width;
	public double height;
	public boolean dragging;
	public boolean expanded;
	public boolean visible;
	public ArrayList<ModuleButton> Elements = new ArrayList<>();
	public ClickGUI clickgui;

	public Panel(String category, double ix, double iy, double iwidth, double iheight, boolean iexpanded, ClickGUI parent) {
        this.category = category;
		this.x = ix;
		this.y = iy;
		this.width = iwidth;
		this.height = iheight;
		this.expanded = iexpanded;
		this.dragging = false;
		this.visible = true;
		this.clickgui = parent;
		setup();
	}

	public void setup() {

	}

	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 24");

		if(!this.visible)
			return;

		if(this.dragging) {
			x = x2 + mouseX;
			y = y2 + mouseY;
		}

		Color temp = ColorUtil.clickGuiColor();
	    int customColor = (new Color(temp.getRed(), temp.getGreen(), temp.getBlue(), 255)).getRGB();
	    Double color = ClickGui.opacity.getValue();
	    int mainColor = 0x90202020; //OPACITY SETTINGS new Color(50, 70, 90, ColorUtil.guiOpacity())).getRGB();
	    int highlight = ((ClickGui.theme.getMode().equals("Discord")) ? 0xff7289DA :
	    	(ClickGui.theme.getMode().equals("Rainbow")) ? ColorUtil.getRainbow(5, 0.6f, 1f) :
	    		(ClickGui.theme.getMode().equals("Custom")) ? customColor : 0);

		Gui.drawRect(x - 2, y, x, y + height, mainColor);
	    GuiDrag.drawRoundedRect(x, y, x + width, y + height, highlight);

        mc.fontRendererObj.drawString(category, x + width / 2.0F - (fr.getWidth(category) / 2), y + height / 2.0F - (fr.getHeight(category) / 2), -1);

		if(this.expanded && !Elements.isEmpty()) {
			System.out.println("this.expanded and no elements");
			double startY = y + height;

			for(ModuleButton et : Elements) {
				Gui.drawRect(x, startY, x + width, startY + et.height + 1, mainColor);
				et.x = x + 2;
				et.y = startY;
				et.width = width - 4;
				et.drawScreen(mouseX, mouseY, partialTicks);
				startY += et.height + 1;
			}
		}
	}

	public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(!this.visible) {
			return false;
		}

		if(mouseButton == 0 && isHovered(mouseX, mouseY)) {
			x2 = this.x - mouseX;
			y2 = this.y - mouseY;
			dragging = true;
			return true;

		} else if(mouseButton == 1 && isHovered(mouseX, mouseY)) {
			expanded = !expanded;
			return true;

		} else if (expanded) {
			for (ModuleButton element : Elements) {
				if (element.mouseClicked(mouseX, mouseY, mouseButton)) {
					return true;
				}
			}
		}
		return false;
	}

	public void mouseReleased(int mouseX, int mouseY, int state) {
		if(!this.visible) {
			return;
		}

		if(state == 0) {
			this.dragging = false;
		}
	}

	public boolean isHovered(int mouseX, int mouseY) {
		return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
	}
}

