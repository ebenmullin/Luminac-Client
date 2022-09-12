package luminac.command.implement;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;
import net.minecraft.util.ChatComponentText;

public class Help extends Command {

	public Help() {
		super("Help", "Shows all available commands", "help", "h");
	}

	@Override
	public void onCommand(String[] args, String command) {
		mc.thePlayer.addChatMessage(new ChatComponentText("\2477\2479Luminac Index \2477page(1/1)"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2477Showing all avalible commands"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2477use the . prefix before typing a command"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2479    " + "Help: \2477Shows all available commands"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2479    " + "Info: \2477Displays client information"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2479    " + "Toggle: \2477Enables or disables a specified module: usage"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2479    " + "Bind: \2477Binds a module to a specified keybind: "));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2479    " + "Speak: Lets you type commands in chat without enabling them"));
		mc.thePlayer.addChatMessage(new ChatComponentText("\2479    " + "Vclip: Teleports you up or down a specified amount"));
	}
}
