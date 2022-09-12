package luminac.modules.misc;

import org.lwjgl.input.Keyboard;

import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.util.BlockPos;

public class AutoTool extends Module {
    public AutoTool() {
    	super("AutoTool", Keyboard.KEY_N, Category.MISC);
    }

    public void onEvent(Event e) {
    	int prevSlot = mc.thePlayer.inventory.currentItem;
		if(e instanceof EventUpdate) {
	        if (!mc.gameSettings.keyBindAttack.isKeyDown()) {
	            return;
	        }
	        if (mc.objectMouseOver == null) {
	            return;
	        }
	        BlockPos pos = mc.objectMouseOver.getBlockPos();
	        if (pos == null) {
	            return;
	        }
	        updateTool(pos);
	    }
		mc.getNetHandler().addToSendQueue(new C09PacketHeldItemChange(prevSlot));
	}

    public static void updateTool(BlockPos pos) {
    	Minecraft mc = Minecraft.getMinecraft();
        Block block = mc.theWorld.getBlockState(pos).getBlock();
        float strength = 1.0f;
        int bestItemIndex = -1;
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = mc.thePlayer.inventory.mainInventory[i];
            if (itemStack != null) {
                if (itemStack.getStrVsBlock(block) > strength) {
                    strength = itemStack.getStrVsBlock(block);
                    bestItemIndex = i;
                }
            }
        }
        if (bestItemIndex != -1) {
            mc.thePlayer.inventory.currentItem = bestItemIndex;
        }
    }
}