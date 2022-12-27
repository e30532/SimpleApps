package simple.mtom;

import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.activation.DataHandler;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.soap.MTOM;


@MTOM(enabled=true, threshold=10)
@WebService
@HandlerChain(file="handler-chain.xml") 
public class Hello {	
	public void fileUpload(String filename, DataHandler dataHandler) {
		InputStream in = null;
		OutputStream out = null;
		try {
			System.out.println("simple.mtom.Hello.fileUpload>");
			in = dataHandler.getInputStream();
			out = new FileOutputStream(new File("/tmp3" + File.separatorChar + filename));
        
			byte[] buf = new byte[1024*1024];
			int read;
			while( (read=in.read(buf))!=-1 ) {
				out.write(buf,0,read);
				out.flush();
			}
			in.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(in!=null)in.close();
			if(out!=null)out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}