

```
start a batchlet job
# curl -X POST http://localhost:9080/SimpleJBatchWeb/SimpleServlet


[11/11/25 2:11:03:831 EST] 000000c8 BatchRuntime  1   Disable IBM Implementation = false; Disable prop = (com.ibm.ws.jbatch.disable => null).
[11/11/25 2:11:03:911 EST] 000000c8 JobOperatorIm 1   Starting job: jobXMLName = SimpleJob
[11/11/25 2:11:03:914 EST] 000000c8 MemoryPersist I   CWWKY0005I: The batch In-Memory persistence service is activated.
[11/11/25 2:11:03:925 EST] 000000c8 DelegatingJob 1   looking up batch job xml at META-INF/batch-jobs/SimpleJob.xml
[11/11/25 2:11:03:926 EST] 000000c8 DelegatingJob 2   Loaded job xml with SimpleJob from META-INF/batch-jobs/
[11/11/25 2:11:03:927 EST] 000000c8 BatchKernelIm 1   Starting job with JSL: <job id="SimpleJob" xmlns="http://xmlns.jcp.org/xml/ns/javaee" version="1.0">
    <step id="step1">
        <batchlet ref="SimpleBatchlet"/>
    </step>
</job>... truncated ...
[11/11/25 2:11:04:161 EST] 000000c8 JNDIDelegatin > com.ibm.jbatch.container.services.impl.JNDIDelegatingThreadPoolServiceImpl executeTask ENTRY
[11/11/25 2:11:04:650 EST] 000000c8 WorkManagerIm >  execute Entry
[11/11/25 2:11:04:650 EST] 000000c8 WorkManagerIm >  submit Entry
[11/11/25 2:11:04:682 EST] 000000c8 WorkManagerIm <  submit Exit
                                 com.ibm.ws.asynchbeans.SubmittedTask@e6dfda1a
[11/11/25 2:11:04:682 EST] 000000c8 WorkManagerIm <  execute Exit
[11/11/25 2:11:04:682 EST] 000000c8 JNDIDelegatin < com.ibm.jbatch.container.services.impl.JNDIDelegatingThreadPoolServiceImpl executeTask RETURN
[11/11/25 2:11:04:682 EST] 000000c8 JobOperatorIm 1   Started job execution with executionId: 0
[11/11/25 2:11:04:682 EST] 000000c8 SystemOut     O 0

[11/11/25 2:11:04:683 EST] 000000cc SubmittedTask >  run Entry
[11/11/25 2:11:04:813 EST] 000000cc SystemOut     O SimpleJob:0
[11/11/25 2:11:04:817 EST] 000000cc JobLogger     1   CWWKY0020I: Step step1 ended with batch status COMPLETED and exit status COMPLETED for job instance 0 and job execution 0.
[11/11/25 2:11:04:831 EST] 000000cc JobLogger     1   CWWKY0010I: Job SimpleJob ended with batch status COMPLETED and exit status COMPLETED for job instance 0 and job execution 0.
[11/11/25 2:11:04:831 EST] 000000cc JobLogger     1   
==========================================================
Completed invoking execution for a job
 JobInstance id = 0
 JobExecution id = 0
 Job Name = SimpleJob
 Job Parameters = {}
 Job Batch Status = COMPLETED, Job Exit Status = COMPLETED
==========================================================
[11/11/25 2:11:04:831 EST] 000000cc RuntimeJobExe 1   
==========================================================
Completed invoking execution for a job
 JobInstance id = 0
 JobExecution id = 0
 Job Name = SimpleJob
 Job Parameters = {}
 Job Batch Status = COMPLETED, Job Exit Status = COMPLETED
==========================================================
[11/11/25 2:11:04:831 EST] 000000cc SubmittedTask <  run Exit




retrieve batch details.
# curl -X GET http://localhost:9080/SimpleJBatchWeb/SimpleServlet

[11/11/25 2:11:43:374 EST] 000000cd SystemOut     O   JobInstanceId=0, JobName=SimpleJob
[11/11/25 2:11:43:384 EST] 000000cd SystemOut     O   ExecutionId=0, Status=COMPLETED, ExitStatus=COMPLETED, StartTime=Tue Nov 11 02:11:04 EST 2025, EndTime=Tue Nov 11 02:11:04 EST 2025
```
/opt/IBM/WebSphere/AppServer90ND/plugins/com.ibm.jbatch.runtime.jar
