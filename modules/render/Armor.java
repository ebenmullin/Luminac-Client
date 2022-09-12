package luminac.modules.render;

import javax.swing.text.Position;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import luminac.events.Event;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Armor extends Module {
	
	public Armor() {
		super("Armor", Keyboard.KEY_NONE, Category.RENDER);
	}
	
	public void onEnable() {
	
	}
	public void onDisable() {
		
	}
	public void onEvent(Event e) {
		if(e instanceof EventUpdate) {
			if(e.isPre()) {
				
				/**
				for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
					ItemStack itemStack	= mc.thePlayer.inventory.armorInventory[i];
					renderItem(Position, i, itemStack);
				}
				mc.getRenderItem()
				renderItem(Position, 3, 3, new ItemStack(Items.diamond_chestplate));
			}
		}
	}

		if(itemStack == null) {
			return;
		}
		
		GL11.glPushMatrix();
		
		if(itemStack.getItem().isDamageable()) {
			double damage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage()) * 100;
			mc.fontRendererObj.drawString("%", 1, 50, -1);
			
			mc.getRenderItem().func_175042_a(itemStack, 100, 100);
			
			GL11.glPopMatrix();
		}*/
		
	}}}
}