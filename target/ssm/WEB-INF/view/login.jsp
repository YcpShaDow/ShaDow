<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ycp
  Date: 2018/9/4
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login.action" method="post">
        <input type="text" name="username">
        <input type="password" name="password">
        <input type="submit" value="登录">
    </form>
</body>
</html>
