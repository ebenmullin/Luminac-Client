package luminac.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import luminac.Client;
import luminac.command.implement.*;
import luminac.events.listeners.EventChat;

public class CommandManager {

	public List<Command> commands = new ArrayList<Command>();
	public String prefix = ".";
	
	public CommandManager() {
		setup();
	}
	
	public void setup() {
		commands.add(new Bind());
		commands.add(new Hclip());
		commands.add(new Help());
		commands.add(new SearchAdd());
		commands.add(new Speak());
		commands.add(new Time());
		commands.add(new Toggle());
		commands.add(new Vclip());
		commands.add(new Info());
	}
	
	public void handleChat(EventChat event) {
		String message = event.getMessage();
		
		if(!message.startsWith(prefix))
			return;
		
		event.setCancelled(true);
		
		message = message.substring(prefix.length());
		
		boolean foundCommand = false;
		
		if(message.split(" ").length > 0) {
			String commandName = message.split(" ")[0];	
			
			for(Command c : commands) {
				if(c.aliases.contains(commandName) || c.name.equalsIgnoreCase(commandName)) {
					c.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length), message);
					foundCommand = true;
					break;
				}
			}
		}
		
		if(!foundCommand) {
			Client.addChatMessage("That command does not exist, use .help for list of commands");
		}
	}
}
