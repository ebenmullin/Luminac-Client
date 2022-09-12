package luminac.modules.render;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import net.minecraft.block.Block;

public class Xray extends Module {
	
	public static ArrayList<Block> xrayBlocks = new ArrayList();
		
	public Xray() {
		super("Xray", Keyboard.KEY_LBRACKET, Category.RENDER);
	}
	
	public void onEnable() {
		mc.renderGlobal.loadRenderers();	
	}
	
	public void onDisable() {
		mc.renderGlobal.loadRenderers();	
	}
}
