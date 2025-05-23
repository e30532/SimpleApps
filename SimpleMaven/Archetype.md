## This document describes a quite simple step-by-step instruction how we create own maven archetype (=template) and use it.


1. create a parent maven project.   
```
mvn archetype:generate -DgroupId=com.simple -DartifactId=myParent -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
The reason why we create this as root project is because we can build an application by a single maven command.  

2. create a WAR maven project.
```
mvn archetype:generate -DgroupId=com.simple -DartifactId=myWAR -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

3. create a EAR maven project.
```
mvn archetype:generate -DgroupId=com.simple -DartifactId=myEAR -DarchetypeArtifactId=maven-archetype-j2ee-simple -DinteractiveMode=false
```

4. move WAR and EAR under the parent directory.
```
mv myWAR myParent/
mv myEAR myParent/
```

5. update pom files.
```
vi myParent/pom.xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.simple</groupId>
  <artifactId>myParent</artifactId>
  <packaging>pom</packaging>
  <version>1.0-SNAPSHOT</version>
  <name>myParent</name>
  <modules>
    <module>myWAR</module>
    <module>myEAR</module>
  </modules>
</project>


vi myParent/myWAR/pom.xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.simple</groupId>
  <artifactId>myWAR</artifactId>
  <packaging>war</packaging>
  <dependencies>
    <!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.4.0</version>
        <configuration>
          <failOnMissingWebXml>false</failOnMissingWebXml>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <parent>
    <groupId>com.simple</groupId>
    <artifactId>myParent</artifactId>
    <version>1.0-SNAPSHOT</version>
  </parent>
</project>


vi myParent/myEAR/pom.xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.simple</groupId>
  <version>1.0-SNAPSHOT</version>
  <artifactId>myEAR</artifactId>
  <packaging>ear</packaging>
  <name>myEAR</name>
  <dependencies>
    <dependency>
      <groupId>com.simple</groupId>
      <artifactId>myWAR</artifactId>
      <type>war</type>
      <version>1.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
  <build>
    <finalName>myEAR</finalName>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-ear-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <defaultLibBundleDir>lib</defaultLibBundleDir>
          <modules>
            <webModule>
              <groupId>com.simple</groupId>
              <artifactId>myWAR</artifactId>
              <contextRoot>/myWAR</contextRoot>
              <bundleFileName>myWAR.war</bundleFileName>
            </webModule>
          </modules>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <parent>
    <groupId>com.simple</groupId>
    <artifactId>myParent</artifactId>
    <version>1.0-SNAPSHOT</version>
  </parent>
</project>
```

6. delete unnecessary files.
```
cd myParent
rm -rf myEAR/ejbs myEAR/ejbs/ myEAR/primary-source/ myEAR/projects/ myEAR/servlets/ myEAR/src/ myWAR/src/main/webapp/index.jsp myWAR/src/main/webapp/WEB-INF/web.xml src/main/java/com src/test/java/com
```

7. create a servlet, web.xml and application.xml
```
mkdir -p myWAR/src/main/java/com/simple
vi myWAR/src/main/java/com/simple/SimpleServlet.java

package com.simple;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
   	private static final long serialVersionUID = 1L;
       
    	/**
     	* @see HttpServlet#HttpServlet()
     	*/
    	public SimpleServlet() {
        	super();
        	// TODO Auto-generated constructor stub
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}



vi myWAR/src/main/webapp/WEB-INF/web.xml

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">
  <display-name>myWAR</display-name>
</web-app>


mkdir -p myEAR/META-INF
vi myEAR/META-INF/application.xml

<?xml version="1.0" encoding="UTF-8"?>
<application xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application_6.xsd" id="Application_ID" version="6">
  <display-name>myEAR</display-name>
  <module>
    <web>
      <web-uri>myWAR.war</web-uri>
      <context-root>myWAR</context-root>
    </web>
  </module>
</application>

```

8. If there is no no .m2/settings.xml, create it.
```
mkdir -p ~/.m2

cat ~/.m2/settings.xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                              https://maven.apache.org/xsd/settings-1.0.0.xsd">
</settings>
```

9. set Java_HOME
```
export JAVA_HOME=/opt/IBM/WebSphere/AppServer90ND/java/8.0/
```

10. build the project
```
mvn clean install
```

11. create a archetype from the project. 
```
mvn archetype:create-from-project
```

12. adjust the archetype. Several points needs to be replaced with variables. 
```
cd target/generated-sources/archetype/
vi src/main/resources/archetype-resources/myEAR/pom.xml


<?xml version="1.0" encoding="UTF-8"?>
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>${groupId}</groupId>
  <version>${version}</version>
  <artifactId>${artifactId}</artifactId>
  <packaging>ear</packaging>
  <name>${artifactId}</name>
  <dependencies>
    <dependency>
      <groupId>${groupId}</groupId>     <==================HERE
      <artifactId>myWAR</artifactId>
      <type>war</type>
      <version>1.0-SNAPSHOT</version>
    </dependency>
  </dependencies>
  <build>
    <finalName>${earName}</finalName>     <==================HERE
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-ear-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <defaultLibBundleDir>lib</defaultLibBundleDir>
          <modules>
            <webModule>
              <groupId>${groupId}</groupId>
              <artifactId>myWAR</artifactId>
              <contextRoot>/${warName}</contextRoot>     <==================HERE
              <bundleFileName>${warName}.war</bundleFileName>     <==================HERE
            </webModule>
          </modules>
        </configuration>
      </plugin>
    </plugins>
  </build>
  <parent>
    <groupId>${groupId}</groupId>
    <artifactId>${rootArtifactId}</artifactId>
    <version>${version}</version>
  </parent>
</project>



vi src/main/resources/META-INF/maven/archetype-metadata.xml 

<?xml version="1.0" encoding="UTF-8"?>
<archetype-descriptor xsi:schemaLocation="https://maven.apache.org/plugins/maven-archetype-plugin/archetype-descriptor/1.2.0 https://maven.apache.org/xsd/archetype-descriptor-1.2.0.xsd" name="myParent"
    xmlns="https://maven.apache.org/plugins/maven-archetype-plugin/archetype-descriptor/1.2.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <requiredProperties>
    <requiredProperty key="earName">
      <defaultValue>SimpleMVC</defaultValue>
    </requiredProperty>
    <requiredProperty key="warName">
      <defaultValue>SimpleMVCWeb</defaultValue>
    </requiredProperty>
  </requiredProperties>
  <modules>
    <module id="myWAR" dir="myWAR" name="myWAR">
:
:
:


vi src/main/resources/archetype-resources/myEAR/META-INF/application.xml

<?xml version="1.0" encoding="UTF-8"?>
<application xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/application_6.xsd" id="Application_ID" version="6">
  <display-name>${earName}</display-name>
  <module>
    <web>
      <web-uri>${warName}.war</web-uri>
      <context-root>${warName}</context-root>
    </web>
  </module>
</application>

```

13. build the archetype.

```
pwd
/root/myParent/target/generated-sources/archetype

mvn clean install
```

14. create a new project by using the archetype.
```
mvn archetype:generate -DarchetypeCatalog=local -DarchetypeGroupId=com.simple -DarchetypeArtifactId=myParent-archetype -DarchetypeVersion=1.0-SNAPSHOT -DgroupId=simple.mvc -DartifactId=SimpleMVC -DinteractiveMode=false -DearName=SimpleMVC -DwarName=SimpleMVCWeb
```

15. build the project
```
cd SimpleMVC
mvn clean install
```

If the build completes successfully, you can deploy it to WAS/Liberty.

```
/opt/IBM/wlp25.0.0.2/wlp/bin/server start test
cp /root/SimpleMVC/myEAR/target/SimpleMVC.ear /opt/IBM/wlp25.0.0.2/wlp/usr/servers/test/dropins/
curl -I http://localhost:9080/SimpleMVCWeb/SimpleServlet
HTTP/1.1 200 OK
X-Powered-By: Servlet/3.1
Content-Length: 0
Content-Language: en-US
Date: Fri, 23 May 2025 10:21:24 GMT
```

You can also confirm the variables are replaced
cat /opt/IBM/wlp25.0.0.2/wlp/usr/servers/test/apps/expanded/SimpleMVC.ear/META-INF/application.xml 
```
<?xml version="1.0" encoding="UTF-8"?>
<application xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/application_7.xsd" version="7">
  <display-name>SimpleMVC</display-name>
  <module>
    <web>
      <web-uri>SimpleMVCWeb.war</web-uri>
      <context-root>/SimpleMVCWeb</context-root>
    </web>
  </module>
  <library-directory>lib</library-directory>
</application>
```

## Tips
You can also publish the archetype to as a git repository.

```
pwd
/root/myParent/target/generated-sources/archetype

git init

gh auth login

gh repo create myEAR-archetype --public --confirm

git remote add origin git@github.ibm.com:E30532/myEAR-archetype.git

vi .gitignore
-------
src/test/
target/
-------

git add .

git commit -m "Initial commit"

git push -u origin master
```

## trouble shooting
If you would like to scratch everyting including cache, you can use the command below.
```
rm -rf ~/.m2/repository/com/simple/*
rm ~/.m2/repository/archetype-catalog.xml
```
