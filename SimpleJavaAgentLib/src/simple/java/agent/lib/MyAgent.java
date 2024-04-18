package simple.java.agent.lib;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MyAgent {
	
    public static void premain(String args, Instrumentation instrumentation) {
    	agentmain(args, instrumentation);
    }
    
    public static void agentmain(String args, Instrumentation instrumentation) {
    	System.out.println("Generating a javacore...");
    	com.ibm.jvm.Dump.JavaDump();
    	System.out.println("Done");

    	//This is an example of how to execute WebSphere specific function.
    	new Thread(() -> {
            System.out.println("New thread running...");
            Class<?>[] loadedClasses = instrumentation.getAllLoadedClasses();
            ClassLoader wasloader = null;
            for (Class<?> cls : loadedClasses) {
            	if(cls.getName().equals("com.ibm.ws.runtime.WsServer")) {
            		wasloader = cls.getClassLoader();
                    try {
                    	Class<?> adminServiceFactoryClass = wasloader.loadClass("com.ibm.websphere.management.AdminServiceFactory");
                    	Method getAdminServiceMethod = adminServiceFactoryClass.getMethod("getAdminService", new Class[0]);
                    	Object adminService = getAdminServiceMethod.invoke(null, new Object[0]);
                    	Field mbServer = adminService.getClass().getDeclaredField("_mbServer");
                    	mbServer.setAccessible(true);
                    	MBeanServer mbs = (MBeanServer)mbServer.get(adminService);          	
                    	Set<ObjectName> mbeans = mbs.queryNames(new ObjectName("WebSphere:name=ClusterMgr,type=ClusterMgr,*"), null);
                    	for (ObjectName name: mbeans) {
                    		mbs.invoke(name, "retrieveClusters", null, null);
                    		
                    	}
                    	System.out.println(mbeans.size());
                    }catch(Exception e) {
                    	e.printStackTrace();
                    }
            	}
            }
        }).start();
    }
}

