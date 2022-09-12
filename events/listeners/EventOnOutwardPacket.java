package luminac.events.listeners;

import java.util.ArrayList;
import net.minecraft.network.Packet;
import luminac.events.Event;
import java.util.ArrayList;
import net.minecraft.network.Packet;

public class EventOnOutwardPacket extends Event<EventOnOutwardPacket>{
	
	private Packet packet;
	private ArrayList<Packet> packetsList = new ArrayList<Packet>();
	
	public EventOnOutwardPacket(Packet packet){
		this.packet = packet;
	}

	public Packet getPacket(){
		return packet;
	}
	
	public void addPacketToList(Packet p){
		packetsList.add(p);
	}
	
	public ArrayList<Packet> getPacketList(){
		return packetsList;
	}
	
}