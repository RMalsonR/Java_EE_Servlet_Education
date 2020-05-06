<%--
  Created by IntelliJ IDEA.
  User: renat
  Date: 06.05.2020
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<div class="container">
    <div class="col-lg-4 text-center flexBox">
        <p class="info">Enter your new avatar</p>
        <form class="form-group well" id="update-form" method="post" enctype="multipart/form-data">
            <label for="avatar" class="lbl">avatar</label>
            <input type="file" class="form-control mtb-min" id="avatar" name="avatar">
            <input type="submit" value="Update" class="btn-info" style="margin-top: 2rem !important;">
        </form>
    </div>
</div>
</body>
</html>
