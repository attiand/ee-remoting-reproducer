package test;

import api.ClassGreeting;
import api.Message;
import api.RecordGreeting;
import api.GreetingService;
import jakarta.ejb.Stateless;

@Stateless
public class GreetingServiceImpl implements GreetingService {

	@Override
	public String greetWithRecord(RecordGreeting greeting) {
		System.out.println("Greeting with record: " + greeting.message().getValue());
		return greeting.message().getValue();
	}

	@Override
	public RecordGreeting fetchWithRecord() {
		return new RecordGreeting(new Message("Greetings from record"));
	}

	@Override
	public String greetWithClass(ClassGreeting greeting) {
		System.out.println("Greeting with class: " + greeting.getMessage().getValue());
		return greeting.getMessage().getValue();
	}

	@Override
	public ClassGreeting fetchWithClass() {
		return new ClassGreeting(new Message("Greetings from class"));
	}
}
