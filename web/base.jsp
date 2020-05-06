<%--
  Created by IntelliJ IDEA.
  User: renat
  Date: 04.03.2020
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/my.css" type="text/css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="col-lg-4 well">
            <p>Hello, ${name}</p>
            <img src="${avatar}">
        </div>
        <div class="col-lg-4 well">
            <a href="${pageContext.request.contextPath}/logout" class="btn-danger">Log Out</a>
        </div>
        <div class="col-lg-4 well">
            <a href="${pageContext.request.contextPath}/update" class="btn-info">Update Avatar</a>
        </div>
    </div>
</body>
</html>
