package simple.jaxws;

import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.handler.Handler;

public class MyHandler implements SOAPHandler<SOAPMessageContext> {
//public class MyHandler implements Handler<SOAPMessageContext> {

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		System.out.println("MyHandler.handleMessage");
		
		Boolean outboundProperty = (Boolean)arg0.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(outboundProperty) {
			Map<QName, List<String>> outboundHeaders=(Map<QName, List<String>>)arg0.get("jaxws.binding.soap.headers.outbound"); 
			List<String> list1 = new ArrayList<String>();
			list1.add("<ns1:myHeader xmlns:ns1=\"http://test/\">aaa</ns1:myHeader>");
			outboundHeaders.put(new QName("http://test/", "myHeader"), list1);
			arg0.put("jaxws.binding.soap.headers.outbound", outboundHeaders);
		}
		
		Object accessor = arg0.get("jaxws.message.accessor");
		if (accessor != null) {
			System.out.println(accessor.toString());
		}

		return true;
	}

	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
