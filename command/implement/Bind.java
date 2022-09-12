package luminac.command.implement;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;

public class Bind extends Command {

	public Bind() {
		super("Bind", "Binds a module to a specific keybind", "bind <name> <key> | clear", "b");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length == 2) {
			String moduleName = args[0];
			String keyName = args[1];
				
			boolean foundModule = false;
			
			for(Module module : Client.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.keyPressed.setKeyCode(Keyboard.getKeyIndex(keyName.toUpperCase()));
					
					Client.addChatMessage(String.format("Bound %s to %s", module.name, Keyboard.getKeyName(module.getKey())));
					foundModule = true;
					break;
				}
			}
			
			if(!foundModule) {
				Client.addChatMessage("Could not find that module");
			}
		}
		
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("clear")) {
				for(Module module : Client.modules) {
					module.keyPressed.setKeyCode(Keyboard.KEY_NONE);
				}
			}
			
			Client.addChatMessage("Cleared all binds.");
		}
	}
}
