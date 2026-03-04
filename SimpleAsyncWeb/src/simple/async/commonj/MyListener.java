package simple.async.commonj;

//For Commonj Timer
import commonj.timers.Timer;
import commonj.timers.TimerListener;

//For Commonj Timer
public class MyListener implements TimerListener{
//For Java EE Scheduled Executor API
//	public class MyListener implements Runnable{

	// For Commonj Timer
	@Override
	public void timerExpired(Timer arg0) {
		System.out.println("Hi");
	}
		
    // For Java EE Scheduled Executor API
	/*
	@Override
	public void run() {
		System.out.println("Hi");
	}
	*/
}
