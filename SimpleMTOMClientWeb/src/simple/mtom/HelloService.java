
package simple.mtom;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "HelloService", targetNamespace = "http://mtom.simple/", wsdlLocation = "WEB-INF/wsdl/HelloService.wsdl")
public class HelloService
    extends Service
{

    private final static URL HELLOSERVICE_WSDL_LOCATION;
    private final static WebServiceException HELLOSERVICE_EXCEPTION;
    private final static QName HELLOSERVICE_QNAME = new QName("http://mtom.simple/", "HelloService");

    static {
            HELLOSERVICE_WSDL_LOCATION = simple.mtom.HelloService.class.getResource("/WEB-INF/wsdl/HelloService.wsdl");
        WebServiceException e = null;
        if (HELLOSERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find 'WEB-INF/wsdl/HelloService.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        HELLOSERVICE_EXCEPTION = e;
    }

    public HelloService() {
        super(__getWsdlLocation(), HELLOSERVICE_QNAME);
    }

    public HelloService(WebServiceFeature... features) {
        super(__getWsdlLocation(), HELLOSERVICE_QNAME, features);
    }

    public HelloService(URL wsdlLocation) {
        super(wsdlLocation, HELLOSERVICE_QNAME);
    }

    public HelloService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HELLOSERVICE_QNAME, features);
    }

    public HelloService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HelloService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Hello
     */
    @WebEndpoint(name = "HelloPort")
    public Hello getHelloPort() {
        return super.getPort(new QName("http://mtom.simple/", "HelloPort"), Hello.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Hello
     */
    @WebEndpoint(name = "HelloPort")
    public Hello getHelloPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://mtom.simple/", "HelloPort"), Hello.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HELLOSERVICE_EXCEPTION!= null) {
            throw HELLOSERVICE_EXCEPTION;
        }
        return HELLOSERVICE_WSDL_LOCATION;
    }

}
