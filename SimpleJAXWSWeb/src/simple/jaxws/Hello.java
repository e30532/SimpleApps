package simple.jaxws;

import javax.jws.WebService;

@WebService
public class Hello {
	public String say(String name) {
		System.out.println(name);
		return "Hello " + name;
	}
}
