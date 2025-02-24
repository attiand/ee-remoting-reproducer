package api;

import jakarta.ejb.Remote;

@Remote
public interface GreetingService {

	String greetWithRecord(RecordGreeting greeting);

	RecordGreeting fetchWithRecord();

	String greetWithClass(ClassGreeting greeting);

	ClassGreeting fetchWithClass();
}
