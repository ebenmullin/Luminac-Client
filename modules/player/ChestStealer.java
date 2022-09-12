package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;

	
	public class ChestStealer extends Module {
		
		public Timer timer = new Timer();
		public static NumberSetting delay = new NumberSetting("Delay", 20, 0, 300, 10);
		
	  public ChestStealer() {
		  super("ChestStealer", Keyboard.KEY_NONE, Category.PLAYER);
		  this.addSettings(delay);
	  
	  }
	  
	  public void onEvent(Event e) {
	    if (e instanceof EventMotion) {
	    	if(e.isPre()) {
	      if (mc.thePlayer.openContainer != null && mc.thePlayer.openContainer instanceof ContainerChest) {
	        ContainerChest chest = (ContainerChest)mc.thePlayer.openContainer;
	        int i = 0;
	        
	        for (; i < chest.getLowerChestInventory().getSizeInventory(); i++) {
	          if (chest.getLowerChestInventory().getStackInSlot(i) != null && timer.hasTimeElapsed((long) delay.getValue(), true)) {
	            mc.playerController.windowClick(chest.windowId, i, 1, 1, (EntityPlayer)mc.thePlayer);
	            timer.reset();
	          } 
	        } 
	      } 
	    } 
	  }
	}
}