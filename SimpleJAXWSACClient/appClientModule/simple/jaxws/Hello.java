
package simple.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Hello", targetNamespace = "http://jaxws.simple/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "say", targetNamespace = "http://jaxws.simple/", className = "simple.jaxws.Say")
    @ResponseWrapper(localName = "sayResponse", targetNamespace = "http://jaxws.simple/", className = "simple.jaxws.SayResponse")
    @Action(input = "http://jaxws.simple/Hello/sayRequest", output = "http://jaxws.simple/Hello/sayResponse")
    public String say(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
