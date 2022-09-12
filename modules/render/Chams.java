package luminac.modules.render;

import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.Client;
import luminac.events.Event;
import luminac.modules.Module;
import luminac.settings.ModeSetting;

public class Chams extends Module {

	/** Credit to TouchPotato 2020 **/
	
	public static ModeSetting color = new ModeSetting("Color", "Skin", "Skin", "Green");

	public Chams() {
		super("Chams", Keyboard.KEY_RBRACKET, Category.RENDER);
		this.addSettings(color);
	}
}
