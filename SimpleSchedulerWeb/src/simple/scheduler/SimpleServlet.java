package simple.scheduler;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.websphere.scheduler.MessageTaskInfo;
import com.ibm.websphere.scheduler.Scheduler;
import com.ibm.websphere.scheduler.TaskStatus;

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
		try {
			Scheduler scheduler = (Scheduler)new InitialContext().lookup("java:comp/env/sched/MyScheduler");
			MessageTaskInfo taskInfo = (MessageTaskInfo) scheduler.createTaskInfo(MessageTaskInfo.class);
			taskInfo.setConnectionFactoryJndiName("jms/SimpleQCF");
			taskInfo.setDestinationJndiName("jms/SimpleQ");
			taskInfo.setNumberOfRepeats(10);
			taskInfo.setMessageData(Long.toString(System.currentTimeMillis()));
			taskInfo.setRepeatInterval("35seconds");
			java.util.Date startDate = new java.util.Date(System.currentTimeMillis()+30000);
			taskInfo.setStartTime(startDate);
			TaskStatus ts = scheduler.create(taskInfo);
			System.out.println("Task created with id: " + ts.getTaskId());
		}catch (Exception e) {
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
