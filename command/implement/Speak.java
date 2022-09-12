package luminac.command.implement;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C01PacketChatMessage;

public class Speak extends Command {

	public Speak() {
		super("Speak", "Lets you type commands in chat without enabling them", "speak", "s");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(String.join(" ", args)));
	}
}