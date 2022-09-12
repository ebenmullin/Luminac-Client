package luminac.ui;

import java.util.Comparator;

import luminac.Client;
import luminac.events.listeners.EventRenderGUI;
import luminac.modules.Module;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
import luminac.util.ColorUtil;
import luminac.util.font.TTFFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class HUD extends GuiScreen {

	public static NumberSetting red = new NumberSetting("Red", 255, 0, 255, 1);
	public static NumberSetting green = new NumberSetting("Green", 255, 0, 255, 1);
	public static NumberSetting blue = new NumberSetting("Blue", 255, 0, 255, 1);
	public static NumberSetting opacity = new NumberSetting("Opacity", 90, 0, 255, 10);
	public static ModeSetting color = new ModeSetting("RainBow", "RainBow", "Color");

	public Minecraft mc = Minecraft.getMinecraft();

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);

		TTFFontRenderer fr = Client.fontManager.getFont("comfortaa 18");
		TTFFontRenderer title = Client.fontManager.getFont("comfortaa 32");

		Client.modules.sort(Comparator.comparingDouble(m -> fr.getWidth(((Module)m).name)).reversed());

		title.drawStringWithShadow(Client.clientName + "§f v" + Client.clientVersion, 5, 5, ColorUtil.getRainbow(5, 0.6f, 1f));

		int count = 0;

		for(Module m : Client.modules) {
			if(!m.toggled || m.name.equals("TabGUI") || m.name.equals("GuiSettings"))
				continue;

			Gui.drawRect(sr.getScaledWidth() - fr.getWidth(m.name) - 7, (int) (count*(fr.getHeight(m.name) + 2)), sr.getScaledWidth(), 2 + fr.getHeight(m.name) + count*(fr.getHeight(m.name) + 2), 0x90000000);
			Gui.drawRect(sr.getScaledWidth() - fr.getWidth(m.name) - 9, count*(fr.getHeight(m.name) + 2), sr.getScaledWidth() - fr.getWidth(m.name) - 7, 2 + fr.getHeight(m.name) + count*(fr.getHeight(m.name) + 2), ColorUtil.getRainbow(5, 0.6f, 1f, count * 80));
			fr.drawStringWithShadow(m.name, sr.getScaledWidth() - fr.getWidth(m.name) - 4, 2 + count*(fr.getHeight(m.name) + 2), ColorUtil.getRainbow(5, 0.6f, 1f, count * 80));
			count++;
		}
		Client.onEvent(new EventRenderGUI());
	}		
}
	
	 
	

