<%-- 
    Document   : createUserError
    Created on : Jun 21, 2023, 10:28:27 PM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@page import="longvu.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup Error</title>
    </head>
    <body>
        <h1>Signup Error</h1>
        <h2 style="color: red">${requestScope.USER_ERROR.usernameError}</h2>
        <h2 style="color: red">${requestScope.USER_ERROR.fullNameError}</h2>
        <h2 style="color: red">${requestScope.USER_ERROR.passwordError}</h2>
        <h2 style="color: red">${requestScope.USER_ERROR.confirmError}</h2>
        <h2 style="color: red">${requestScope.USER_ERROR.messageError}</h2>
        <a href="createUser.jsp">Click here to signup again!</a>
    </body>
</html>
