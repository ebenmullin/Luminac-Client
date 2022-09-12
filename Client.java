package luminac;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import luminac.util.font.*;
import luminac.util.render.XrayUtils;
import luminac.command.CommandManager;
import luminac.events.Event;
import luminac.events.listeners.EventChat;
import luminac.events.listeners.EventKey;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.modules.combat.*;
import luminac.modules.misc.*;
import luminac.modules.movement.*;
import luminac.modules.player.*;
import luminac.modules.render.*;
import luminac.modules.world.*;
import luminac.settings.SettingsManager;
import luminac.ui.HUD;
import luminac.ui.clickgui.ClickGUI;
import luminac.ui.notifications.NotificationManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Client {
	
	public static String clientName = "Luminac";
	public static String clientVersion = "1.9";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	public static ClickGUI clickgui;
	public static CommandManager commandManager = new CommandManager();
	public static NotificationManager notificationManager;
	public static SettingsManager settingsManager;
	public static FontManager fontManager;
	//public static ConfigManager configManager = new ConfigManager();
	
	
	public static void startup() {
		boolean isusingclient = true;
		System.out.println("Starting " + clientName + " v" + clientVersion);
		Display.setTitle(clientName + " v" + clientVersion);
		settingsManager = new SettingsManager();
		clickgui = new ClickGUI();
//		notificationManager = new NotificationManager();
		fontManager = new FontManager();
		fontManager.setup();
		XrayUtils.initXRayBlocks();

		/**
		 * COMBAT
		 */
		modules.add(new AimAssist());
		modules.add(new AntiKB());
		modules.add(new AutoClicker());
		modules.add(new AutoPot());
		modules.add(new AutoDisconnect());
		modules.add(new Criticals());
		modules.add(new KillAura());
		modules.add(new TpAura());

		/**
		 * MOVEMENT
		 */
		modules.add(new AutoWalk());
		modules.add(new Bhop());
		//modules.add(new ClickTP());
		//modules.add(new EntitySpeed());
		modules.add(new Fly());
		modules.add(new Glide());
		modules.add(new Jesus());
		modules.add(new LongJump());
		//modules.add(new Parkour());
		modules.add(new Phase());
		modules.add(new Sprint());
		modules.add(new Step());

		/**
		 * PLAYER
		 */
		modules.add(new AirJump());
		modules.add(new Blink());
		modules.add(new ChestStealer());
		modules.add(new Inventory());
		//modules.add(new NoClip());
		modules.add(new NoFall());
		//modules.add(new Perspective());
		modules.add(new SafeWalk());
		modules.add(new Scaffold());
		modules.add(new Scaffold2());

		/**
		 * RENDER
		 */
		//modules.add(new Armor());
		modules.add(new Chams());
		modules.add(new ClickGui());
		modules.add(new ESP());
		modules.add(new FreeCam());
		modules.add(new FullBright());
		modules.add(new KeyStrokes());
		modules.add(new NameTags());
		//modules.add(new Notifications());
		//modules.add(new Projectiles());
		//modules.add(new Search());
		modules.add(new StorageESP());
		modules.add(new TabGui());
		modules.add(new Tracers());
		modules.add(new Xray());

		/**
		 * MISC
		 */
		modules.add(new AutoCorrect());
		modules.add(new AutoCorrect());
		//modules.add(new Color());
		modules.add(new AutoEat());
		//modules.add(new AutoTool());
		modules.add(new BetterFPS());
		//modules.add(new Capes());
		modules.add(new GuiSettings());
		modules.add(new Hotbar());
		modules.add(new Zoom());

		/**
		 * WORLD
		 */
		modules.add(new FastPlace());
		modules.add(new Nuker());
		modules.add(new Timer());
		modules.add(new WorldTime());
	}

	public static void onEvent(Event e) {
		if(e instanceof EventChat) {
			commandManager.handleChat((EventChat) e);
		}
		
		for(Module m : modules) {
			if(!m.toggled)
				continue;
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key) {
		Client.onEvent(new EventKey(key));
		
		for(Module m : modules) {
			if(m.getKey() == key) {
				m.toggled();
			}
		}
		
	}
	
	public static List<Module> getModulesByCategory(Category c) {
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Client.modules) {
			if(m.category == c)
				modules.add(m);
		}
		
		return modules;
	}
	
	public static Module getModule(String name) {
        for(Module module : modules) {
            if(module.name.contentEquals(name)) {
                return module;
            }
        }
        return null;
    }
	
	public static void addChatMessage(String message) {
		message = "\2479" + clientName + "\2477: " + message;
		
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
}
