package luminac.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import luminac.util.LuminacMinecraft;

public class EntityFakePlayer extends EntityOtherPlayerMP {
	public Minecraft mc = Minecraft.getMinecraft();
	
	public EntityFakePlayer() {
		super(LuminacMinecraft.getWorld(), LuminacMinecraft.getPlayer().getGameProfile());
		copyLocationAndAnglesFrom(LuminacMinecraft.getPlayer());
		
		// fix inventory
		//inventory.copyInventory(LuminacMinecraft.getPlayer().inventory);
		//getDataManager().set(EntityPlayer.PLAYER_MODEL_FLAG, LuminacMinecraft
		//	.getPlayer().getDataManager().get(EntityPlayer.PLAYER_MODEL_FLAG));
		
		// fix rotation
		rotationYawHead = LuminacMinecraft.getPlayer().rotationYawHead;
		renderYawOffset = LuminacMinecraft.getPlayer().renderYawOffset;
		
		// fix cape movement
		//chasingPosX = posX;
		//chasingPosY = posY;
		//chasingPosZ = posZ;
		
		// spawn
		LuminacMinecraft.getWorld().addEntityToWorld(getEntityId(), this);
	}
	
	public void resetPlayerPosition()
	{
		LuminacMinecraft.getPlayer().setPositionAndRotation(posX, posY, posZ,
			rotationYaw, rotationPitch);
	}
	
	public void despawn()
	{
		LuminacMinecraft.getWorld().removeEntityFromWorld(getEntityId());
	}
}