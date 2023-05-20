# Background
We can't create Jakarta EE 9 or later application by using eclipse(WTP).   
https://wiki.eclipse.org/Category:Eclipse_Web_Tools_Platform_Project  
https://wiki.eclipse.org/WTP_2023-05-18  
```
New versions of key facets have yet to be added for Jakarta EE 9 and now Jakarta EE 10 and 10.1. A significant amount of additions have been made for EE9 and 10 support, but more are still needed. 
```
<img width="608" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/e8bf037b-12b5-478d-b389-21003cbbfdd4">.  

So this document describes how to create an EAR application by using Maven so that we can specify the ear version in pom.xml.   


# How to create a typical ear application by using maven.
1. create an application as we usually do.   
<img width="161" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/1c19fd50-89ce-4e55-aab7-0b16055d2e67">.  

2. convert each modules into maven project.   
<img width="632" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/e0e49100-e100-4e70-8451-f8f6a13dc480">.  
<img width="174" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a1d3335c-3407-4f62-9196-8b6b541f1677">.  

3. create a parent maven project.   
<img width="611" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/1630fc10-9409-4648-8b5e-87ab8c2903ba">.  
<img width="697" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/26edd26c-e435-4b8f-b805-7f667fe225eb">.  
<img width="704" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/356878bd-d393-460e-9a0c-e1b50e5f0fed">.  

4. add the modules to the parent project.   
<img width="1009" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/03d9c42b-b264-486f-9a63-7a2e24ef90a2">

5. add war and ejb to ear pom.xml as dependencies.   
<img width="535" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/b4e578ac-eb4d-4c1c-8f7a-e82c62fe4cbf">.  
<img width="618" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/b7123c0d-9ead-4d6b-8ebf-c52106a4d5b7">.  

6. if the war module doen't contain web.xml, set "failOnMissingWebXml" to false.    
<img width="555" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/50e45ce2-6db3-42cb-a8c3-d140117e7738">.  

7. By running "Maven install", you can build the ear including the war and the ejb.   
<img width="1047" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/3d52583b-edca-449e-ba38-be8a0b954679">.  

8. You can specify the ear version in the ear pom.xml. The following example is setting the ear version to Jakarta EE 10. Note: By default, application.xml is not included in the ear file. So I set generateApplicationXml to true. And I also update the maven plugin ear version which support Jakarta EE 10.   
<img width="556" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/42572355-ae1a-4014-a91d-608194a098d6">.  

You will see this message, but you can ignore this.   
<img width="928" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/aa1e19c1-2552-4fc5-b922-fd97d0ca1e1c">.  

<img width="1044" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/2b48d5bc-f619-472c-b026-18e662e5bbb2">.  



# Sample
The following maven project contains two maven modules (ear and war). So by just importing this project, you can customize this template a bit to meet your requirement.   

1. Download the maven project from here.  
https://ibm.box.com/s/osre1lka2m6xaji0xl4f3x30oq7wl8gs

2. extract the zip. 
```
[root@weekends1 tmp]# pwd
/root/tmp
[root@weekends1 tmp]# ls
SimpleMaven.zip
[root@weekends1 tmp]# unzip SimpleMaven.zip 
Archive:  SimpleMaven.zip
  inflating: SimpleMaven/.project    
  inflating: SimpleMaven/.settings/org.eclipse.m2e.core.prefs  
 ```

3. I used eclipse JavaEE 2023-03.    
<img width="571" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/30f4b699-b628-4ce3-b096-89dfc8c06547">.    

4. import the extracted directory as maven project.    
<img width="603" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/007361ab-a623-480d-907a-ca8fca864745">.  
<img width="630" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/33fb0719-c8fc-4b35-867c-a199f6573bc6">
<img width="636" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/16ebbd0b-9b78-4622-a207-7671fbe0ea16">

5. update the project.  
<img width="723" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/bffae26a-c1f7-467a-bd5c-efd2d65f415c">.  
<img width="609" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/d0895167-56bf-4ca1-a08f-585acfb2368e">.  
You would see the error below, but it should be ok.   
<img width="774" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/87e8efb2-ac47-4ff5-84d2-e9ee6511e945">.  

6. build the application (Maven install).   
<img width="702" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a31592e4-99ee-483c-ae36-46e3d30f27a3">. 
You will see the ear here.  
<img width="789" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/9a6ad01a-fafe-4098-8110-ffb863e7c632">.  

7. deploy the app to liberty.  
Now you can deploy the ear on liberty (I used 23.0.0.4.)
```
[root@weekends1 bin]# cat ../usr/servers/test/server.xml 
<?xml version="1.0" encoding="UTF-8"?>
<server description="new server">

    <!-- Enable features -->
    <featureManager>
    <feature>jakartaee-10.0</feature>
    </featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint id="defaultHttpEndpoint"
                  host="*"
                  httpPort="9080"
                  httpsPort="9443" />
    <keyStore id="defaultKeyStore" password="***" />
                  
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

    <application type="ear" id="SimpleMavenEar" location="/root/tmp/SimpleMaven/SimpleMavenEar/target/SimpleMavenEar-0.0.1-SNAPSHOT.ear" name="SimpleMavenEar"/>

</server>
```

8. This maven project contains a simple Spring application(not Spring Boot)
Spring version is 6.0.8. EAR version is 10. Servlet version is 6.0.

```
[root@weekends1 bin]# curl http://localhost:9080/SimpleMavenWar/config
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
</body>
</html>
[root@weekends1 bin]# 


[5/17/23, 4:00:13:032 PDT] 0000003a com.ibm.ws.app.manager.AppMessageHelper                      I CWWKZ0018I: Starting application SimpleMavenEar.
[5/17/23, 4:00:13:034 PDT] 0000003a bm.ws.app.manager.ear.internal.EARDeployedAppInfoFactoryImpl I CWWKZ0133I: The SimpleMavenEar application at the /root/tmp/SimpleMaven/SimpleMavenEar/target/SimpleMavenEar-0.0.1-SNAPSHOT.ear location is being expanded to the /opt/IBM/wlp23.0.0.4/wlp/usr/servers/test/apps/expanded/SimpleMavenEar.ear directory.
[5/17/23, 4:00:15:857 PDT] 0000003a com.ibm.ws.webcontainer.osgi.webapp.WebGroup                 I SRVE0169I: Loading Web Module: SimpleMavenWar.
[5/17/23, 4:00:15:860 PDT] 0000003a com.ibm.ws.webcontainer                                      I SRVE0250I: Web Module SimpleMavenWar has been bound to default_host.
[5/17/23, 4:00:15:860 PDT] 0000003a com.ibm.ws.http.internal.VirtualHostImpl                     A CWWKT0016I: Web application available (default_host): http://weekends1.fyre.ibm.com:9080/SimpleMavenWar/
[5/17/23, 4:00:16:368 PDT] 00000033 com.ibm.ws.webcontainer.webapp                               I SRVE0292I: Servlet Message - [SimpleMavenWar-1.0-SNAPSHOT]:.1 Spring WebApplicationInitializers detected on classpath
[5/17/23, 4:00:16:429 PDT] 00000033 com.ibm.ws.app.manager.AppMessageHelper                      A CWWKZ0001I: Application SimpleMavenEar started in 3.396 seconds.
[5/17/23, 4:00:16:454 PDT] 00000033 org.springframework.web.servlet.DispatcherServlet            I Initializing Servlet 'dispatcher'
[5/17/23, 4:00:16:505 PDT] 00000032 com.ibm.ws.kernel.feature.internal.FeatureManager            A CWWKF0012I: The server installed the following features: [appAuthentication-3.0, appAuthorization-2.1, appClientSupport-2.0, appSecurity-5.0, batch-2.1, beanValidation-3.0, cdi-4.0, concurrent-3.0, connectors-2.1, distributedMap-1.0, enterpriseBeans-4.0, enterpriseBeansHome-4.0, enterpriseBeansLite-4.0, enterpriseBeansPersistentTimer-4.0, enterpriseBeansRemote-4.0, expressionLanguage-5.0, faces-4.0, jakartaee-10.0, jdbc-4.2, jndi-1.0, jsonb-3.0, jsonp-2.1, mail-2.1, managedBeans-2.0, mdb-4.0, messaging-3.1, messagingClient-3.0, messagingSecurity-3.0, messagingServer-3.0, pages-3.1, persistence-3.1, persistenceContainer-3.1, restfulWS-3.1, restfulWSClient-3.1, servlet-6.0, ssl-1.0, transportSecurity-1.0, webProfile-10.0, websocket-2.1, xmlBinding-4.0, xmlWS-4.0].
[5/17/23, 4:00:16:506 PDT] 00000032 com.ibm.ws.kernel.feature.internal.FeatureManager            I CWWKF0008I: Feature update completed in 10.675 seconds.
[5/17/23, 4:00:16:506 PDT] 00000032 com.ibm.ws.kernel.feature.internal.FeatureManager            A CWWKF0011I: The test server is ready to run a smarter planet. The test server started in 12.263 seconds.
[5/17/23, 4:00:17:622 PDT] 00000033 org.springframework.web.servlet.DispatcherServlet            I Completed initialization in 1163 ms
[5/17/23, 4:00:17:622 PDT] 00000033 com.ibm.ws.webcontainer.servlet                              I SRVE0242I: [SimpleMavenEar] [/SimpleMavenWar] [dispatcher]: Initialization successful.
[5/17/23, 4:00:21:654 PDT] 00000039 SystemOut                                                    O Welcome home! The client locale is en_US
[5/17/23, 4:00:21:656 PDT] 00000039 SystemOut                                                    O Hello
[5/17/23, 4:00:22:508 PDT] 00000039 com.ibm.ws.webcontainer.servlet                              I SRVE0242I: [SimpleMavenEar] [/SimpleMavenWar] [/WEB-INF/views/home.jsp]: Initialization successful.
```



