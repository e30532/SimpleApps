This is a simple steps to build a simple legacy spring application with JPA that uses own eclipselink implementation.      


# application build
update pom.xml   
<img width="712" alt="image" src="https://github.com/user-attachments/assets/5d553691-eb5b-4fce-a258-eb678de41059">   
<img width="605" alt="image" src="https://github.com/user-attachments/assets/b828d417-459a-491f-842d-48c4b3fcce77">   
run maven install   
<img width="646" alt="image" src="https://github.com/user-attachments/assets/83e2c455-6a27-4808-a1b8-648329dc7a2a">   
<img width="758" alt="image" src="https://github.com/user-attachments/assets/947635ad-225c-4b22-a94e-09b78b6b4886">   


# Liberty server configuration 
Reference: https://www.ibm.com/docs/en/was-liberty/base?topic=liberty-deploying-jpa-application
```
[root@c89289v1 bin]# cat ../usr/servers/test2/server.xml 
<?xml version="1.0" encoding="UTF-8"?>
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>servlet-3.1</feature>
        <feature>jsp-2.3</feature>
        <feature>jpaContainer-2.1</feature>
        <feature>bells-1.0</feature> 
        <feature>jdbc-4.1</feature>
        <feature>ssl-1.0</feature>
    </featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint id="defaultHttpEndpoint"
                  host="*"
                  httpPort="19080"
                  httpsPort="19443">
    </httpEndpoint>

    <keyStore id="defaultKeyStore" password="PassW0rd"/> 
    <library id="DB2JCC4Lib">
       <fileset dir="/opt/ibm/db2/V11.5/java/" includes="db2jcc4.jar"/>
    </library>
    <dataSource id="DefaultDataSource" jndiName="jdbc/SimpleDS">
       <connectionManager agedTimeout="0" connectionTimeout="10s" maxPoolSize="20" minPoolSize="5"/>
       <jdbcDriver libraryRef="DB2JCC4Lib"/>
       <properties.db2.jcc databaseName="WASDB" password="******" portNumber="50000" serverName="***.***.ibm.com" user="db2inst1"/>
    </dataSource>

    <bell libraryRef="myLib"/>
    <library id="myLib">
       <fileset dir="/home/ibmwasl2/Downloads" includes="eclipselink-2.7.15.jar"/>
    </library>

    <jpa defaultPersistenceProvider="org.eclipse.persistence.jpa.PersistenceProvider"/>

    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

</server>
```

# import the schema server certification.
```
[root@c89289v1 bin]# echo "" |openssl s_client -connect www.springframework.org:443 | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > /tmp/tmp.crt
depth=2 C = US, O = Google Trust Services LLC, CN = GTS Root R4
verify return:1
depth=1 C = US, O = Google Trust Services, CN = WE1
verify return:1
depth=0 CN = springframework.org
verify return:1
DONE
[root@c89289v1 bin]# /opt/IBM/HTTPServer90/bin/gskcapicmd -cert -add -db /opt/IBM/wlp24.0.0.11/wlp/usr/servers/test2/resources/security/key.p12 -pw PassW0rd -label spring -file /tmp/tmp.crt
```

# deploy and access the application 
[root@c89289v1 bin]# cp /root/git/SimpleApps/legacy.jpa/target/legacy.jpa-0.0.1-SNAPSHOT.war ../usr/servers/test2/dropins/
[root@c89289v1 bin]# ./server start test2 --clean

Starting server test2.
Server test2 started with process ID 4131421.
[root@c89289v1 bin]# 

http://fyre1:19080/legacy.jpa-0.0.1-SNAPSHOT/

```
[root@c89289v1 bin]# cat ../usr/servers/test2/logs/messages.log 
********************************************************************************
product = WebSphere Application Server 24.0.0.11 (wlp-1.0.95.cl241120241021-1102)
wlp.install.dir = /opt/IBM/wlp24.0.0.11/wlp/
java.home = /opt/IBM/WebSphere/AppServer90ND/java/8.0/jre
java.version = 1.8.0_421
java.runtime = Java(TM) SE Runtime Environment (8.0.8.30 - pxa6480sr8fp30-20240801_01(SR8 FP30))
os = Linux (4.18.0-553.27.1.el8_10.x86_64; amd64) (en_US)
process = 4131421@9.46.97.119
Classpath = /opt/IBM/wlp24.0.0.11/wlp/bin/tools/ws-server.jar:/opt/IBM/wlp24.0.0.11/wlp/bin/tools/ws-javaagent.jar
Java Library path = /opt/IBM/WebSphere/AppServer90ND/java/8.0/jre/lib/amd64/compressedrefs:/opt/IBM/WebSphere/AppServer90ND/java/8.0/jre/lib/amd64:/usr/lib64:/usr/lib
********************************************************************************
[11/28/24 20:22:53:649 PST] 00000001 com.ibm.ws.kernel.launch.internal.FrameworkManager           A CWWKE0001I: The server test2 has been launched.
[11/28/24 20:22:54:978 PST] 00000001 com.ibm.ws.kernel.launch.internal.FrameworkManager           I CWWKE0002I: The kernel started after 1.556 seconds
[11/28/24 20:22:55:259 PST] 00000033 com.ibm.ws.kernel.feature.internal.FeatureManager            I CWWKF0007I: Feature update started.
[11/28/24 20:22:56:215 PST] 0000002a com.ibm.ws.app.manager.internal.monitor.DropinMonitor        A CWWKZ0058I: Monitoring dropins for applications.
[11/28/24 20:22:56:714 PST] 00000037 com.ibm.ws.app.manager.AppMessageHelper                      I CWWKZ0018I: Starting application legacy.jpa-0.0.1-SNAPSHOT.
[11/28/24 20:22:56:715 PST] 00000037 bm.ws.app.manager.war.internal.WARDeployedAppInfoFactoryImpl I CWWKZ0133I: The legacy.jpa-0.0.1-SNAPSHOT application at the /opt/IBM/wlp24.0.0.11/wlp/usr/servers/test2/dropins/legacy.jpa-0.0.1-SNAPSHOT.war location is being expanded to the /opt/IBM/wlp24.0.0.11/wlp/usr/servers/test2/apps/expanded/legacy.jpa-0.0.1-SNAPSHOT.war directory.
[11/28/24 20:22:56:973 PST] 0000002f com.ibm.ws.ssl.config.WSKeyStore                             I Successfully loaded default keystore: /opt/IBM/wlp24.0.0.11/wlp/usr/servers/test2/resources/security/key.p12 of type: PKCS12
[11/28/24 20:22:57:645 PST] 00000037 com.ibm.ws.jpa.container.thirdparty.ThirdPartyJPAProvider    I CWWJP0006I: The org.eclipse.persistence.jpa.PersistenceProvider class is loaded as the default Java Persistence API (JPA) provider.
[11/28/24 20:22:57:646 PST] 00000037 com.ibm.ws.jpa.container.thirdparty.ThirdPartyJPAProvider    I CWWJP0006I: The org.eclipse.persistence.jpa.PersistenceProvider class is loaded as the default Java Persistence API (JPA) provider.
[11/28/24 20:22:57:662 PST] 00000037 com.ibm.ws.jca.cm.ConnectorService                           I J2CA8050I: An authentication alias should be used instead of defining a user name and password on dataSource[DefaultDataSource].
[11/28/24 20:22:57:899 PST] 00000037 com.ibm.ws.recoverylog.spi.RecoveryDirectorImpl              I CWRLS0010I: Performing recovery processing for local WebSphere server (test2).
[11/28/24 20:22:57:940 PST] 00000037 com.ibm.ws.recoverylog.spi.RecoveryDirectorImpl              I CWRLS0012I: All persistent services have been directed to perform recovery processing for this WebSphere server (test2).
[11/28/24 20:22:57:940 PST] 00000044 com.ibm.tx.jta.impl.RecoveryManager                          I WTRN0135I: Transaction service recovering no transactions.
[11/28/24 20:22:57:955 PST] 00000037 com.ibm.ws.jpa.AbstractJPAProviderIntegration                I CWWJP0052I: A third-party JPA provider, org.eclipse.persistence.jpa.PersistenceProvider, is used.
[11/28/24 20:22:57:972 PST] 00000037 com.ibm.ws.session.WASSessionCore                            I SESN8501I: The session manager did not find a persistent storage location; HttpSession objects will be stored in the local application server's memory.
[11/28/24 20:22:57:979 PST] 00000037 com.ibm.ws.webcontainer.osgi.webapp.WebGroup                 I SRVE0169I: Loading Web Module: legacy.jpa-0.0.1-SNAPSHOT.
[11/28/24 20:22:57:980 PST] 00000037 com.ibm.ws.webcontainer                                      I SRVE0250I: Web Module legacy.jpa-0.0.1-SNAPSHOT has been bound to default_host.
[11/28/24 20:22:57:981 PST] 00000037 com.ibm.ws.http.internal.VirtualHostImpl                     A CWWKT0016I: Web application available (default_host): http://fyre1:19080/legacy.jpa-0.0.1-SNAPSHOT/
[11/28/24 20:22:58:055 PST] 00000035 com.ibm.ws.webcontainer.osgi.mbeans.PluginGenerator          I SRVE9103I: A configuration file for a web server plugin was automatically generated for this server at /opt/IBM/wlp24.0.0.11/wlp/usr/servers/test2/logs/state/plugin-cfg.xml.
[11/28/24 20:22:58:316 PST] 0000003d com.ibm.ws.session.WASSessionCore                            I SESN0176I: A new session context will be created for application key default_host/legacy.jpa-0.0.1-SNAPSHOT
[11/28/24 20:22:58:320 PST] 0000003d com.ibm.ws.util                                              I SESN0172I: The session manager is using the Java default SecureRandom implementation for session ID generation.
[11/28/24 20:22:58:354 PST] 0000003d com.ibm.ws.webcontainer.webapp                               I SRVE0292I: Servlet Message - [legacy.jpa-0.0.1-SNAPSHOT]:.No Spring WebApplicationInitializer types detected on classpath
[11/28/24 20:22:58:446 PST] 0000003d com.ibm.ws.webcontainer.webapp                               I SRVE0292I: Servlet Message - [legacy.jpa-0.0.1-SNAPSHOT]:.Initializing Spring root WebApplicationContext
[11/28/24 20:22:58:447 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.context.ContextLoader - Root WebApplicationContext: initialization started
[11/28/24 20:22:58:549 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.context.support.XmlWebApplicationContext - Refreshing Root WebApplicationContext: startup date [Thu Nov 28 20:22:58 PST 2024]; root of context hierarchy
[11/28/24 20:22:58:592 PST] 0000003d SystemOut                                                    O INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from ServletContext resource [/WEB-INF/spring/root-context.xml]
[11/28/24 20:22:59:550 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.context.ContextLoader - Root WebApplicationContext: initialization completed in 1103 ms
[11/28/24 20:22:59:551 PST] 0000003d com.ibm.ws.app.manager.AppMessageHelper                      A CWWKZ0001I: Application legacy.jpa-0.0.1-SNAPSHOT started in 2.837 seconds.
[11/28/24 20:22:59:565 PST] 00000033 com.ibm.ws.tcpchannel.internal.TCPPort                       I CWWKO0219I: TCP Channel defaultHttpEndpoint has been started and is now listening for requests on host *  (IPv4) port 19080.
[11/28/24 20:22:59:567 PST] 00000033 com.ibm.ws.tcpchannel.internal.TCPPort                       I CWWKO0219I: TCP Channel defaultHttpEndpoint-ssl has been started and is now listening for requests on host *  (IPv4) port 19443.
[11/28/24 20:22:59:573 PST] 0000003d com.ibm.ws.webcontainer.webapp                               I SRVE0292I: Servlet Message - [legacy.jpa-0.0.1-SNAPSHOT]:.Initializing Spring FrameworkServlet 'appServlet'
[11/28/24 20:22:59:574 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.servlet.DispatcherServlet - FrameworkServlet 'appServlet': initialization started
[11/28/24 20:22:59:579 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.context.support.XmlWebApplicationContext - Refreshing WebApplicationContext for namespace 'appServlet-servlet': startup date [Thu Nov 28 20:22:59 PST 2024]; parent: Root WebApplicationContext
[11/28/24 20:22:59:581 PST] 0000003d SystemOut                                                    O INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from ServletContext resource [/WEB-INF/spring/app/servlet-context.xml]
[11/28/24 20:22:59:626 PST] 00000033 com.ibm.ws.kernel.feature.internal.FeatureManager            A CWWKF0012I: The server installed the following features: [bells-1.0, el-3.0, jdbc-4.1, jndi-1.0, jpaContainer-2.1, jsp-2.3, servlet-3.1, ssl-1.0].
[11/28/24 20:22:59:626 PST] 00000033 com.ibm.ws.kernel.feature.internal.FeatureManager            I CWWKF0008I: Feature update completed in 4.668 seconds.
[11/28/24 20:22:59:627 PST] 00000033 com.ibm.ws.kernel.feature.internal.FeatureManager            A CWWKF0011I: The test2 server is ready to run a smarter planet. The test2 server started in 6.223 seconds.
[11/28/24 20:23:00:565 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping - Mapped "{[/],methods=[GET]}" onto public java.lang.String legacy.jpa.HomeController.home(org.springframework.ui.Model)
[11/28/24 20:23:00:748 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter - Looking for @ControllerAdvice: WebApplicationContext for namespace 'appServlet-servlet': startup date [Thu Nov 28 20:22:59 PST 2024]; parent: Root WebApplicationContext
[11/28/24 20:23:00:798 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter - Looking for @ControllerAdvice: WebApplicationContext for namespace 'appServlet-servlet': startup date [Thu Nov 28 20:22:59 PST 2024]; parent: Root WebApplicationContext
[11/28/24 20:23:00:863 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.servlet.handler.SimpleUrlHandlerMapping - Mapped URL path [/resources/**] onto handler 'org.springframework.web.servlet.resource.ResourceHttpRequestHandler#0'
[11/28/24 20:23:00:971 PST] 0000003d SystemOut                                                    O INFO : org.springframework.web.servlet.DispatcherServlet - FrameworkServlet 'appServlet': initialization completed in 1397 ms
[11/28/24 20:23:00:972 PST] 0000003d com.ibm.ws.webcontainer.servlet                              I SRVE0242I: [legacy.jpa-0.0.1-SNAPSHOT] [/legacy.jpa-0.0.1-SNAPSHOT] [appServlet]: Initialization successful.
[11/28/24 20:23:05:825 PST] 00000046 SystemOut                                                    O [EL Info]: 2024-11-28 20:23:05.819--ServerSession(333722178)--EclipseLink, version: Eclipse Persistence Services - 2.7.15.v20240516-53511fdbd8
[11/28/24 20:23:07:108 PST] 00000046 com.ibm.ws.rsadapter.impl.DatabaseHelper                     I DSRA8203I: Database product name : DB2/LINUXX8664
[11/28/24 20:23:07:110 PST] 00000046 com.ibm.ws.rsadapter.impl.DatabaseHelper                     I DSRA8204I: Database product version : SQL110570
[11/28/24 20:23:07:110 PST] 00000046 com.ibm.ws.rsadapter.impl.DatabaseHelper                     I DSRA8205I: JDBC driver name  : IBM Data Server Driver for JDBC and SQLJ
[11/28/24 20:23:07:111 PST] 00000046 com.ibm.ws.rsadapter.impl.DatabaseHelper                     I DSRA8206I: JDBC driver version  : 4.31.10
[11/28/24 20:23:07:303 PST] 00000046 SystemOut                                                    O [EL Warning]: 2024-11-28 20:23:07.303--ServerSession(333722178)--Failed to find MBean Server: null or empty List returned from MBeanServerFactory.findMBeanServer(null)
[11/28/24 20:23:07:498 PST] 00000046 SystemOut                                                    O All Persons: [legacy.jpa.Emp2@c61a8233]
[11/28/24 20:23:08:195 PST] 00000046 com.ibm.ws.webcontainer.servlet                              I SRVE0242I: [legacy.jpa-0.0.1-SNAPSHOT] [/legacy.jpa-0.0.1-SNAPSHOT] [/WEB-INF/views/home.jsp]: Initialization successful.
[root@c89289v1 bin]#
```

