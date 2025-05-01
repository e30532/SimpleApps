

<img width="889" alt="image" src="https://github.com/user-attachments/assets/82504dde-9256-4d20-8092-fc83454b8b6b" />

        <resourceProperties xmi:id="J2EEResourceProperty_1746081163918" name="mail.smtp.auth" type="java.lang.String" value="true" required="false"/>
        <resourceProperties xmi:id="J2EEResourceProperty_1746081190199" name="mail.smtp.starttls.enable" type="java.lang.String" value="true" required="false"/>
        <resourceProperties xmi:id="J2EEResourceProperty_1746083011713" name="mail.smtp.port" type="java.lang.String" value="587" required="false"/>
	
<img width="838" alt="image" src="https://github.com/user-attachments/assets/dd257ad6-f65d-4303-a3e6-167f8cbfb027" />
<img width="484" alt="image" src="https://github.com/user-attachments/assets/361bf459-bd5a-4e1f-8d5b-f714cc5b1366" />

```
package f;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Resource(name = "mail/CNMailSession")
    private Session mailSession;
    
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
		try {
            MimeMessage message = new MimeMessage(mailSession);
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("e30532@jp.ibm.com"));
            message.setSubject("Test");
            message.setText("Chihiro");

            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
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
```



[root@c89289v1 bin]# echo "" |openssl s_client -connect smtp.gmail.com:465 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p'  > /tmp/tmp.crt
depth=2 C = US, O = Google Trust Services LLC, CN = GTS Root R1
verify return:1
depth=1 C = US, O = Google Trust Services, CN = WR2
verify return:1
depth=0 CN = smtp.gmail.com
verify return:1
DONE
[root@c89289v1 bin]# /opt/IBM/HTTPServer90/bin/gskcapicmd -cert -add -db /opt/IBM/wlp24.0.0.5/wlp/usr/servers/defaultServer/resources/security/key.p12 -pw PassW0rd -type pkcs12 -label gmail -file /tmp/tmp.crt

```
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>localConnector-1.0</feature>
    	<feature>javaee-7.0</feature>
	</featureManager>
    
   
    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>

    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

    <applicationMonitor updateTrigger="mbean"/>
    <logging maxFileSize="100" maxFiles="10" traceFileName="trace.log" traceFormat="BASIC" traceSpecification="*=info:GenericBNF=all:HTTPChannel=all:HTTPDispatcher=all:SSLChannel=all:TCPChannel=all:com.ibm.websphere.ssl=all:com.ibm.ws.ssl.*=all:com.ibm.ws.webcontainer*=all:com.ibm.wsspi.ssl.*=all:com.ibm.wsspi.webcontainer*=all"/>
    <keyStore password="PassW0rd"/>

    <basicRegistry>
    	<user password="admin" name="admin"></user>
    </basicRegistry>
    <application location="/root/SimpleApps/F.ear"/>
    
    <mailSession user="ymdyskl2@gmail.com" jndiName="mail/CNMailSession"
    	from="ymdyskl2@gmail.com" description="CNMailSession"
    	password="**********" host="smtp.gmail.com"
    	mailSessionID="CNMailSession">
    	<property name="mail.smtp.auth" value="true"></property>
    	<property name="mail.smtp.starttls.enable" value="true"></property>
    	<property name="mail.smtp.port" value="587"></property>
    </mailSession>
</server>
```

