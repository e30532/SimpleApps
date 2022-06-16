<%@page language="java"
        contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>SimpleMVC</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
        <form action="/SimpleMVCWeb/SimpleServlet">
                <input type="submit" value="Get"><input type="text" name="message" value="STSC GET">
        </form>
        <form action="/SimpleMVCWeb/SimpleServlet" method="post">
                <input type="submit" value="Post"><input type="text" name="message" value="STSC POST">
        </form>
</body>
</html>