package test;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import api.ClassGreeting;
import api.RecordGreeting;
import api.GreetingService;
import api.Message;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("")
public class TestResource {

	@Path("record")
	@GET
	public Response recordProxy() throws NamingException {
		final String greeting = lookupGreetingBean().greetWithRecord(new RecordGreeting(new Message("Hello record!")));

		System.out.println("Received greeting: " + greeting);

		return Response.ok().build();
	}

	@Path("class")
	@GET
	public Response classProxy() throws NamingException {
		final String greeting = lookupGreetingBean().greetWithClass(new ClassGreeting(new Message("Hello class!")));

		System.out.println("Received greeting: " + greeting);

		return Response.ok().build();
	}

	private GreetingService lookupGreetingBean() throws NamingException {
		final Hashtable<String, String> props = new Hashtable<>();
		props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new javax.naming.InitialContext(props);

		return (GreetingService) context.lookup(
				"ejb:" + "test-ear-0.0.1-SNAPSHOT" + "/" + "test-ejb-mod-0.0.1-SNAPSHOT" + "/" + "GreetingServiceImpl" + "!"
						+ api.GreetingService.class.getName());
	}
}
