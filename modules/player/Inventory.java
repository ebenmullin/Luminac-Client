package luminac.modules.player;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.ui.clickgui.ClickGUI;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public class Inventory extends Module {

	public BooleanSetting cleaner = new BooleanSetting("InvCleaner", true);
	public BooleanSetting armor = new BooleanSetting("AutoArmor", true);
	public BooleanSetting move = new BooleanSetting("InvMove", true);
	public BooleanSetting preferSwords = new BooleanSetting("PreferSwords", true);

	public int[] bestArmorDamageReducement;
	public int[] bestArmorSlots;
	public float bestSwordDamage;
	public int bestSwordSlot;

	public Inventory() {
		super("Inventory", Keyboard.KEY_NONE, Category.PLAYER);
		this.addSettings(cleaner, move, armor, preferSwords);
	}

    public void onDisable() {
    	if(move.isEnabled()) {
		    if(!GameSettings.isKeyDown(mc.gameSettings.keyBindForward) || mc.currentScreen != null)
		        mc.gameSettings.keyBindForward.pressed = false;
		    if(!GameSettings.isKeyDown(mc.gameSettings.keyBindBack) || mc.currentScreen != null)
		        mc.gameSettings.keyBindBack.pressed = false;
		    if(!GameSettings.isKeyDown(mc.gameSettings.keyBindRight) || mc.currentScreen != null)
		        mc.gameSettings.keyBindRight.pressed = false;
		    if(!GameSettings.isKeyDown(mc.gameSettings.keyBindLeft) || mc.currentScreen != null)
		        mc.gameSettings.keyBindLeft.pressed = false;
		    if(!GameSettings.isKeyDown(mc.gameSettings.keyBindJump) || mc.currentScreen != null)
		        mc.gameSettings.keyBindJump.pressed = false;
		    if(!GameSettings.isKeyDown(mc.gameSettings.keyBindSprint) || mc.currentScreen != null)
		        mc.gameSettings.keyBindSprint.pressed = false;
    	}
    }

    public void onEvent(Event e) {
    	if(e instanceof EventMotion) {
	    	if(move.isEnabled()) {
		        if((mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof ClickGUI)) {
		            mc.gameSettings.keyBindForward.pressed = GameSettings.isKeyDown(mc.gameSettings.keyBindForward);
		            mc.gameSettings.keyBindBack.pressed = GameSettings.isKeyDown(mc.gameSettings.keyBindBack);
		            mc.gameSettings.keyBindRight.pressed = GameSettings.isKeyDown(mc.gameSettings.keyBindRight);
		            mc.gameSettings.keyBindLeft.pressed = GameSettings.isKeyDown(mc.gameSettings.keyBindLeft);
		            mc.gameSettings.keyBindJump.pressed = GameSettings.isKeyDown(mc.gameSettings.keyBindJump);
		            mc.gameSettings.keyBindSprint.pressed = GameSettings.isKeyDown(mc.gameSettings.keyBindSprint);
		        }
	        }

	        if(cleaner.isEnabled()) {
	    		//code
	    	}

	    	if(armor.isEnabled() ) {
	    		searchForItems();

	    		for(int i = 0; i < 4; i++) {
	    			if(bestArmorSlots[i] != -1) {
	    				int bestSlot = bestArmorSlots[i];

	    				ItemStack oldArmor = mc.thePlayer.inventory.armorItemInSlot(i);

	    				if(oldArmor != null && oldArmor.getItem() != null) {
	    					mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, 8 - i, 0, 1, mc.thePlayer);
	    				}

	    				mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, bestSlot, 0, 1, mc.thePlayer);
	    			}
	    		}

	    		if(bestSwordSlot != -1 && bestSwordDamage != -1) {
	    			mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, bestSwordSlot < 9 ? bestSwordSlot + 36 : bestSwordSlot, 0, 2, mc.thePlayer);
	    		}
	    	}
	    }
    }

    public void searchForItems() {
    	bestArmorDamageReducement = new int[4];
    	bestArmorSlots = new int[4];
    	bestSwordDamage = -1;
    	bestSwordSlot = -1;

    	for(int i = 0; i < bestArmorSlots.length; i++) {
    		ItemStack itemStack = mc.thePlayer.inventory.armorItemInSlot(i);

    		if(itemStack != null && itemStack.getItem() != null) {
    			if(itemStack.getItem() instanceof ItemArmor) {
    				ItemArmor armor = (ItemArmor) itemStack.getItem();

    				bestArmorDamageReducement[1] = armor.damageReduceAmount;

    			}

    		}
    	}

    	for(int i = 0; i < 9 * 4; i++) {
    		ItemStack itemStack = mc.thePlayer.inventory.getStackInSlot(i);

    		if(itemStack == null || itemStack.getItem() == null) continue;

    		if(itemStack.getItem() instanceof ItemArmor) {
    			ItemArmor armor = (ItemArmor) itemStack.getItem();

    			int armorType = 3 - armor.armorType;

    			if(bestArmorDamageReducement[armorType] < armor.damageReduceAmount) {
    				bestArmorDamageReducement[armorType] = armor.damageReduceAmount;
    				bestArmorSlots[armorType] = i;

    			}
    		}

    		if(itemStack.getItem() instanceof ItemSword) {
    			ItemSword sword = (ItemSword) itemStack.getItem();

    			if(bestSwordDamage < sword.getDamageVsEntity()) {
    				bestSwordDamage = sword.getDamageVsEntity();
    				bestSwordSlot = i;
    			}
    		}

    		if(itemStack.getItem() instanceof ItemTool) {
    			ItemTool tool = (ItemTool) itemStack.getItem();

    			float damage = tool.getToolMaterial().getDamageVsEntity();

    			if(preferSwords.isEnabled()) {
    				damage -= 1;
    			}

    			if(bestSwordDamage < damage) {
    				bestSwordDamage = damage;
    				bestSwordSlot = i;
    			}
    		}
    	}
    }
}