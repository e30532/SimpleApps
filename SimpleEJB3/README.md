<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/291aa935-43c8-43ea-ad00-406d1dd0f049"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/905d7375-27e8-445d-a378-4eaead589ccf"><br>
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/b8683fee-de66-4e9b-9155-d3cab61cb68f"><br>   
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a89c899d-c41f-4ead-b7b9-eb73fd45a2bd"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/0b9024aa-1d33-4bfc-ae23-5dc6f67b7d48"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a024749a-00d9-4c57-bba2-de20315136c2"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a2f160a5-e410-470a-8421-6c153bcb8838"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/213340d8-cf83-44d9-b6d1-425a4d1d8618"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/de7ab587-6d5a-4531-a905-479cc056b36d"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/2bb593e2-3486-457f-a89a-5d1518ae3559"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/01fdc2bf-31c2-4aa6-9f5b-15f808157411"><br>  
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/34431fee-3bec-478a-84f5-3f863dc594f8"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/5f661610-5c94-406e-88ff-75095c4a899e"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/e09f7f3b-8c42-48b7-a6db-ec20a897d2e6"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/ce60e07f-9dd9-4c5e-90b1-c90d1d6f1cd8"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/113c0366-6b4c-47dc-bd6b-dce9950ba873"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/00e3e8db-c040-47dd-a7e5-77be4e5fa6fe"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/a9ae1ed7-4c87-4400-935b-9012677a1d74"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/c672b376-7b77-452c-b1c8-ab0d7b57be39"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/4d8e63ef-6a09-4cdf-b783-eacef50410f6"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/72623ec6-47b3-4e37-833f-82f8fca0df74"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/897b16a5-6a66-4655-adf8-6b7d9d50fd39"><br>    

```
[root@c81981v1 ~]# curl http://localhost:9080/SimpleEJB3ClientWeb/SimpleServlet
[root@c81981v1 ~]# tail -n 2 /opt/IBM/WebSphere/AppServer90ND/profiles/AppSrv01/logs/server1/SystemOut.log
[4/3/24 20:01:25:850 PDT] 000000a3 ServletWrappe I com.ibm.ws.webcontainer.servlet.ServletWrapper init SRVE0242I: [SimpleEJB3Client] [/SimpleEJB3ClientWeb] [simple.ejb3.client.SimpleServlet]: Initialization successful.
[4/3/24 20:01:25:851 PDT] 000000a3 SystemOut     O Hello STSC
[root@c81981v1 ~]# 
```

<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/9104572b-d9ad-4de2-8638-c067a232ffe1"><br>    
<img width="300" alt="image" src="https://github.com/e30532/SimpleApps/assets/22098113/4dcf331b-1ab5-4ea8-b389-92038b349e26"><br>    

```
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
</server>

[root@c81981v1 ~]# curl http://localhost:9080/SimpleEJB3ClientWeb/SimpleServlet
[root@c81981v1 ~]# tail -n 2 /opt/IBM/wlp24.0.0.2/wlp/usr/servers/defaultServer/logs/messages.log 
[4/3/24 20:09:31:064 PDT] 0000006f com.ibm.tx.jta.impl.RecoveryManager                          I WTRN0135I: Transaction service recovering no transactions.
[4/3/24 20:09:31:115 PDT] 00000036 SystemOut                                                    O Hello STSC
[root@c81981v1 ~]#
```

