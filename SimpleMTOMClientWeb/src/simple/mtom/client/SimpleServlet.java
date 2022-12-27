package simple.mtom.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;

import simple.mtom.Hello;
import simple.mtom.HelloService;
import simple.mtom.MyHandler;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HelloService service = new HelloService();

	    Hello proxy = service.getHelloPort(new MTOMFeature(10));
	    BindingProvider bp = (BindingProvider)proxy;
	    bp.getRequestContext().put("javax.xml.ws.service.endpoint.address", "http://localhost:9080/SimpleMTOMWeb/HelloService");
	    SOAPBinding binding = (SOAPBinding) bp.getBinding();

	    List<Handler> handlerChain = new ArrayList<Handler>();
	    handlerChain.add(new MyHandler());
	    bp.getBinding().setHandlerChain(handlerChain);
	    
	 	binding.setMTOMEnabled(true);
	    File file = new File("/tmp2/a.txt");
	    FileInputStream fileinputstream = null;
	    try { 
	    	fileinputstream = new FileInputStream(file);
	    	int numberBytes = fileinputstream.available();
	    	byte[] bytearray = new byte[numberBytes];
	    	fileinputstream.read(bytearray);
	    	fileinputstream.close();
	    	proxy.fileUpload("a.txt", bytearray);
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {	    	
	    	if(fileinputstream!=null)
				try {
					fileinputstream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	    }	    
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
