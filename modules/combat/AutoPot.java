package luminac.modules.combat;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.events.Event;
import luminac.events.listeners.EventMotion;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;

public class AutoPot extends Module {
	
	public NumberSetting health = new NumberSetting("Health amount", 16, 0, 19, 1);
	
	public Timer timer = new Timer();
    public boolean potting;
    private float prevPitch;
	
	public AutoPot() {
		super("AutoPot", Keyboard.KEY_NONE, Category.COMBAT);
		this.addSettings(health);
	}
	
	
	public void onEvent(Event e) {
        //this.setSuffix(String.valueOf(getPotionCount()));
        int prevSlot = mc.thePlayer.inventory.currentItem;
        if(e instanceof EventMotion && e.isPost()) {
        	if(!mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
        		if(timer.hasTimeElapsed(50, true)) {
        			if(isSpeedPotsInHotBar() && !mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
        				for(int index = 36; index < 45; index++) {
                            final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                            if(stack == null)
                                continue;
                            
                            if(isStackSplashSpeedPot(stack)) {
                                potting = true;
                                prevPitch = mc.thePlayer.rotationPitch;
                            }
                            
                        }
                        for(int index = 36; index < 45; index++) {
                            final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                            if(stack == null)
                                continue;
                            if(isStackSplashSpeedPot(stack) && potting) {
                                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, 89, mc.thePlayer.onGround));
                                mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(index - 36));
                                mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0, 0, 0));
                                mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(prevSlot));
                                mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, prevPitch, mc.thePlayer.onGround));
                                break;
                            }
                        }
                        timer.reset();
                        potting = false;
                    } else {
                        getSpeedPotsFromInventory();
                    }
                }
            }

                if(!mc.thePlayer.isPotionActive(Potion.regeneration) && mc.thePlayer.getHealth() <= health.getValue()) {
                    if(timer.hasTimeElapsed(50, true)) {
                        if(isRegenPotsInHotBar()) {
                            for(int index = 36; index < 45; index++) {
                                final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                if(stack == null)
                                    continue;
                                if(isStackSplashRegenPot(stack)) {
                                    potting = true;
                                    prevPitch = mc.thePlayer.rotationPitch;
                                }
                            }
                            for(int index = 36; index < 45; index++) {
                                final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                if(stack == null)
                                    continue;
                                if(isStackSplashRegenPot(stack)) {
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, 89, mc.thePlayer.onGround));
                                    mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(index - 36));
                                    mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0, 0, 0));
                                    mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(prevSlot));
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, prevPitch, mc.thePlayer.onGround));
                                    break;
                                }
                            }
                            timer.reset();
                            potting = false;
                        } else {
                            getRegenPotsFromInventory();
                        }
                    }
                }

                if(mc.thePlayer.getHealth() <= health.getValue()) {
                    if(timer.hasTimeElapsed(50, true)) {
                        if(isHealthPotsInHotBar()) {
                            for(int index = 36; index < 45; index++) {
                                final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                if(stack == null)
                                    continue;
                                if(isStackSplashHealthPot(stack)) {
                                    potting = true;
                                    prevPitch = mc.thePlayer.rotationPitch;
                                }
                            }
                            for(int index = 36; index < 45; index++) {
                                final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
                                if(stack == null)
                                    continue;
                                if(isStackSplashHealthPot(stack)) {
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, 89, mc.thePlayer.onGround));
                                    mc.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(index - 36));
                                    mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(-1, -1, -1), 255, mc.thePlayer.getHeldItem(), 0, 0, 0));
                                    mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(prevSlot));
                                    mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C06PacketPlayerPosLook(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, prevPitch, mc.thePlayer.onGround));
                                    break;
                                }
                            }
                            timer.reset();
                            potting = false;
                        } else {
                            getPotsFromInventory();
                        }
                    }
                }
            
    /**}else if(e instanceof EventUpdate) {
    	if(mc.thePlayer.getHealth() < health.getValue()) {
			for(int i = 0; i < 9; ++i) {
	            ItemStack item = mc.thePlayer.inventory.mainInventory[i];
	            if(item != null) {
	            	if(item.getItem() instanceof ItemPotion) {
	            		mc.thePlayer.inventory.currentItem = i;
	            		mc.thePlayer.rotationPitch = 90;
	            		mc.gameSettings.keyBindUseItem.pressed = true;
	            		//if(timer.hasTimeElapsed(100, true))
						if(mc.thePlayer.getHealth() >= health.getValue()){
							mc.gameSettings.keyBindUseItem.pressed = false;
							//mc.thePlayer.inventory.currentItem = prevSlot;
						
						}
	            	}
	            }else if(item == null) {
	            	Client.addChatMessage("No potions in hotbar");
	            }
	        }
		}
    */
    }
} 

	private boolean isSpeedPotsInHotBar() {
	    for(int index = 36; index < 45; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(stack.getDisplayName().contains("Frog")) continue;
	        if(isStackSplashSpeedPot(stack))
	            return true;
	    }
	    return false;
	}

	private boolean isHealthPotsInHotBar() {
	    for(int index = 36; index < 45; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(isStackSplashHealthPot(stack))
	            return true;
	    }
	    return false;
	}
	
	private boolean isRegenPotsInHotBar() {
	    for(int index = 36; index < 45; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(isStackSplashRegenPot(stack))
	            return true;
	    }
	    return false;
	}
	
	private void getSpeedPotsFromInventory() {
	    if(mc.currentScreen instanceof GuiChest)
	        return;
	    for(int index = 9; index < 36; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(stack.getDisplayName().contains("Frog")) continue;
	        if(isStackSplashSpeedPot(stack)) {
	            mc.playerController.windowClick(0, index, 6, 2, mc.thePlayer);
	            break;
	        }
	    }
	}
	
	private int getPotionCount() {
	    int count = 0;
	    for(int index = 0; index < 45; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(isStackSplashHealthPot(stack) || isStackSplashHealthPot(stack) || isStackSplashRegenPot(stack))
	            count++;
	    }
	    return count;
	}
	
	private void getPotsFromInventory() {
	    if(mc.currentScreen instanceof GuiChest)
	        return;
	    for(int index = 9; index < 36; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(isStackSplashHealthPot(stack)) {
	            mc.playerController.windowClick(0, index, 6, 2, mc.thePlayer);
	            break;
	        }
	    }
	}
	
	private boolean isStackSplashSpeedPot(ItemStack stack) {
	    if(stack == null) {
	        return false;
	    }
	    if(stack.getItem() instanceof ItemPotion) {
	        final ItemPotion potion = (ItemPotion) stack.getItem();
	        if(ItemPotion.isSplash(stack.getItemDamage())) {
	            for(final Object o : potion.getEffects(stack)) {
	                final PotionEffect effect = (PotionEffect) o;
	                if(stack.getDisplayName().contains("Frog")) return false;
	                if(effect.getPotionID() == Potion.moveSpeed.id && effect.getPotionID() != Potion.jump.id) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	
	private boolean isStackSplashHealthPot(ItemStack stack) {
	    if(stack == null) {
	        return false;
	    }
	    if(stack.getItem() instanceof ItemPotion) {
	        final ItemPotion potion = (ItemPotion) stack.getItem();
	        if(ItemPotion.isSplash(stack.getItemDamage())) {
	            for(final Object o : potion.getEffects(stack)) {
	                final PotionEffect effect = (PotionEffect) o;
	                if(effect.getPotionID() == Potion.heal.id) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
	
	private void getRegenPotsFromInventory() {
	    if(mc.currentScreen instanceof GuiChest)
	        return;
	    for(int index = 9; index < 36; index++) {
	        final ItemStack stack = mc.thePlayer.inventoryContainer.getSlot(index).getStack();
	        if(stack == null)
	            continue;
	        if(isStackSplashRegenPot(stack)) {
	            mc.playerController.windowClick(0, index, 6, 2, mc.thePlayer);
	            break;
	        }
	    }
	}
	
	private boolean isStackSplashRegenPot(ItemStack stack) {
	    if(stack == null) {
	        return false;
	    }
	    if(stack.getItem() instanceof ItemPotion) {
	        final ItemPotion potion = (ItemPotion) stack.getItem();
	        if(ItemPotion.isSplash(stack.getItemDamage())) {
	            for(final Object o : potion.getEffects(stack)) {
	                final PotionEffect effect = (PotionEffect) o;
	                if(effect.getPotionID() == Potion.regeneration.id) {
	                    return true;
	                }
	            }
	        }
	    }
	    return false;   
		}
	}
	
