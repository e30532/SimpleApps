/**
 * HelloService.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * f5122219.01 v5922153307
 */

package simple.jaxrpc;

public interface HelloService extends javax.xml.rpc.Service {
    public simple.jaxrpc.Hello getHello() throws javax.xml.rpc.ServiceException;

    public java.lang.String getHelloAddress();

    public simple.jaxrpc.Hello getHello(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
