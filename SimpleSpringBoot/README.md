
# Summary
1. SystemOut logging stop issue can occur if a customer is still using an older Spring version (v1.x.x). It was identified as a Spring defect and was resolved in version 2.   
2. you can ask the customer's application developer to see if the application set the root logger level to OFF.   
3. as a workaround, you can rename jul-to-slf4j-*.*.*.jar   
4. if the application's logging setting is externalized like application.properties or logback-spring.xml, we may update the configuraion file of the expanded binary directly. 


   
# 1. background
Working as a WebSphere technical support engineer, I sometimes see that WebSphere stops writing to SystemOut.log while a Spring application is starting. In short, this is described in https://www.ibm.com/support/pages/server-logging-ends-abruptly-when-using-slf4j-application  In this document, I'd like to add a bit more for better understanding. 

# 2. very basic for building an old spring boot application
As of now(Nov, 2024), the latest spring boot version is 3.3.5, which requires Java17. If we would like to build a spring boot application that can be deployed on tWASv8.5/v9 (Java8), the building process a little bit tricky. Here is a step-by-step instruction.

2.1. download eclipse IDE (I used Eclipse IDE for Enterprise Java and Web Developers 2024-09).  
<img width="849" alt="Eclipse IDE 2024-09 R Packages" src="https://github.com/user-attachments/assets/e82b31c1-49af-4645-80bd-a47ef0707646">

2.2. launch the eclipse.
```
[root@c89289v1 Downloads]# tar xzf eclipse-jee-2024-09-R-linux-gtk-x86_64.tar.gz 
[root@c89289v1 Downloads]# mv eclipse eclipse-2024-09
[root@c89289v1 Downloads]# ./eclipse-2024-09/eclipse
```
<img width="677" alt="Eclipse IDE Launcher" src="https://github.com/user-attachments/assets/3487c0be-5bd8-47d3-80b3-2f20c53ef096">.  

2.3. install Spring Tools v4.   
<img width="604" alt="Eclipse Marketplace" src="https://github.com/user-attachments/assets/c43b0327-01b9-4168-b688-04259400ca06">   

2.4. create a spring starter project.   
<img width="605" alt="Select a wizard" src="https://github.com/user-attachments/assets/76876d6f-fe60-49dc-b4a8-30b91a89aa56">   

2.5. change the Service URL.   
The latest official service URL for starter project supports Java17+. To create a spring project for Java8, we can use an alternate URL. (https://start.aliyun.com/) provided by Alibaba.   
<img width="606" alt="New Spring Starter Project" src="https://github.com/user-attachments/assets/80dafacb-8df5-47fb-bdc7-7d2b65dcf8e6">   

2.6. add a spring web   
I'm just going to create a sample spring rest application.   
<img width="607" alt="New Spring Starter Project Dependencies" src="https://github.com/user-attachments/assets/9f370768-afd9-4c5d-b48f-b1eda4396cec">   

2.7. change the spring boot version in pom.xml.   
To replicate a logging issue, I set the verion to v1 purposefully.   
<img width="781" alt="Pasted Graphic 2" src="https://github.com/user-attachments/assets/ee2b0535-f3e2-4a26-9b69-38d82d7aa3a5">   
<img width="553" alt="«project build sourceEncodingUTF-8project build sourceEncodin" src="https://github.com/user-attachments/assets/e21a1ca2-210a-45bd-acf1-c7de80f46033">    
Note: You might need to update several packages due to incompatibility across spring boot versions.   
<img width="757" alt="Pasted Graphic 4" src="https://github.com/user-attachments/assets/865bc848-19d1-43c1-8f2d-d8e922aae19d">   
<img width="755" alt="protected SpringApplicationBuilder configure(SpringApplicationBuilder application) ‹" src="https://github.com/user-attachments/assets/0cf72028-3dce-4cff-a1f2-3ed140b9eae8">   
<img width="555" alt="4 import org springframework boot test context SpringBootTest;" src="https://github.com/user-attachments/assets/88dde400-ab90-4464-8dab-66da072f070e">   
<img width="555" alt="E DemoApplicationTests java X" src="https://github.com/user-attachments/assets/4c861b25-c001-48c3-93c2-bd9b14ff7277">   

2.8. Add JDK (NOT JRE) for maven compilation.   
I used IBM Semeru v11. Please note that the Java version for the application is specified in pom.xml.   
<img width="601" alt="JRE Definition" src="https://github.com/user-attachments/assets/6a0580e5-afe7-4406-8a7d-9a80cdfb018f">   
<img width="849" alt="Code Coverage" src="https://github.com/user-attachments/assets/2d660244-89cb-4ed9-8ca9-43779d6ff568">   
<img width="716" alt="type filter text" src="https://github.com/user-attachments/assets/1e511873-93ba-412c-ab7f-7a5822a8efda">   

2.9. build the project.    
<img width="670" alt="• Run As" src="https://github.com/user-attachments/assets/93a32455-ca93-4bee-97a3-7f78611c413f">   
the war is generated under target directory.   
<img width="712" alt="Downloaded fron" src="https://github.com/user-attachments/assets/d0ffb2f3-5048-4b58-adb7-6f457c694c36">


# 3. deploy the application on tWAS.   
Just after the initialization of spring application, WebSphere specific message style is also changed. This is the evidence that spring boot intefers the WAS logging.   
<img width="1286" alt="Pasted Graphic 17" src="https://github.com/user-attachments/assets/3b8852ba-40d1-4619-8c61-eb0e093d00a7">


# 4. replicate the logging stop issue.   
In application.properties, if we set the root logger's log level to OFF, SystemOut stops while the application is being started.   
<img width="1031" alt="Pasted Graphic 18" src="https://github.com/user-attachments/assets/e5d82c0d-e258-44da-8ef0-0490e10d1f12">   
```
logging.level.root=OFF
```

```
[root@c89289v1 bin]# tail -n 10 ../logs/server1/SystemOut.log
[11/12/24 21:43:11:179 PST] 0000006e webapp        I com.ibm.ws.webcontainer.webapp.WebApp log SRVE0292I: Servlet Message - [demo-0_0_1-SNAPSHOT_war#demo-0.0.1-SNAPSHOT.war]:.2 Spring WebApplicationInitializers detected on classpath
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O 
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O   .   ____          _            __ _ _
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O   '  |____| .__|_| |_|_| |_\__, | / / / /
[11/12/24 21:43:12:971 PST] 0000006e SystemOut     O  =========|_|==============|___/=/_/_/_/
[11/12/24 21:43:12:978 PST] 0000006e SystemOut     O  :: Spring Boot ::       (v1.5.19.RELEASE)
[11/12/24 21:43:12:978 PST] 0000006e SystemOut     O 
[root@c89289v1 bin]# 
```

While the application is starting, SLF4JBridgeHandler.removeHandlersForRootLogger is called and it overrides the WebSphere's default JUL settings.   

```
       mt.3        > org/slf4j/bridge/SLF4JBridgeHandler.removeHandlersForRootLogger()V bytecode static method
j9trc_aux.0        - jstacktrace:
j9trc_aux.1        - [1] org.slf4j.bridge.SLF4JBridgeHandler.removeHandlersForRootLogger (SLF4JBridgeHandler.java:167)
j9trc_aux.1        - [2] org.springframework.boot.logging.Slf4JLoggingSystem.removeJdkLoggingBridgeHandler (Slf4JLoggingSystem.java:78)
j9trc_aux.1        - [3] org.springframework.boot.logging.Slf4JLoggingSystem.configureJdkLoggingBridgeHandler (Slf4JLoggingSystem.java:61)
j9trc_aux.1        - [4] org.springframework.boot.logging.Slf4JLoggingSystem.beforeInitialize (Slf4JLoggingSystem.java:41)
j9trc_aux.1        - [5] org.springframework.boot.logging.logback.LogbackLoggingSystem.beforeInitialize (LogbackLoggingSystem.java:102)
j9trc_aux.1        - [6] org.springframework.boot.logging.LoggingApplicationListener.onApplicationStartingEvent (LoggingApplicationListener.java:231)
j9trc_aux.1        - [7] org.springframework.boot.logging.LoggingApplicationListener.onApplicationEvent (LoggingApplicationListener.java:210)
j9trc_aux.1        - [8] org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener (SimpleApplicationEventMulticaster.java:172)
j9trc_aux.1        - [9] org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener (SimpleApplicationEventMulticaster.java:165)
j9trc_aux.1        - [10] org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent (SimpleApplicationEventMulticaster.java:139)
j9trc_aux.1        - [11] org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent (SimpleApplicationEventMulticaster.java:122)
j9trc_aux.1        - [12] org.springframework.boot.context.event.EventPublishingRunListener.starting (EventPublishingRunListener.java:69)
j9trc_aux.1        - [13] org.springframework.boot.SpringApplicationRunListeners.starting (SpringApplicationRunListeners.java:48)
j9trc_aux.1        - [14] org.springframework.boot.SpringApplication.run (SpringApplication.java:292)
j9trc_aux.1        - [15] org.springframework.boot.web.support.SpringBootServletInitializer.run (SpringBootServletInitializer.java:156)
j9trc_aux.1        - [16] org.springframework.boot.web.support.SpringBootServletInitializer.createRootApplicationContext (SpringBootServletInitializer.java:136)
j9trc_aux.1        - [17] org.springframework.boot.web.support.SpringBootServletInitializer.onStartup (SpringBootServletInitializer.java:91)
j9trc_aux.1        - [18] org.springframework.web.SpringServletContainerInitializer.onStartup (SpringServletContainerInitializer.java:169)
j9trc_aux.1        - [19] com.ibm.ws.webcontainer.webapp.WebAppImpl.initializeServletContainerInitializers (WebAppImpl.java:653)
j9trc_aux.1        - [20] com.ibm.ws.webcontainer.webapp.WebAppImpl.initialize (WebAppImpl.java:437)
```

As you see in the stack below, spring logging component relays on logback, which set the loglevel of JUL.   
```
       mt.1        > java/util/logging/Logger.setLevel(Ljava/util/logging/Level;)V compiled method, this = 0x7fd977da8
j9trc_aux.0        - jstacktrace:
j9trc_aux.1        - [1] java.util.logging.Logger.setLevel (Logger.java:1700) (Compiled Code)
j9trc_aux.1        - [2] com.ibm.ws.logging.WsLogger.setLevel (WsLogger.java:335) (Compiled Code)
j9trc_aux.1        - [3] com.ibm.ws.logging.WsLogger$2.run (WsLogger.java:1091) (Compiled Code)
j9trc_aux.1        - [4] com.ibm.ws.security.util.AccessController.doPrivileged (AccessController.java:63) (Compiled Code)
j9trc_aux.1        - [5] com.ibm.ws.logging.WsLogger.traceStateChanged (WsLogger.java:1088)
j9trc_aux.1        - [6] com.ibm.ws.logging.WsLogger.registerTraceComponent (WsLogger.java:1077) (Compiled Code)
j9trc_aux.1        - [7] com.ibm.ws.logging.WsLogger.<init> (WsLogger.java:242)
j9trc_aux.1        - [8] com.ibm.ws.logging.WsLoggerFactoryImpl.createWsLogger (WsLoggerFactoryImpl.java:79)
j9trc_aux.1        - [9] com.ibm.ws.bootstrap.RASDelegator.createWsLogger (RASDelegator.java:74)
j9trc_aux.1        - [10] com.ibm.ws.bootstrap.WsLogManager.getLogger (WsLogManager.java:262) (Compiled Code)
j9trc_aux.1        - [11] java.util.logging.LogManager.demandLogger (LogManager.java:562) (Compiled Code)
j9trc_aux.1        - [12] java.util.logging.Logger.demandLogger (Logger.java:466)
j9trc_aux.1        - [13] java.util.logging.Logger.getLogger (Logger.java:513)
j9trc_aux.1        - [14] ch.qos.logback.classic.jul.JULHelper.asJULLogger (JULHelper.java:66)
j9trc_aux.1        - [15] ch.qos.logback.classic.jul.JULHelper.asJULLogger (JULHelper.java:70)
j9trc_aux.1        - [16] ch.qos.logback.classic.jul.LevelChangePropagator.propagate (LevelChangePropagator.java:61)
j9trc_aux.1        - [17] ch.qos.logback.classic.jul.LevelChangePropagator.onLevelChange (LevelChangePropagator.java:56)
j9trc_aux.1        - [18] ch.qos.logback.classic.LoggerContext.fireOnLevelChange (LoggerContext.java:318)
j9trc_aux.1        - [19] ch.qos.logback.classic.Logger.setLevel (Logger.java:173)
j9trc_aux.1        - [20] org.springframework.boot.logging.logback.LogbackConfigurator.logger (LogbackConfigurator.java:89)
j9trc_aux.1        - [21] org.springframework.boot.logging.logback.LogbackConfigurator.logger (LogbackConfigurator.java:82)
j9trc_aux.1        - [22] org.springframework.boot.logging.logback.LogbackConfigurator.logger (LogbackConfigurator.java:78)
j9trc_aux.1        - [23] org.springframework.boot.logging.logback.DefaultLogbackConfiguration.base (DefaultLogbackConfiguration.java:102)
j9trc_aux.1        - [24] org.springframework.boot.logging.logback.DefaultLogbackConfiguration.apply (DefaultLogbackConfiguration.java:81)
j9trc_aux.1        - [25] org.springframework.boot.logging.logback.LogbackLoggingSystem.loadDefaults (LogbackLoggingSystem.java:134)
j9trc_aux.1        - [26] org.springframework.boot.logging.AbstractLoggingSystem.initializeWithConventions (AbstractLoggingSystem.java:84)
j9trc_aux.1        - [27] org.springframework.boot.logging.AbstractLoggingSystem.initialize (AbstractLoggingSystem.java:59)
j9trc_aux.1        - [28] org.springframework.boot.logging.logback.LogbackLoggingSystem.initialize (LogbackLoggingSystem.java:114)
j9trc_aux.1        - [29] org.springframework.boot.logging.LoggingApplicationListener.initializeSystem (LoggingApplicationListener.java:304)
j9trc_aux.1        - [30] org.springframework.boot.logging.LoggingApplicationListener.initialize (LoggingApplicationListener.java:277)
j9trc_aux.1        - [31] org.springframework.boot.logging.LoggingApplicationListener.onApplicationEnvironmentPreparedEvent (LoggingApplicationListener.java:240)
j9trc_aux.1        - [32] org.springframework.boot.logging.LoggingApplicationListener.onApplicationEvent (LoggingApplicationListener.java:213)
j9trc_aux.1        - [33] org.springframework.context.event.SimpleApplicationEventMulticaster.doInvokeListener (SimpleApplicationEventMulticaster.java:172)
j9trc_aux.1        - [34] org.springframework.context.event.SimpleApplicationEventMulticaster.invokeListener (SimpleApplicationEventMulticaster.java:165)
j9trc_aux.1        - [35] org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent (SimpleApplicationEventMulticaster.java:139)
j9trc_aux.1        - [36] org.springframework.context.event.SimpleApplicationEventMulticaster.multicastEvent (SimpleApplicationEventMulticaster.java:122)
j9trc_aux.1        - [37] org.springframework.boot.context.event.EventPublishingRunListener.environmentPrepared (EventPublishingRunListener.java:74)
j9trc_aux.1        - [38] org.springframework.boot.SpringApplicationRunListeners.environmentPrepared (SpringApplicationRunListeners.java:54)
j9trc_aux.1        - [39] org.springframework.boot.SpringApplication.prepareEnvironment (SpringApplication.java:325)
j9trc_aux.1        - [40] org.springframework.boot.SpringApplication.run (SpringApplication.java:296)
j9trc_aux.1        - [41] org.springframework.boot.web.support.SpringBootServletInitializer.run (SpringBootServletInitializer.java:156)
j9trc_aux.1        - [42] org.springframework.boot.web.support.SpringBootServletInitializer.createRootApplicationContext (SpringBootServletInitializer.java:136)
j9trc_aux.1        - [43] org.springframework.boot.web.support.SpringBootServletInitializer.onStartup (SpringBootServletInitializer.java:91)
j9trc_aux.1        - [44] org.springframework.web.SpringServletContainerInitializer.onStartup (SpringServletContainerInitializer.java:169)
j9trc_aux.1        - [45] com.ibm.ws.webcontainer.webapp.WebAppImpl.initializeServletContainerInitializers (WebAppImpl.java:653)
j9trc_aux.1        - [46] com.ibm.ws.webcontainer.webapp.WebAppImpl.initialize (WebAppImpl.java:437)
```

This is the typical mechanism by which a Spring application interferes with WebSphere logging.　　　

As another example, we can also set the root logger level in logback-spring.xml like below. Because we don't know how the application developer has configured the logging setting, you may ask the application developer to check if the root logger logging level is set to OFF or not.   

<img width="975" alt="Pasted Graphic 20" src="https://github.com/user-attachments/assets/222aac68-3372-4833-8e78-a480ac3550ea">    

Note: Even if we disable the root logger by setting its level to OFF, we can configure application specific loggers like below. 

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE logback>
<configuration>
 
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <target>System.out</target>
        <encoder>
            <charset>UTF-8</charset>
            <pattern>%d{yyyy/MM/dd HH:mm:ss} %-5level [%thread] - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>logs/app.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
			<fileNamePattern>logs/app.%d{yyyy-MM-dd}.log.tar.gz</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd'T'HH:mm:ss'Z'} - %m%n</pattern>
        </encoder>
    </appender>
    
    <root level="OFF">
        <appender-ref ref="STDOUT" />
    </root>

	<logger name="mylogger" additivity="false">
		<level value="INFO" />
        <appender-ref ref="FILE" />
    </logger>
</configuration>
```
<img width="825" alt="image" src="https://github.com/user-attachments/assets/3374f7b7-671a-4847-8ae8-27e11b605b5f">   


# 5. How to resolve/avoid this.   

If the application contains jul-to-slf4j-*.*.*.jar, renaming the jar file will be able to avoid the issue. The application specific logging in the example above was not affected with this approach.      
```
[root@c89289v1 lib]# mv jul-to-slf4j-1.7.25.jar jul-to-slf4j-1.7.25.jar.bk
[root@c89289v1 lib]# 
```

```
/opt/IBM/WebSphere/AppServer90ND/profiles/AppSrv01/installedApps/c89289v1Node01Cell/demo-0_0_1-SNAPSHOT_war.ear/demo-0.0.1-SNAPSHOT.war
[root@c89289v1 demo-0.0.1-SNAPSHOT.war]# tree
.
|-- META-INF
|   |-- MANIFEST.MF
|   `-- maven
|       `-- com.example
|           `-- demo
|               |-- pom.properties
|               `-- pom.xml
`-- WEB-INF
    |-- classes
    |   |-- application.properties
    |   |-- com
    |   |   `-- example
    |   |       `-- demo
    |   |           |-- DemoApplication.class
    |   |           |-- demos
    |   |           |   `-- web
    |   |           |       |-- BasicController.class
    |   |           |       |-- PathVariableController.class
    |   |           |       `-- User.class
    |   |           `-- ServletInitializer.class
    |   |-- logback-spring.xml
    |   `-- static
    |       `-- index.html
    |-- ibm-metadata.xml
    |-- ibm-web-bnd.xml
    |-- ibm-web-ext.xml
    |-- lib
    |   |-- classmate-1.3.4.jar
    |   |-- hibernate-validator-5.3.6.Final.jar
    |   |-- jackson-annotations-2.8.0.jar
    |   |-- jackson-core-2.8.11.jar
    |   |-- jackson-databind-2.8.11.3.jar
    |   |-- jboss-logging-3.3.2.Final.jar
    |   |-- jcl-over-slf4j-1.7.25.jar
    |   |-- jul-to-slf4j-1.7.25.jar
    |   |-- log4j-over-slf4j-1.7.25.jar
    |   |-- logback-classic-1.1.11.jar
    |   |-- logback-core-1.1.11.jar
    |   |-- slf4j-api-1.7.25.jar
    |   |-- snakeyaml-1.17.jar
    |   |-- spring-aop-4.3.22.RELEASE.jar
    |   |-- spring-beans-4.3.22.RELEASE.jar
    |   |-- spring-boot-1.5.19.RELEASE.jar
    |   |-- spring-boot-autoconfigure-1.5.19.RELEASE.jar
    |   |-- spring-boot-starter-1.5.19.RELEASE.jar
    |   |-- spring-boot-starter-logging-1.5.19.RELEASE.jar
    |   |-- spring-boot-starter-web-1.5.19.RELEASE.jar
    |   |-- spring-context-4.3.22.RELEASE.jar
    |   |-- spring-core-4.3.22.RELEASE.jar
    |   |-- spring-expression-4.3.22.RELEASE.jar
    |   |-- spring-web-4.3.22.RELEASE.jar
    |   |-- spring-webmvc-4.3.22.RELEASE.jar
    |   `-- validation-api-1.1.0.Final.jar
    `-- web_merged.xml

13 directories, 41 files
[root@c89289v1 demo-0.0.1-SNAPSHOT.war]# 
```

The same issue had been reported acorss several JavaEE runtimes and the spring project has fixed this as a defect, which fixed code is available from v2.   
```
Slf4JLoggingSystem removes JUL root handlers via SLF4JBridgeHandler #8933   
https://github.com/spring-projects/spring-boot/issues/8933   
```
Therefore, if the customer can migrate their spring boot v1 app to v2, this can be another solution. However, as we saw above, the customer's application might need to be updated.   
Note: v1 reached EOL back in 2019 (https://spring.io/blog/2018/07/30/spring-boot-1-x-eol-aug-1st-2019)  


And if the application's logging setting is externalized like application.properties or logback-spring.xml, we may update the configuraion file of the expanded binary directly.   
```
[root@c89289v1 classes]# pwd
/opt/IBM/WebSphere/AppServer90ND/profiles/AppSrv01/installedApps/c89289v1Node01Cell/demo-0_0_1-SNAPSHOT_war.ear/demo-0.0.1-SNAPSHOT.war/WEB-INF/classes
[root@c89289v1 classes]# cat application.properties 
logging.level.root=INFO
server.port=8080
```
