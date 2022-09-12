package luminac.modules.combat;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.concurrent.ThreadLocalRandom;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.modules.Module;
import luminac.settings.BooleanSetting;
import luminac.settings.NumberSetting;
import luminac.util.Timer;

public class AutoClicker extends Module {
    public Timer timer = new Timer();
    private Robot robot;

    NumberSetting minCps = new NumberSetting("Min CPS", 16, 1, 20, 1);
    NumberSetting maxCps = new NumberSetting("Max CPS", 20, 1, 20, 1);

    BooleanSetting breakBlocks = new BooleanSetting("Break Blocks", true);
    BooleanSetting swordOnly = new BooleanSetting("Sword Only", false);

    public AutoClicker() {
        super("AutoClicker", Keyboard.KEY_NONE, Category.COMBAT);
        addSettings(minCps, maxCps, breakBlocks, swordOnly);
    }
    
    public void onEnable() {
    	Client.addChatMessage("1st");
    	for(int i = 0; i < 1; i++) {
    		Client.addChatMessage("2nd");
    		if(timer.hasTimeElapsed((1000 / getRandomCPS((long) minCps.getValue(), (long) maxCps.getValue())), true)) {
    			Client.addChatMessage("3rd");
    			//robot.mousePress(InputEvent.BUTTON1_MASK);
    			//robot.mouseRelease(InputEvent.BUTTON1_MASK);
    			
    		}
    	}
    }

    /**public void onEvent(Event e) {
        if (e instanceof EventMotion && e.isPre()) {
            if (mc.gameSettings.keyBindAttack.isKeyDown() && timer.hasTimeElapsed((1000 / getRandomCPS((long) minCps.getValue(), (long) maxCps.getValue())), true)) {
                if (swordOnly.isEnabled() && mc.thePlayer.getCurrentEquippedItem() == null || mc.thePlayer.getCurrentEquippedItem() != null && !(mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword))
                    return;

                mc.leftClickCounter = 0;
                mc.clickMouse();
            }
        }
    }
    */
    
    public long getRandomCPS(long minimum, long maximum) {
        if(maximum > minimum) {
            maximum -= minimum;
            return minimum + ThreadLocalRandom.current().nextLong(0, maximum);
        }else if (minimum > maximum) {
            minimum -= maximum;
            return maximum + ThreadLocalRandom.current().nextLong(0, minimum);
        }
        return minimum;
    }
}