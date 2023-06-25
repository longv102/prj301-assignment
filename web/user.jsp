<%-- 
    Document   : user
    Created on : Jun 21, 2023, 10:50:36 PM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="longvu.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <!--user authorization-->
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleId ne 'US'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>
        <h1>Welcome User: ${sessionScope.LOGIN_USER.fullName}</h1>
        <form action="MainController">
            <button type="submit" name="btAction" value="Logout">
                Logout
            </button>
        </form>
        <a href="MainController?btAction=searchAll">Click this link to move to shopping page!</a>
    </body>
</html>
