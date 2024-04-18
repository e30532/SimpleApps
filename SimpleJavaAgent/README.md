This document explains how to develop a simple java agent and how it looks like for better understanding Java Attach API.   

# How to run
1. specify the target PID in MyMain.java
   <img width="563" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/e2e727aa-4119-43d8-bb2b-3e365091f4d7">

2. export the library jar
<img width="684" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/28fedffb-4edd-46c0-868d-42d0dbdc1ac6">
<img width="595" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/600b4fca-cde2-4076-a2db-f8c557aca4f5">
It's necessary to use the existing MANIFEST.MF because it specifies which class will be executed as an agent class.  
<img width="601" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/c39846db-8a57-4411-902e-e8bfb1ac917a">

3. start a target server
```
[root@c81981v1 bin]# pwd
/opt/IBM/WebSphere/AppServer90ND/profiles/Dmgr01/bin
[root@c81981v1 bin]# ./startManager.sh 
ADMU0116I: Tool information is being logged in file
           /opt/IBM/WebSphere/AppServer90ND/profiles/Dmgr01/logs/dmgr/startServer.log
ADMU0128I: Starting tool with the Dmgr01 profile
ADMU3100I: Reading configuration for server: dmgr
ADMU3200I: Server launched. Waiting for initialization status.
ADMU3000I: Server dmgr open for e-business; process id is 3706094
```

As you see below, we can execute an arbitary code in the agent libary on the target process. 
```
[root@c81981v1 bin]# tail -n 1 -f ../logs/dmgr/SystemOut.log
[4/17/24 19:08:54:498 PDT] 0000007d HAManagedItem I   HAMI0024I: The VMPubController_Default Controller is active on c81981v1Cell01\c81981v1CellManager01\dmgr
[4/17/24 19:08:54:970 PDT] 000000f5 SystemOut     O Generating a javacore...
[4/17/24 19:08:55:149 PDT] 000000f5 SystemOut     O Done
[4/17/24 19:08:55:604 PDT] 0000007d HAManagedItem I   HAMI0024I: The WorkProfiler_c81981v1Cell01 Controller is active on c81981v1Cell01\c81981v1CellManager01\dmgr
[4/17/24 19:09:00:214 PDT] 0000007f ODCTreeImpl   I   ODCF0007I: Routing information was received. 
```

```
[root@c81981v1 bin]# less ../javacore.20240417.190854.3706094.0001.txt 
:
3XMTHREADINFO      "Attachment portNumber: 37121" J9VMThread:0x000000000315EB00, omrthread_t:0x00007FB9692A8F18, java/lang/Thread:0x00000007FED09EE0, state:R, prio=10
3XMJAVALTHREAD            (java/lang/Thread getId:0xF5, isDaemon:true)
3XMJAVALTHRCCL            sun/misc/Launcher$AppClassLoader(0x0000000709FA4DA8)
3XMTHREADINFO1            (native thread ID:0x3890C5, native priority:0xA, native policy:UNKNOWN, vmstate:R, vm thread flags:0x00041020)
3XMTHREADINFO2            (native stack address range from:0x00007FB8D11E9000, to:0x00007FB8D1229000, size:0x40000)
3XMCPUTIME               CPU usage total: 0.058298227 secs, current category="Application"
3XMHEAPALLOC             Heap bytes allocated since last GC cycle=262520 (0x40178)
3XMTHREADINFO3           Java callstack:
4XESTACKTRACE                at com/ibm/jvm/Dump.JavaDumpImpl(Native Method)
4XESTACKTRACE                at com/ibm/jvm/Dump.JavaDump(Dump.java:120)
4XESTACKTRACE                at simple/java/agent/lib/MyAgent.agentmain(MyAgent.java:13)
4XESTACKTRACE                at sun/reflect/NativeMethodAccessorImpl.invoke0(Native Method)
4XESTACKTRACE                at sun/reflect/NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:90(Compiled Code))
4XESTACKTRACE                at sun/reflect/DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:55(Compiled Code))
4XESTACKTRACE                at java/lang/reflect/Method.invoke(Method.java:508(Compiled Code))
4XESTACKTRACE                at sun/instrument/InstrumentationImpl.loadClassAndStartAgent(InstrumentationImpl.java:408)
4XESTACKTRACE                at sun/instrument/InstrumentationImpl.loadClassAndCallAgentmain(InstrumentationImpl.java:433)
4XESTACKTRACE                at openj9/internal/tools/attach/target/Attachment.loadAgentLibraryImpl(Native Method)
4XESTACKTRACE                at openj9/internal/tools/attach/target/Attachment.loadAgentLibrary(Attachment.java:345)
4XESTACKTRACE                at openj9/internal/tools/attach/target/Attachment.parseLoadAgent(Attachment.java:323)
4XESTACKTRACE                at openj9/internal/tools/attach/target/Attachment.doCommand(Attachment.java:189)
4XESTACKTRACE                at openj9/internal/tools/attach/target/Attachment.run(Attachment.java:149)
```
