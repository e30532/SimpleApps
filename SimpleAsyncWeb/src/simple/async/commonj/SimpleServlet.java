package simple.async.commonj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

//For Java EE Concurrency and Scheduled Excutor API
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import javax.annotation.Resource;
//import javax.enterprise.concurrent.ManagedExecutorService;
//import javax.enterprise.concurrent.ManagedScheduledExecutorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//For Concurrency and Timer CommonJ 
import javax.naming.InitialContext;
import commonj.timers.Timer;
import commonj.timers.TimerListener;
import commonj.work.WorkItem;
import commonj.work.WorkManager;

/**
 * Servlet implementation class SimpleServletCommonJ
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//For Java EE Concurrency and Scheduled Excutor API
	/*
	@Resource
	private ManagedExecutorService executor;
	@Resource
	private ManagedScheduledExecutorService scheduler;
//	/*

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

			// For CommonJ Concurrency
//			/*
			InitialContext ctx  = new InitialContext();
			commonj.work.WorkManager wm =
			    (commonj.work.WorkManager) ctx.lookup("java:comp/env/wm/default");

			MyWork work1 = new MyWork();
			MyWork work2 = new MyWork();
				
			WorkItem item1 = wm.schedule(work1);
			WorkItem item2 = wm.schedule(work2);
				
			Collection col1 = new ArrayList();
			col1.add(item1);
			col1.add(item2);
			wm.waitForAll(col1, WorkManager.INDEFINITE);

			System.out.println("work1 data="+work1.getData());
			System.out.println("work2 data="+work2.getData());
//			*/
			
			// For Java EE Concurrency API
			/*
			MyWork work1 = new MyWork();
			MyWork work2 = new MyWork();

			Future<?> f1 = executor.submit(work1);
			Future<?> f2 = executor.submit(work2);

			f1.get();
			f2.get();

			System.out.println("work1 data=" + work1.getData());
			System.out.println("work2 data=" + work2.getData());
			*/
			
			// For CommonJ Timer
//			/*
			commonj.timers.TimerManager tm = (commonj.timers.TimerManager) ctx.lookup("java:comp/env/tm/default");
			TimerListener listener = new MyListener();
			System.out.println("The timer task will be excuted in 30 seconds.");
			Timer timer = tm.schedule(listener, 1000 * 30);
//			*/
			
			// For Java EE Scheduled Executor API
			/*
			System.out.println("The timer task will be executed in 30 seconds.");
			scheduler.schedule(new MyListener(), 30, TimeUnit.SECONDS);
			*/
		}catch(Exception e) {
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
