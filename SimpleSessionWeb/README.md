
## Session DB setting for liberty.

<?xml version="1.0" encoding="UTF-8"?>
<server description="new server">

    <!-- Enable features -->
    <featureManager>
        <feature>jsp-2.3</feature>
        <feature>sessionDatabase-1.0</feature>
        <feature>jdbc-4.0</feature>
    </featureManager>

    <!-- To access this server from a remote client add a host attribute to the following element, e.g. host="*" -->
    <httpEndpoint id="defaultHttpEndpoint"
                  host="*"
                  httpPort="9080"
                  httpsPort="9443" />
                  
    <!-- Automatically expand WAR files and EAR files -->
    <applicationManager autoExpand="true"/>

<httpSessionDatabase id="SessionDB" dataSourceRef="DefaultDataSource" writeFrequency="END_OF_SERVLET_SERVICE"/>
<library id="DB2JCC4Lib">
    <fileset dir="/opt/ibm/db2/V11.5/java/" includes="db2jcc4.jar"/>
</library>
<dataSource id="DefaultDataSource" jndiName="jdbc/SAMPLE">
    <connectionManager agedTimeout="0" connectionTimeout="10s" maxPoolSize="20" minPoolSize="5"/>
    <jdbcDriver libraryRef="DB2JCC4Lib"/>
    <properties.db2.jcc databaseName="WASDB" password="****" portNumber="50000" serverName="fyre1" user="db2inst1"/>
</dataSource>

<logging traceSpecification="com.ibm.ws.webcontainer*=all:com.ibm.wsspi.webcontainer*=all:HTTPChannel=all:GenericBNF=all:HTTPDispatcher=all:com.ibm.ws.session.*=all" traceFileName="trace.log" maxFileSize="20" maxFiles="10" traceFormat="BASIC" />

</server>
```
