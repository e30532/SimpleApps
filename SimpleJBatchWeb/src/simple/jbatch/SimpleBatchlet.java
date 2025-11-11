package simple.jbatch;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

@Dependent
@Named("SimpleBatchlet")
public class SimpleBatchlet implements Batchlet{

	@Inject
    private JobContext jobContext;
	
	@Override
	public String process() throws Exception {
		System.out.println(jobContext.getJobName() + ":" + jobContext.getInstanceId());
		return "COMPLETED";
	}

	@Override
	public void stop() throws Exception {
		System.out.println("SimpleBatchlet.stop()>");
		System.out.println("SimpleBatchlet.stop()<");		
	}

}
