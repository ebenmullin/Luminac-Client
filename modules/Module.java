package luminac.modules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import luminac.events.Event;
import luminac.settings.KeybindSetting;
import luminac.settings.Settings;
import luminac.ui.notifications.NotificationManager;
import luminac.ui.notifications.NotificationType;
import net.minecraft.client.Minecraft;

public class Module {
	
	public String name;
	public boolean toggled;
	public KeybindSetting keyPressed = new KeybindSetting(0);
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	protected static int categoryCount;
	
	public boolean expanded;
	public int index;
	public List<Settings> settings = new ArrayList<Settings>();
	
	public Module(String name, int key, Category cg) {
		this.name = name;
		keyPressed.code = key;
		this.category = cg;
		this.addSettings(keyPressed);
	}

	public void addSettings(Settings... settings) {
		this.settings.addAll(Arrays.asList(settings));
		this.settings.sort(Comparator.comparingInt(s -> s == keyPressed ? 1 : 0));
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public int getKey() {
		return keyPressed.code;
	}
	
	public void onEvent(Event e) {
		
	}
	
	public void toggled() {
		toggled = !toggled;
		if(toggled) {
			onEnable();
				
		} else {
			onDisable();
		}
		
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public enum Category {
        COMBAT("Combat"),
        MOVEMENT("Movement"),
        RENDER("Render"),
        PLAYER("Player"),
        MISC("misc"),
        WORLD("world");

        public String name;
        public int moduleIndex;
        public int posX, posY;
        public boolean expanded;

        Category(String name) {
            this.name = name;
            posX = 150 + (categoryCount * 95);
            posY = 85; //Should be 85.5
            expanded = true;
            categoryCount++;
        }
    }
	
	protected void notify(NotificationType type, String title, String text, int length) {
	    NotificationManager.show(type, title, text, length);
	  }
		
}
