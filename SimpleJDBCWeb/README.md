This application expects the table below.  
```
db2 => CREATE TABLE "DB2INST1"."EMP"  ("ID" INTEGER NOT NULL , "NAME" CHAR(5 OCTETS) ) IN "USERSPACE1" ORGANIZE BY ROW; 
DB20000I  The SQL command completed successfully.
db2 => ALTER TABLE "DB2INST1"."EMP" ADD PRIMARY KEY("ID");
```

http://xxxx:9080/SimpleJDBCWeb
