Note: Server side EJB application is this.<br>
https://github.com/e30532/SimpleApps/blob/master/SimpleEJB3/README.md <br>


<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/38bf1a59-8803-4aff-b7a3-d685ee7508e0"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/0e840429-57cb-4937-8625-76fa4f277226"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/55677a0b-b1b7-4eb4-8194-de0917a727a0"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/4a35a5e7-40b6-4ab8-bb7f-56eb2f764337"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/4d6d854c-73a4-4082-8c07-3122e639726b"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/f2cdf89d-9deb-4cf0-95e5-165ce1eadaf4"><br>


```
[root@c81981v1 ~]# /opt/IBM/wlp24.0.0.2/wlp/bin/client create clientA
Client clientA created.
[root@c81981v1 ~]# vi /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/client.xml 
[root@c81981v1 ~]# cat /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/client.xml 
<?xml version="1.0" encoding="UTF-8"?>
<client description="new client">
    <featureManager>
        <feature>javaeeClient-7.0</feature>
    </featureManager>
    <application id="SimpleEJB3Libertyclient" name="SimpleEJB3Libertyclient" type="ear" location="/root/SimpleApps/SimpleEJB3Libertyclient.ear"/>
</client>
[root@c81981v1 ~]# /opt/IBM/wlp24.0.0.2/wlp/bin/server start defaultServer

Starting server defaultServer.
Server defaultServer started with process ID 1890860.
[root@c81981v1 ~]# /opt/IBM/wlp24.0.0.2/wlp/bin/client run clientA
Launching clientA (WebSphere Application Server 24.0.0.2/wlp-1.0.86.cl240220240212-1928) on IBM J9 VM, version 8.0.8.20 - pxa6480sr8fp20-20240112_01(SR8 FP20) (en_US)
[AUDIT   ] CWWKE0907I: The client clientA has been launched.
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[ERROR   ] CWWKI0003E: The request to the secured server on the localhost host has failed because the current configuration does not have security enabled. You must configure client-side (outbound) Common Secure Interoperability Version 2 (CSIv2) by including a keyStore element and adding the appropriate version of the appSecurity feature in a server or the appropriate version of the appSecurityClient feature in an application client container.  
[ERROR   ] CWWKI0003E: The request to the secured server on the localhost host has failed because the current configuration does not have security enabled. You must configure client-side (outbound) Common Secure Interoperability Version 2 (CSIv2) by including a keyStore element and adding the appropriate version of the appSecurity feature in a server or the appropriate version of the appSecurityClient feature in an application client container.  
[ERROR   ] CWWKZ0130E: Could not start application client SimpleEJB3Libertyclient.
[ERROR   ] CWWKZ0002E: An exception occurred while starting the application SimpleEJB3Libertyclient. The exception message was: com.ibm.ws.container.service.state.StateChangeException: com.ibm.wsspi.injectionengine.InjectionException: CWNEN0030E: The server was unable to obtain an object instance for the java:comp/env/ejb/Hello reference.  The exception message was: CWNEN1006E: The server was unable to obtain an object for the corbaname::localhost:2809#ejb/global/SimpleEJB3/SimpleEJB3EJB/Hello!simple.ejb3.view.HelloRemote binding with the simple.ejb3.view.HelloRemote type. The exception message was: org.omg.CORBA.TRANSIENT: attempt to establish connection failed:  vmcid: Apache minor code: 0x1  completed: No
[AUDIT   ] CWWKF0034I: The client installed the following features: [beanValidation-1.1, cdi-1.2, el-3.0, javaMail-1.5, javaeeClient-7.0, jaxb-2.2, jdbc-4.1, jndi-1.0, jpa-2.1, jpaContainer-2.1, jsonp-1.0, managedBeans-1.0, wasJmsClient-2.0].
[AUDIT   ] CWWKF0035I: The client clientA is running.
[AUDIT   ] CWWKE1103I: Waiting for up to 30 seconds for the client to quiesce.
[AUDIT   ] CWWKE0908I: The client clientA stopped after 12.748 seconds.
TRAS3005E: Failed to write messages to the /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/logs/messages.log file.
CWWKE0917E: The client application reported an error.
com.ibm.ws.kernel.boot.ClientRunnerException: Error while executing running the application
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchFramework(FrameworkManager.java:342)
	at com.ibm.ws.kernel.launch.internal.LauncherDelegateImpl.doFrameworkLaunch(LauncherDelegateImpl.java:116)
	at com.ibm.ws.kernel.launch.internal.LauncherDelegateImpl.launchFramework(LauncherDelegateImpl.java:102)
	at com.ibm.ws.kernel.boot.internal.KernelBootstrap.go(KernelBootstrap.java:218)
	at com.ibm.ws.appclient.boot.ClientLauncher.handleActions(ClientLauncher.java:66)
	at com.ibm.ws.kernel.boot.Launcher.createPlatform(Launcher.java:119)
	at com.ibm.ws.kernel.boot.cmdline.EnvCheck.main(EnvCheck.java:61)
	at com.ibm.ws.appclient.boot.cmdline.ClientEnvCheck.main(ClientEnvCheck.java:28)
Caused by: java.lang.IllegalStateException: Client module main failed initialization
	at com.ibm.ws.clientcontainer.internal.ClientRunnerImpl.run(ClientRunnerImpl.java:68)
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchClient(FrameworkManager.java:408)
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchFramework(FrameworkManager.java:337)
	... 7 more
[root@c81981v1 ~]# 
```



```
[root@c81981v1 ~]# vi /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/client.xml 
[root@c81981v1 ~]# cat /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/client.xml 
<?xml version="1.0" encoding="UTF-8"?>
<client description="new client">
    <featureManager>
        <feature>javaeeClient-7.0</feature>
        <feature>appSecurityClient-1.0</feature>
    </featureManager>
    <keyStore id="defaultKeyStore" password="WebASWebAS"/>
    <application id="SimpleEJB3Libertyclient" name="SimpleEJB3Libertyclient" type="ear" location="/root/SimpleApps/SimpleEJB3Libertyclient.ear"/>
</client>
[root@c81981v1 ~]# /opt/IBM/wlp24.0.0.2/wlp/bin/client run clientA
Launching clientA (WebSphere Application Server 24.0.0.2/wlp-1.0.86.cl240220240212-1928) on IBM J9 VM, version 8.0.8.20 - pxa6480sr8fp20-20240112_01(SR8 FP20) (en_US)
[AUDIT   ] CWWKE0907I: The client clientA has been launched.
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[AUDIT   ] CWPKI0803A: SSL certificate created in 3.332 seconds. SSL key file: /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/resources/security/key.p12
[WARNING ] CWWKS9702W: The following attribute(s) is missing in the IIOP client policy. The missing attribute(s) is [user password]. Unless a programmatic login is implemented by the application, ensure the attribute(s) is specified in the client.xml file.
*** SSL SIGNER EXCHANGE PROMPT ***
The SSL signer from target host is not found in trust store /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/resources/security/key.p12.

Here is the signer information (verify the digest value matches what is displayed at the server): 
Subject DN:    CN=localhost, OU=defaultServer
Issuer DN:     CN=localhost, OU=defaultServer
Serial number: 1353914636
Expires:       Thu Apr 03 20:07:18 PDT 2025
SHA-1 Digest:  63:62:35:98:BC:4D:0F:CA:B8:39:97:98:11:60:1F:9F:0C:08:1A:5E
Add signer to the trust store now? (y/n) y
CWPKI0308I: The system is adding signer alias CN=localhost, OU=defaultServer to
           
local keystore
           
/opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/resources/security/key.p12
           
with the following SHA digest:
           
63:62:35:98:BC:4D:0F:CA:B8:39:97:98:11:60:1F:9F:0C:08:1A:5E
[AUDIT   ] CWPKI0811I: The keystore file /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/resources/security/key.p12 has been modified.  The keystore file will be reloaded so the updated keystore file can be used.
[ERROR   ] CWWKZ0130E: Could not start application client SimpleEJB3Libertyclient.
[ERROR   ] CWWKZ0002E: An exception occurred while starting the application SimpleEJB3Libertyclient. The exception message was: com.ibm.ws.container.service.state.StateChangeException: com.ibm.wsspi.injectionengine.InjectionException: CWNEN0030E: The server was unable to obtain an object instance for the java:comp/env/ejb/Hello reference.  The exception message was: CWNEN1006E: The server was unable to obtain an object for the corbaname::localhost:2809#ejb/global/SimpleEJB3/SimpleEJB3EJB/Hello!simple.ejb3.view.HelloRemote binding with the simple.ejb3.view.HelloRemote type. The exception message was: org.omg.CORBA.NO_PERMISSION: :  vmcid: 0x49424000 minor code: 0x300  completed: No
[AUDIT   ] CWWKF1038I: The client added the [appSecurityClient-1.0, ssl-1.0] features to the existing feature set. 
[AUDIT   ] CWWKF0034I: The client installed the following features: [appSecurityClient-1.0, beanValidation-1.1, cdi-1.2, el-3.0, javaMail-1.5, javaeeClient-7.0, jaxb-2.2, jdbc-4.1, jndi-1.0, jpa-2.1, jpaContainer-2.1, jsonp-1.0, managedBeans-1.0, ssl-1.0, wasJmsClient-2.0].
[AUDIT   ] CWWKF0035I: The client clientA is running.
[AUDIT   ] CWWKE1103I: Waiting for up to 30 seconds for the client to quiesce.
[AUDIT   ] CWWKE0908I: The client clientA stopped after 17.341 seconds.
TRAS3005E: Failed to write messages to the /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/logs/messages.log file.
CWWKE0917E: The client application reported an error.
com.ibm.ws.kernel.boot.ClientRunnerException: Error while executing running the application
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchFramework(FrameworkManager.java:342)
	at com.ibm.ws.kernel.launch.internal.LauncherDelegateImpl.doFrameworkLaunch(LauncherDelegateImpl.java:116)
	at com.ibm.ws.kernel.launch.internal.LauncherDelegateImpl.launchFramework(LauncherDelegateImpl.java:102)
	at com.ibm.ws.kernel.boot.internal.KernelBootstrap.go(KernelBootstrap.java:218)
	at com.ibm.ws.appclient.boot.ClientLauncher.handleActions(ClientLauncher.java:66)
	at com.ibm.ws.kernel.boot.Launcher.createPlatform(Launcher.java:119)
	at com.ibm.ws.kernel.boot.cmdline.EnvCheck.main(EnvCheck.java:61)
	at com.ibm.ws.appclient.boot.cmdline.ClientEnvCheck.main(ClientEnvCheck.java:28)
Caused by: java.lang.IllegalStateException: Client module main failed initialization
	at com.ibm.ws.clientcontainer.internal.ClientRunnerImpl.run(ClientRunnerImpl.java:68)
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchClient(FrameworkManager.java:408)
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchFramework(FrameworkManager.java:337)
	... 7 more
[root@c81981v1 ~]# 
```



```
[root@c81981v1 ~]# vi /opt/IBM/wlp24.0.0.2/wlp/usr/servers/defaultServer/server.xml 
[root@c81981v1 ~]# cat /opt/IBM/wlp24.0.0.2/wlp/usr/servers/defaultServer/server.xml 
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>jsp-2.3</feature>
        <feature>localConnector-1.0</feature>
    	<feature>javaee-7.0</feature>
	</featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>
                  
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

    <keyStore password="WebASWebAS"></keyStore>
    <quickStartSecurity userName="admin" userPassword="admin"></quickStartSecurity>

    <orb id="defaultOrb">
      <serverPolicy.csiv2>
        <layers>
          <authenticationLayer establishTrustInClient="Never"/>
        </layers>
      </serverPolicy.csiv2>
    </orb>
</server>
[root@c81981v1 ~]# /opt/IBM/wlp24.0.0.2/wlp/bin/client run clientA
Launching clientA (WebSphere Application Server 24.0.0.2/wlp-1.0.86.cl240220240212-1928) on IBM J9 VM, version 8.0.8.20 - pxa6480sr8fp20-20240112_01(SR8 FP20) (en_US)
[AUDIT   ] CWWKE0907I: The client clientA has been launched.
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[WARNING ] CWWKS9702W: The following attribute(s) is missing in the IIOP client policy. The missing attribute(s) is [user password]. Unless a programmatic login is implemented by the application, ensure the attribute(s) is specified in the client.xml file.
[ERROR   ] CWWKZ0130E: Could not start application client SimpleEJB3Libertyclient.
[ERROR   ] CWWKZ0002E: An exception occurred while starting the application SimpleEJB3Libertyclient. The exception message was: com.ibm.ws.container.service.state.StateChangeException: com.ibm.wsspi.injectionengine.InjectionException: CWNEN0030E: The server was unable to obtain an object instance for the java:comp/env/ejb/Hello reference.  The exception message was: CWNEN1006E: The server was unable to obtain an object for the corbaname::localhost:2809#ejb/global/SimpleEJB3/SimpleEJB3EJB/Hello!simple.ejb3.view.HelloRemote binding with the simple.ejb3.view.HelloRemote type. The exception message was: org.omg.CORBA.BAD_PARAM: other: corbaname evaluation error:an unknown user exception was raised: IDL:omg.org/CosNaming/NamingContext/NotFound:1.0:  vmcid: OMG minor code: 0xa  completed: No
[AUDIT   ] CWWKF0034I: The client installed the following features: [appSecurityClient-1.0, beanValidation-1.1, cdi-1.2, el-3.0, javaMail-1.5, javaeeClient-7.0, jaxb-2.2, jdbc-4.1, jndi-1.0, jpa-2.1, jpaContainer-2.1, jsonp-1.0, managedBeans-1.0, ssl-1.0, wasJmsClient-2.0].
[AUDIT   ] CWWKF0035I: The client clientA is running.
[AUDIT   ] CWWKE1103I: Waiting for up to 30 seconds for the client to quiesce.
[AUDIT   ] CWWKE0908I: The client clientA stopped after 10.206 seconds.
TRAS3005E: Failed to write messages to the /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/logs/messages.log file.
CWWKE0917E: The client application reported an error.
com.ibm.ws.kernel.boot.ClientRunnerException: Error while executing running the application
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchFramework(FrameworkManager.java:342)
	at com.ibm.ws.kernel.launch.internal.LauncherDelegateImpl.doFrameworkLaunch(LauncherDelegateImpl.java:116)
	at com.ibm.ws.kernel.launch.internal.LauncherDelegateImpl.launchFramework(LauncherDelegateImpl.java:102)
	at com.ibm.ws.kernel.boot.internal.KernelBootstrap.go(KernelBootstrap.java:218)
	at com.ibm.ws.appclient.boot.ClientLauncher.handleActions(ClientLauncher.java:66)
	at com.ibm.ws.kernel.boot.Launcher.createPlatform(Launcher.java:119)
	at com.ibm.ws.kernel.boot.cmdline.EnvCheck.main(EnvCheck.java:61)
	at com.ibm.ws.appclient.boot.cmdline.ClientEnvCheck.main(ClientEnvCheck.java:28)
Caused by: java.lang.IllegalStateException: Client module main failed initialization
	at com.ibm.ws.clientcontainer.internal.ClientRunnerImpl.run(ClientRunnerImpl.java:68)
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchClient(FrameworkManager.java:408)
	at com.ibm.ws.kernel.launch.internal.FrameworkManager.launchFramework(FrameworkManager.java:337)
	... 7 more
[root@c81981v1 ~]# 
```


```
[root@c81981v1 ~]# vi /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/client.xml 
[root@c81981v1 ~]# cat /opt/IBM/wlp24.0.0.2/wlp/usr/clients/clientA/client.xml 
<?xml version="1.0" encoding="UTF-8"?>
<client description="new client">
    <featureManager>
        <feature>javaeeClient-7.0</feature>
        <feature>appSecurityClient-1.0</feature>
    </featureManager>
    <keyStore id="defaultKeyStore" password="WebASWebAS"/>
    <application id="SimpleEJB3Libertyclient" name="SimpleEJB3Libertyclient" type="ear" location="/root/SimpleApps/SimpleEJB3Libertyclient.ear"/>
    <orb id="defaultOrb">
      <clientPolicy.clientContainerCsiv2>
        <layers>
          <authenticationLayer user="admin" password="admin"/>
        </layers>
      </clientPolicy.clientContainerCsiv2>
    </orb>
</client>
[root@c81981v1 ~]# /opt/IBM/wlp24.0.0.2/wlp/bin/client run clientA
Launching clientA (WebSphere Application Server 24.0.0.2/wlp-1.0.86.cl240220240212-1928) on IBM J9 VM, version 8.0.8.20 - pxa6480sr8fp20-20240112_01(SR8 FP20) (en_US)
[AUDIT   ] CWWKE0907I: The client clientA has been launched.
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[AUDIT   ] CWWKZ0001I: Application SimpleEJB3Libertyclient started in 2.051 seconds.
[AUDIT   ] CWWKF0034I: The client installed the following features: [appSecurityClient-1.0, beanValidation-1.1, cdi-1.2, el-3.0, javaMail-1.5, javaeeClient-7.0, jaxb-2.2, jdbc-4.1, jndi-1.0, jpa-2.1, jpaContainer-2.1, jsonp-1.0, managedBeans-1.0, ssl-1.0, wasJmsClient-2.0].
[AUDIT   ] CWWKF0035I: The client clientA is running.
Hello IBM
[AUDIT   ] CWWKE1103I: Waiting for up to 30 seconds for the client to quiesce.
[AUDIT   ] CWWKZ0009I: The application SimpleEJB3Libertyclient has stopped successfully.
[AUDIT   ] CWWKE0908I: The client clientA stopped after 10.795 seconds.
[root@c81981v1 ~]# 
```
