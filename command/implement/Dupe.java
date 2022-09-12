package luminac.command.implement;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;

public class Dupe extends Command {

	public Dupe() {
		super("Dupe", "hold a bookand quil then do .d or .dupe will then fill the book with random letters and symmbols which the server will not allow so will kick u out the server", "dupe", "d");
	}

	@Override
	public void onCommand(String[] args, String command) {
		
	}
}
