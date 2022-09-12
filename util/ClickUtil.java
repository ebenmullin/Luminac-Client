package luminac.util;

import net.minecraft.client.Minecraft;
import luminac.util.Timer;

public class ClickUtil {
	
	public static void attackOnce() {
		Timer timer = new Timer();
		Minecraft mc = Minecraft.getMinecraft();
		
		mc.gameSettings.keyBindAttack.pressed = true;
		timer.hasTimeElapsed(1000, true);
		mc.gameSettings.keyBindAttack.pressed = false;
	}
	
	
}
