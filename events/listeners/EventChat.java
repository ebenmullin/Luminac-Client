package luminac.events.listeners;

import luminac.events.Event;

public class EventChat extends Event<EventChat>{

	public String message;

	public EventChat(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
