<%-- 
    Document   : adminUser
    Created on : Jun 25, 2023, 10:06:38 PM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin User</title>
        <link rel="stylesheet" href="css/adminStyle.css">
    </head>
    <body>
        <!--admin user authorization-->
        <c:if test="${sessionScope.LOGIN_USER == null || sessionScope.LOGIN_USER.roleId ne 'ADU'}">
            <c:redirect url="login.html"></c:redirect>
        </c:if>

        <div class="header">
            <h1>Welcome Admin: ${sessionScope.LOGIN_USER.fullName}</h1>
            <form action="MainController">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </div>
        <!--admin search bar-->
        <form action="MainController">
            <label>Search Value</label>
            <input type="text" name="searchValue" value="${param.searchValue}">
            <button type="submit" name="btAction" value="userSearch">
                Search
            </button>
        </form>
        <br>
        <c:if test="${requestScope.USER_SEARCH_RESULT != null}">
            <c:if test="${not empty requestScope.USER_SEARCH_RESULT}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Username</th>
                            <th>Full Name</th>
                            <th>Password</th>
                            <th>Role ID</th>
                            <th>Email</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="user" varStatus="counter" items="${requestScope.USER_SEARCH_RESULT}">
                        <form action="MainController">
                            <tr>
                                <td>${counter.count}</td>
                                <td>
                                    <input type="text" name="username" value="${user.username}" readonly="">
                                </td>
                                <td>
                                    <input type="text" name="fullName" value="${user.fullName}" required="">
                                </td>
                                <td>${user.password}</td>
                                <td>
                                    <input type="text" name="roleId" value="${user.roleId}" required="">
                                </td>
                                <td>
                                    <input type="text" name="email" value="${user.email}">
                                </td>
                                <td>
                                    <input type="hidden" name="username" value="${user.username}" readonly="">
                                    <input type="hidden" name="searchValue" value="${param.searchValue}" readonly="">
                                    <button type="submit" name="btAction" value="userUpdate">
                                        Update
                                    </button>
                                </td>
                                <td>
                                    <input type="hidden" name="username" value="${user.username}" readonly="">
                                    <input type="hidden" name="searchValue" value="${param.searchValue}" readonly="">
                                    <button type="submit" name="btAction" value="userDelete">
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            ${requestScope.USER_MESSAGE}
        </c:if>
    </c:if>
</body>
</html>
