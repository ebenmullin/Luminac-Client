package luminac.modules.movement;

import luminac.events.Event;
import luminac.events.listeners.EventRender3d;
import luminac.events.listeners.EventUpdate;
import luminac.modules.Module;
import luminac.modules.Module.Category;
import luminac.util.Timer;
import luminac.util.render.RenderUtils;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C0CPacketInput;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
public class ClickTP extends Module {

	public Timer timer = new Timer();
	
	public ClickTP() {
		super("ClickTP", Keyboard.KEY_NONE, Category.MOVEMENT);
	}
  
	public void onEnable() {
   
	}
  
	public void onEvent(Event e) {
		if(e instanceof EventRender3d) {
			int posX = this.mc.objectMouseOver.getBlockPos().getX();
		    int posY = this.mc.objectMouseOver.getBlockPos().getY();
		    int posZ = this.mc.objectMouseOver.getBlockPos().getZ();
		    
			RenderUtils.drawFilledBox(true, posX, posY, posZ, posX + 1, posY + 1, posZ + 1, 1, 1, 1, 1, 1, 1, 1, 1);
		}
		/**
		int x = this.mc.objectMouseOver.func_178782_a().getX();
	    int y = this.mc.objectMouseOver.func_178782_a().getY();
	    int z = this.mc.objectMouseOver.func_178782_a().getZ();
	    switch (this.index) {
	    	case 0:
	    		if (Mouse.isButtonDown(1) && !this.mc.thePlayer.isSneaking() && this.mc.inGameHasFocus) {
	    			timer.reset();
			        Vec3 target = new Vec3(x, y, z);
			        killPlayer();
			        //this.stage = " ".length();
	        } 
	        break;
	      case 1:
	        if (timer.hasTimeElapsed(6000L, true)) {
	          this.mc.getNetHandler().addToSendQueue((Packet)new C0CPacketInput(0.0F, 0.0F, true, true));
	          //this.mc.thePlayer.setPosition(this.target.xCoord, this.target.yCoord, this.target.zCoord);
	          //this.stage = "".length();
	        } 
	        break;
	    } 
	  }
	  
	private void killPlayer() {
		NetHandlerPlayClient netHandler = this.mc.getNetHandler();
	    	netHandler.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 0.060100000351667404D, this.mc.thePlayer.posZ, true));
	        netHandler.addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(this.mc.thePlayer.posX, this.mc.thePlayer.posY + 5.000000237487257E-4D, this.mc.thePlayer.posZ, true));
		netHandler.addToSendQueue((Packet)new C03PacketPlayer());
	}
		 */
	}
}