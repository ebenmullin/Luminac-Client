package luminac.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;

public final class LuminacMinecraft
{
	public static final String VERSION = "1.10.2";
	
	private static final Minecraft mc = Minecraft.getMinecraft();
	
	public static EntityPlayerSP getPlayer()
	{
		return mc.thePlayer;
	}
	
	public static WorldClient getWorld()
	{
		return mc.theWorld;
	}
	
	public static FontRenderer getFontRenderer()
	{
		return mc.fontRendererObj;
	}
}