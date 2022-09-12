package luminac.events.listeners;

import luminac.events.Event;
import net.minecraft.network.Packet;

public class EventPacket extends Event<EventPacket> {
	public static Packet packet;
    public boolean cancelled;

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public EventPacket(Packet packet) {
        EventPacket.packet = packet;
    }

    public static Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        EventPacket.packet = packet;
    }
}
