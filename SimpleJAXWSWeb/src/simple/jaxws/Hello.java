package simple.jaxws;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService
@HandlerChain(file="handler-chain.xml")
public class Hello {
	public String say(String name) {
		System.out.println(name);
		return "Hello " + name;
	}
}
