package simple.ssl.client.auth;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

public class MyMain {

	public static void main(String[] args) {
		try {
			HttpClient httpclient = new DefaultHttpClient();

			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream tsf = new FileInputStream("/tmp/key.p12");
			ks.load(tsf, "WebAS".toCharArray());
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ks);

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, "WebAS".toCharArray());

			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			Scheme sch = new Scheme("https", 9443, socketFactory);
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);
			
			HttpResponse httpResponse = httpclient.execute(new HttpGet("https://reunited1.fyre.ibm.com/SimpleSSLClientAuthWeb/SimpleServlet"));
			System.out.println(httpResponse.getStatusLine().getStatusCode());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}



/** 
# openssl genrsa -out CA.key 4096  
# openssl req -new -key CA.key -out CA.csr -subj "/C=US/CN=ROOT.com"
# openssl x509 -req -in CA.csr -out CA.crt -signkey CA.key -sha256 -days 3650
# openssl genrsa -out my.key 4096 
# openssl req -new -key my.key -out my.csr -subj "/C=US/CN=reunited1.fyre.ibm.com"
# cat ext.txt 
extendedKeyUsage = clientAuth
# openssl x509 -req -in my.csr -out my.crt -CA CA.crt -CAkey CA.key -CAcreateserial -extfile ext.txt -days 3650 -sha256
# openssl x509 -in my.crt -text -noout
# openssl pkcs12 -export -in my.crt -inkey my.key -out key.p12
 

keytool -importcert -keystore key.p12 -storepass WebAS -alias IHS -file  /home/ibmwasl2/Downloads/reunited1-fyre-ibm-com.pem

/opt/IBM/HTTPServer90/bin/gskcapicmd -cert -add -db /opt/IBM/HTTPServer90/conf/ihsserverkey.kdb -pw WebAS -label CA -file CA.crt 
 
https://www.ibm.com/support/pages/potential-websphere-application-server-problems-when-deployed-behind-websphere-aware-proxy-server


LoadModule ibm_ssl_module modules/mod_ibm_ssl.so
Listen 443
SSLCheckCertificateExpiration 30
<VirtualHost *:443>
 SSLEnable
 SSLClientAuth 1
 Header always set Strict-Transport-Security "max-age=31536000; includeSubDomains; preload"
</VirtualHost>
KeyFile /opt/IBM/HTTPServer90/conf/ihsserverkey.kdb
SSLDisable


cd /home/ibmwasl2/eclipse-workspace/SimpleSSLClientAuthWeb/WebContent/WEB-INF/classes
/opt/IBM/WebSphere/AppServer90ND/java/8.0/jre/bin/java -cp /home/ibmwasl2/Downloads/httpclient-4.5.14.jar:/home/ibmwasl2/Downloads/httpcore-4.4.16.jar:/home/ibmwasl2/Downloads/commons-logging-1.2.jar:. -Djavax.net.debug=true simple.ssl.client.auth.MyMain
*/
