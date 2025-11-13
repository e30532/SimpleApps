package simple.ejbtimer;

import java.util.Collection;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

@Singleton
@Startup
public class MyTimer {


	@Resource
    private TimerService timerService;


    @javax.annotation.PostConstruct
    private void init() {

    	boolean create = false;
    	Collection<Timer> timers = timerService.getTimers();
    	if (timers.isEmpty())create = true;
        for (Timer t : timers) {
            Object info = t.getInfo();
            if (info instanceof String) {
                String s = (String) info;
                if (!s.contains("MyTimer"))create = true;
            }
        }
        if(create) {
        	long initialDuration = 0;
        	long intervalDuration = 5000;

        	TimerConfig config = new TimerConfig();
        	config.setInfo("MyTimer");
        	config.setPersistent(true);

        	timerService.createIntervalTimer(initialDuration, intervalDuration, config);
        }
    }


    @Timeout
    public void onTimeout(Timer timer) {
        String info = (String) timer.getInfo();
        System.out.println(info + ":" + new java.util.Date());
    }
}