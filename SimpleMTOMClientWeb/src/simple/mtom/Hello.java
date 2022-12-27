
package simple.mtom;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(name = "Hello", targetNamespace = "http://mtom.simple/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Hello {


    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "fileUpload", targetNamespace = "http://mtom.simple/", className = "simple.mtom.FileUpload")
    @ResponseWrapper(localName = "fileUploadResponse", targetNamespace = "http://mtom.simple/", className = "simple.mtom.FileUploadResponse")
    @Action(input = "http://mtom.simple/Hello/fileUploadRequest", output = "http://mtom.simple/Hello/fileUploadResponse")
    public void fileUpload(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        byte[] arg1);

}
