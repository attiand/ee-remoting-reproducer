package api;

import java.io.Serializable;

public class ClassGreeting implements Serializable {

	private final Message message;

	public ClassGreeting(Message message) {
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}
}
