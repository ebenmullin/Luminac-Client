package luminac.events.listeners;

import luminac.events.Event;

public class EventRender3d extends Event<EventRender3d> {
    float partialTicks;
    public EventRender3d(float paritalTicks) {
    	this.partialTicks = paritalTicks;
    }
      
    public float getpartialTicks() {
    	return this.partialTicks;
    }
      
    public void setpartialTicks(float paritalTicks) {
        this.partialTicks = paritalTicks;
      }
}
