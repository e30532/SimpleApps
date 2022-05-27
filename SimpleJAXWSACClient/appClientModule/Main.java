import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import simple.jaxws.Hello;

public class Main {
	public static void main(String[] args) {
		URL url = null;
		try {
		    url = new URL("http://localhost:9080/SimpleJAXWSWeb/HelloService?wsdl");
		    QName serviceName = new QName("http://jaxws.simple/", "HelloService");
		    QName portQname = new QName("http://jaxws.simple/", "HelloPort");
		    Service service = Service.create(url, serviceName);
		    Hello port = (Hello)service.getPort(portQname, Hello.class);
		    System.out.println(port.say("IBM"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}

}