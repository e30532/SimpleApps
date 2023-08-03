package simple.jaxrs20;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/example20")
public class SimpleJAXRSService {

        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public Response sayHello(){
        	System.out.println("simple.jaxrs20.SimpleJAXRSService#sayHello > ");
        	return Response.ok("Hello from JAXRS").build();
        }
        
        @POST
        @Consumes(MediaType.TEXT_PLAIN)
        @Produces(MediaType.TEXT_PLAIN)
        public Response sayHellowithName(String name){
        	System.out.println("simple.jaxrs20.SimpleJAXRSService#sayHellowithName > ");
            return Response.ok("Hello "+ name +" from JAXRS").build();
        }
}