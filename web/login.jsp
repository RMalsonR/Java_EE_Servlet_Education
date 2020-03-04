<%--
  Created by IntelliJ IDEA.
  User: renat
  Date: 04.03.2020
  Time: 1:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link href="/css/bootstrap.css" type="text/css" rel="stylesheet">
    <link href="/css/my.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="col-lg-4">
        <p class="info">Enter your login and password</p>
        <form class="form-group well" id="login-form" method="post">
            <label for="login" class="lbl">Login</label>
            <input type="text" class="form-control mtb-min" id="login" name="login" autofocus>
            <label for="password" class="lbl">Password</label>
            <input type="password" class="form-control mtb-min" id="password" name="password">
            <input type="submit" value="sign in" class="form-control m-btn mtb-min">
        </form>
        <% Boolean error = (Boolean) request.getAttribute("error"); %>
        <% if(error){ %>
        <div id="error" class="well">
            <p>Login or password is incorrect. Please check it and
            try again!</p>
        </div>
        <% } %>
</div>
</div>
</body>
</html>
