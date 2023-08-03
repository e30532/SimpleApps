package simple.jaxrs20.client;

import java.io.IOException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class SimpleJAXRSServiceClientFilter implements ClientRequestFilter, ClientResponseFilter {
	
	public SimpleJAXRSServiceClientFilter() {
	}
	
	@Override
	public void filter(ClientRequestContext request) throws IOException {
		System.out.println("Method: " + request.getMethod());
	}

	@Override
	public void filter(ClientRequestContext request, ClientResponseContext response) throws IOException {
		System.out.println("StatusCode: " + response.getStatus());
	}
}