<%-- 
    Document   : error
    Created on : Jun 1, 2023, 10:35:39 AM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1 style="color: red">${requestScope.ERROR_MSG}</h1>
        <a href="login.html">Click here to login again!</a>
    </body>
</html>
