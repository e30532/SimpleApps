<!-- <%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
 -->
 <%@taglib prefix="my" uri="/WEB-INF/tlds/mytag" %>
 <%@taglib prefix="html" uri="/tags/struts-html" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>
<html:form>
	<my:MyTag form="helloForm" property="CIF_DOB"/>
	<html:submit property="submit" value="submit" />
</html:form>
</body>
</html>