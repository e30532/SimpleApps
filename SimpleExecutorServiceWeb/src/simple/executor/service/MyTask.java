package simple.executor.service;

public class MyTask implements Runnable{
	int count;
	int sleeptime;
	
	public MyTask(int count, int sleeptime) {
		this.count = count;
		this.sleeptime = sleeptime;
	}
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + ": I'm going to sleep.. count[" + count +"]");
		try {
			Thread.sleep(sleeptime*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + ": wake up! count[" + count +"]");
	}

}
