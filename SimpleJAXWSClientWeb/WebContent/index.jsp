<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/SimpleJAXWSClientWeb/SimpleServlet">
        <p/><input type="text" name="endpointurl" value="http://localhost:9080/SimpleJAXWSWeb/HelloService" size="50">
        <p/><input type="text" name="name" value="IBM">
        <p/><input type="submit">
</form>
</body>
</html>