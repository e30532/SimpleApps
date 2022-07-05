/**
 * HelloServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * f5122219.01 v5922153307
 */

package simple.jaxrpc;

public class HelloServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, simple.jaxrpc.HelloService {

    public HelloServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://jaxrpc.simple",
           "HelloService"));

        context.setLocatorName("simple.jaxrpc.HelloServiceLocator");
    }

    public HelloServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("simple.jaxrpc.HelloServiceLocator");
    }

    // Use to get a proxy class for hello
    private final java.lang.String hello_address = "http://localhost:9080/SimpleJAXRPCWeb/services/Hello";

    public java.lang.String getHelloAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return hello_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("Hello");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return hello_address;
        }
    }

    private java.lang.String helloPortName = "Hello";

    // The WSDD port name defaults to the port name.
    private java.lang.String helloWSDDPortName = "Hello";

    public java.lang.String getHelloWSDDPortName() {
        return helloWSDDPortName;
    }

    public void setHelloWSDDPortName(java.lang.String name) {
        helloWSDDPortName = name;
    }

    public simple.jaxrpc.Hello getHello() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getHelloAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getHello(endpoint);
    }

    public simple.jaxrpc.Hello getHello(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        simple.jaxrpc.Hello _stub =
            (simple.jaxrpc.Hello) getStub(
                helloPortName,
                (String) getPort2NamespaceMap().get(helloPortName),
                simple.jaxrpc.Hello.class,
                "simple.jaxrpc.HelloSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(helloWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (simple.jaxrpc.Hello.class.isAssignableFrom(serviceEndpointInterface)) {
                return getHello();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("Hello".equals(inputPortName)) {
            return getHello();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        helloWSDDPortName = prefix + "/" + helloPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://jaxrpc.simple", "HelloService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "Hello",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
        if  (portName.getLocalPart().equals("Hello")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "say", "sayRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
