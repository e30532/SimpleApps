package simple.java.agent.lib;

import java.lang.instrument.Instrumentation;

public class MyAgent {
	
    public static void premain(String args, Instrumentation instrumentation) {
    	agentmain(args, instrumentation);
    }
    
    public static void agentmain(String args, Instrumentation instrumentation) {
    	System.out.println("Generating a javacore...");
    	com.ibm.jvm.Dump.JavaDump();
    	System.out.println("Done");
    }
}

