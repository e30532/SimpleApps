
package simple.wsat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Hello", targetNamespace = "http://wsat.simple/")
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
    @RequestWrapper(localName = "say", targetNamespace = "http://wsat.simple/", className = "simple.wsat.Say")
    @ResponseWrapper(localName = "sayResponse", targetNamespace = "http://wsat.simple/", className = "simple.wsat.SayResponse")
    @Action(input = "http://wsat.simple/Hello/sayRequest", output = "http://wsat.simple/Hello/sayResponse")
    public String say(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
