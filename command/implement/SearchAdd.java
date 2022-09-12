package luminac.command.implement;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import luminac.Client;
import luminac.command.Command;
import luminac.modules.Module;

public class SearchAdd extends Command {

	public SearchAdd() {
		super("Info", "Displays client information", "info", "i");
	}

	@Override
	public void onCommand(String[] args, String command) {
		
		/**
		if(!containsId(Integer.parseInt(cmd.split("add ")[1].trim()))){
			Resilience.getInstance().getValues().searchIds.add(new Float[]{Float.parseFloat(cmd.split("add ")[1]), (float)r/100,(float)g/100,(float)b/100});
			Resilience.getInstance().getValues().ticksForSearch = 71;
			Resilience.getInstance().getLogger().infoChat("Added a block with id "+cmd.split("add ")[1]+" to the search list");
		}else{
			Resilience.getInstance().getLogger().warningChat("Block already on the list!");
		}
		
		return true;
	}
	
	public boolean containsId(float id){
		for(Float[] list : Resilience.getInstance().getValues().searchIds){
			if(list[0].floatValue() == id) return true;
		}
		return false;
		 */
	}
}