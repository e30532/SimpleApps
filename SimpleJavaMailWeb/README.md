

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
