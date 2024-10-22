package api;

import jakarta.ejb.Remote;

@Remote
public interface GreetingService {

	String greetWithRecord(RecordGreeting greeting);

	String greetWithClass(ClassGreeting greeting);
}
