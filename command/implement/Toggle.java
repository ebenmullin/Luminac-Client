package luminac.command.implement;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;

public class Toggle extends Command {

	public Toggle() {
		super("Toggle", "Enables or disables a certain module", "toggle <name>", "t");
	}

	@Override
	public void onCommand(String[] args, String command) {
		if(args.length > 0) {
			String moduleName = args[0];
			
			boolean foundModule = false;
			
			for(Module module : Client.modules) {
				if(module.name.equalsIgnoreCase(moduleName)) {
					module.toggled();
					
					Client.addChatMessage((module.isEnabled() ? "Enabled" : "Disabled") + " " + module.name);
					
					foundModule = true;
					break;
				}
			}
			
			if(!foundModule) {
				Client.addChatMessage("Could not find that module");
			}
		}
	}
	
}
