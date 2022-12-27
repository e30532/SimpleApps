This application expects the table below.  
```
db2 => CREATE TABLE "DB2INST1"."EMP"  ("ID" INTEGER NOT NULL , "NAME" CHAR(5 OCTETS) ) IN "USERSPACE1" ORGANIZE BY ROW; 
DB20000I  The SQL command completed successfully.
db2 => ALTER TABLE "DB2INST1"."EMP" ADD PRIMARY KEY("ID");
```

http://xxxx:9080/SimpleJDBCWeb

```
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>localConnector-1.0</feature>
		<feature>javaee-7.0</feature>
	</featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint host="*" httpPort="9080" httpsPort="9443" id="defaultHttpEndpoint"/>
                  
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

    <keyStore id="defaultKeyStore" password="Liberty"/>
    <basicRegistry id="basic" realm="BasicRealm"> 
        <user name="admin" password="admin"/>
    </basicRegistry>
    <administrator-role>
         <user>admin</user>
    </administrator-role>	

    <library id="DB2JCC4Lib">
        <fileset dir="/opt/ibm/db2/V11.5/java" includes="db2jcc4.jar"/>
    </library>
    <dataSource id="DefaultDataSource" jndiName="jdbc/SimpleDS">
        <connectionManager agedTimeout="0" connectionTimeout="10s" maxPoolSize="20" minPoolSize="5"/>
        <jdbcDriver libraryRef="DB2JCC4Lib"/>
        <properties.db2.jcc databaseName="WASDB" password="*****" portNumber="50000" serverName="localhost" user="db2inst1"/>
    </dataSource>
</server>
```
