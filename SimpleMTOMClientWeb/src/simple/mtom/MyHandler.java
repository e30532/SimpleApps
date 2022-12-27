package simple.mtom;

import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Element;

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
		System.out.println("MyHandler.handleMessage - client");
		
		Boolean outboundProperty = (Boolean)arg0.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(outboundProperty) {
			try {
			SOAPMessage msg = arg0.getMessage();
            SOAPPart part = msg.getSOAPPart();
            SOAPEnvelope envelope = part.getEnvelope();
            SOAPHeader header = envelope.addHeader();
            QName xmlName = new QName("simple.mtom","MyHeader");
            SOAPHeaderElement headerElement =(SOAPHeaderElement)header.addChildElement(xmlName);
            headerElement.addTextNode("MyValue");
			}catch(Exception e) {
				e.printStackTrace();
			}
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
