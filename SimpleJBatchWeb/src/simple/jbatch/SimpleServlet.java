package simple.jbatch;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
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
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		List<JobInstance> instances = jobOperator.getJobInstances("SimpleJob", 0, 10);
		for (JobInstance ji : instances) {
            System.out.println("  JobInstanceId=" + ji.getInstanceId() + ", JobName=" + ji.getJobName());
            List<JobExecution> executions = jobOperator.getJobExecutions(ji);
            for (JobExecution je : executions) {
                System.out.println("  ExecutionId=" + je.getExecutionId()
                        + ", Status=" + je.getBatchStatus()
                        + ", ExitStatus=" + je.getExitStatus()
                        + ", StartTime=" + je.getStartTime()
                        + ", EndTime=" + je.getEndTime());
            }
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties params = new Properties();
		long executionId = jobOperator.start("SimpleJob", params);
		System.out.println(executionId);
	}

}
