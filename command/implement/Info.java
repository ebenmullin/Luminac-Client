package luminac.command.implement;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;

public class Info extends Command {

	public Info() {
		super("Info", "Displays client information", "info", "i");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Client.addChatMessage("Name: " + Client.clientName + " Version: " + Client.clientVersion);
		Client.addChatMessage("Join my discord for moer info: https://discord.com/invite/WgtBwFV");
	}
}