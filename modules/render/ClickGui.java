package luminac.modules.render;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.ModeSetting;
import luminac.settings.NumberSetting;
//import luminac.ui.clickgui.ClickGUI;
import net.minecraft.client.Minecraft;

public class ClickGui extends Module {
    private final Minecraft mc = Minecraft.getMinecraft();

    public static ModeSetting theme = new ModeSetting("Mode", "Discord", "Discord", "Rainbow", "Custom");
    public static BooleanSetting blur = new BooleanSetting("Blur", true);
    public static BooleanSetting sound = new BooleanSetting("Sound", false);
    public static NumberSetting opacity = new NumberSetting("Opacity", 0.8, 0, 1, 0.01),
    	red = new NumberSetting("Red", 255, 0, 255, 1),
    	green = new NumberSetting("Green", 200, 0, 255, 1),
    	blue = new NumberSetting("Blue", 0, 0, 255, 1),
    	size = new NumberSetting("Size", 1, 0.5, 5, 0.1);

    public ClickGui() {
        super("ClickGUI", Keyboard.KEY_RSHIFT, Category.RENDER);
        addSettings(theme, blur, sound, opacity, red, green, blue, size);
    }

    public void onDisable() {

    }

    public void onEnable() {
    	mc.displayGuiScreen(Client.clickgui);
    }

    public void onEvent(Event e) {
        if(e instanceof EventUpdate) {
        	if(mc.currentScreen == null) {
        		toggled = false;
        	}
        }
    }
}
