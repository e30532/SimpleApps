package simple.async.commonj;

import java.util.concurrent.ThreadLocalRandom;

//For CommonJ Concurrency
public class MyWork implements commonj.work.Work{
//For Java EE Concurrency API
//public class MyWork implements Runnable{

	private String state;
	
	@Override
	public void run() {
		this.state = "Begun";
	    try {
	        int seconds = ThreadLocalRandom.current().nextInt(1, 11);
	        System.out.println("Sleeping for " + seconds + " seconds");
	        Thread.sleep(seconds * 1000L);
	        System.out.println("Wake up!");
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
		this.state = "Finished";
	}


	//For CommonJ Concurrency
	@Override
	public boolean isDaemon() {
		// TODO Auto-generated method stub
		return false;
	}
	
	//For CommonJ Concurrency
	@Override
	public void release() {
		// TODO Auto-generated method stub
		
	}

	public String getData() {
		return this.state;
	}


}
