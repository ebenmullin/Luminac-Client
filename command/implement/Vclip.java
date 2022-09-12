package luminac.command.implement;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Vclip extends Command {

	public Vclip() {
		super("Vclip", "Teleports you up or down a specified amount", "Vclip <amount>", "v");
	}

	@Override
	public void onCommand(String[] args, String command) {
		float clipAmount = - 2;
		mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY - 3f, mc.thePlayer.posZ);
		//this.mc.getNetHandler().addToSendQueue((Packet)new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY - 10, mc.thePlayer.posZ, this.mc.thePlayer.onGround));
		//mc.thePlayer.setLocationAndAngles(mc.thePlayer.posX, clipAmount + mc.thePlayer.posY, mc.thePlayer.posZ, mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
		Client.addChatMessage("Teleported you " + (clipAmount < 0 ? "down " : "up ") + Math.abs(clipAmount) + " block" + (Math.abs(clipAmount) == 1 ? "" : "s" ));
	}
} 