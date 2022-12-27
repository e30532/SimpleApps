package simple.mtom;

import java.util.Set;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPHeader;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.headers.Header;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

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
		if(!outboundProperty) {
			try {
/**
				SOAPHeader header = arg0.getMessage().getSOAPHeader();
			NodeList nodes = header.getChildNodes();
	        for (int x = 0; x < nodes.getLength(); x++) {
	        	System.out.println(nodes.item(x).getTextContent());
	        }
**/				

				/**
				List<?> list = (List<?>) arg0.get(Header.HEADER_LIST);
				if (list != null && !list.isEmpty()) {
				   Iterator<?> iter = list.iterator();
				   while (iter.hasNext()) {
				       Header header = (Header) iter.next();
				       if (header.getDirection() == Header.Direction.DIRECTION_IN) {
//				    	   System.out.println(header.getObject());
				       } else if (header.getDirection() == Header.Direction.DIRECTION_OUT) {
				       }
				   }
				}		
**/			
			
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			Map<QName, List<String>> inboundHeaders=(Map<QName, List<String>>)arg0.get("jaxws.binding.soap.headers.inbound");
			List<String> elements = inboundHeaders.get(new QName("simple.mtom", "MyHeader"));
			System.out.println(elements.get(0));

//			List<SoapHeader> soapHeaders = (List)arg0.get("org.apache.cxf.headers.Header.list"); 
//			Element header = ((Element)soapHeaders.get(0).getObject());
//			String value = (String)(header.getElementsByTagName("MyHeader").item(0).getTextContent());
//			System.out.println(value);
			
		}
		
//		Object accessor = arg0.get("jaxws.message.accessor");
//		if (accessor != null) {
//			System.out.println(accessor.toString());
//		}

		return true;
	}

	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
