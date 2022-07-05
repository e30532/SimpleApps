package simple.jaxrpc;

public class HelloProxy implements simple.jaxrpc.Hello {
  private boolean _useJNDI = true;
  private boolean _useJNDIOnly = false;
  private String _endpoint = null;
  private simple.jaxrpc.Hello __hello = null;
  
  public HelloProxy() {
    _initHelloProxy();
  }
  
  private void _initHelloProxy() {
  
    if (_useJNDI || _useJNDIOnly) {
      try {
        javax.naming.InitialContext ctx = new javax.naming.InitialContext();
        __hello = ((simple.jaxrpc.HelloService)ctx.lookup("java:comp/env/service/HelloService")).getHello();
      }
      catch (javax.naming.NamingException namingException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("JNDI lookup failure: javax.naming.NamingException: " + namingException.getMessage());
          namingException.printStackTrace(System.out);
        }
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__hello == null && !_useJNDIOnly) {
      try {
        __hello = (new simple.jaxrpc.HelloServiceLocator()).getHello();
        
      }
      catch (javax.xml.rpc.ServiceException serviceException) {
        if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
          System.out.println("Unable to obtain port: javax.xml.rpc.ServiceException: " + serviceException.getMessage());
          serviceException.printStackTrace(System.out);
        }
      }
    }
    if (__hello != null) {
      if (_endpoint != null)
        ((javax.xml.rpc.Stub)__hello)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
      else
        _endpoint = (String)((javax.xml.rpc.Stub)__hello)._getProperty("javax.xml.rpc.service.endpoint.address");
    }
    
  }
  
  
  public void useJNDI(boolean useJNDI) {
    _useJNDI = useJNDI;
    __hello = null;
    
  }
  
  public void useJNDIOnly(boolean useJNDIOnly) {
    _useJNDIOnly = useJNDIOnly;
    __hello = null;
    
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (__hello == null)
      _initHelloProxy();
    if (__hello != null)
      ((javax.xml.rpc.Stub)__hello)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public java.lang.String say(java.lang.String name) throws java.rmi.RemoteException{
    if (__hello == null)
      _initHelloProxy();
    return __hello.say(name);
  }
  
  
  public simple.jaxrpc.Hello getHello() {
    if (__hello == null)
      _initHelloProxy();
    return __hello;
  }
  
}