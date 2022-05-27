package simple.jaxws;

import javax.jws.WebService;

@WebService
public class Hello {
	public String say(String name) {
		return "Hello " + name;
	}
}
