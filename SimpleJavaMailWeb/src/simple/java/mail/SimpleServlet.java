package simple.java.mail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
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
		final String password = request.getParameter("password"); 
		Properties props = new Properties(); 
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.port", "587"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable","true"); 
		props.put("mail.debug", "true");
		Session session = Session.getInstance(props, new 
		javax.mail.Authenticator(){ 
		    protected PasswordAuthentication getPasswordAuthentication(){ 
		        return new PasswordAuthentication("ymdyskl2@gmail.com", password);
		    } 
		}); 
//		session.setDebug(false); 
		try{ 
		    MimeMessage msg = new MimeMessage(session); 
		    msg.setFrom(new InternetAddress("ymdyskl2@gmail.com")); 
		    InternetAddress[] address = {new InternetAddress("e30532@jp.ibm.com")}; 
		    msg.setRecipients(Message.RecipientType.TO, address); 
		    msg.setSubject("JavaMail APIs Test"); 
		    msg.setSentDate(new Date()); 
		    String body = "Hello"; 
		    msg.setText(body,"UTF-8");
		    Transport.send(msg); 
		}catch(Exception e){ 
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

//http://fyre1:9080/SimpleJavaMailWeb/SimpleServlet?password=** check 1password **
