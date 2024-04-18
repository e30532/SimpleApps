package simple.java.agent;

import com.sun.tools.attach.VirtualMachine;

public class MyMain {

    public static void main(String[] args) {
        try {
            VirtualMachine vm = VirtualMachine.attach("3720595");
            vm.loadAgent("/tmp/SimpleJavaAgentLib.jar", null);
            vm.detach();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

