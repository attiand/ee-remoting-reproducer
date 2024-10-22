package api;

import java.io.Serializable;

public class Message implements Serializable {

	private final String value;

	public Message(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
