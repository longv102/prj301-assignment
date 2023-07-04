<%-- 
    Document   : adminProduct
    Created on : Jun 15, 2023, 8:53:28 PM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="longvu.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="longvu.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Product Page</title>
        <link rel="stylesheet" href="css/adminStyle.css">
    </head>
    <body>
        <div class="header">
            <h1>Welcome Admin: ${sessionScope.LOGIN_USER.fullName}</h1>
            <form action="MainController">
                <input type="submit" value="Logout" name="btAction" />
            </form>
        </div>

        <form action="MainController" class="search">
            <label>Search Value</label>
            <input type="text" name="searchValue" value="${param.searchValue}">
            <button type="submit" name="btAction" value="proSearch">
                Search
            </button>
        </form> <br>
        
        <!--admin search result-->
        <c:if test="${requestScope.SEARCH_RESULT != null}">
            <c:if test="${not empty requestScope.SEARCH_RESULT}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Product Image</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Category ID</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" varStatus="counter" 
                                   items="${requestScope.SEARCH_RESULT}">
                            <form action="MainController">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        <input type="text" name="id" 
                                               value="${product.id}" readonly="">
                                    </td>
                                    <td>
                                        <input type="text" name="name" 
                                               value="${product.name}" required="">
                                    </td>
                                    <td>
                                        <input type="text" name="image" 
                                               value="${product.image}" required="">
                                    </td>
                                    <td>
                                        <input type="number" name="price" 
                                               value="${product.price}" required="" min="1">
                                    </td>
                                    <td>
                                        <input type="number" name="quantity" 
                                               value="${product.quantity}" required="" min="1">
                                    </td>
                                    <td>
                                        <input type="text" name="categoryId" 
                                               value="${product.categoryId}" readonly="">
                                    </td>
                                    <!--update-->
                                    <td>
                                        <button type="submit" name="btAction" 
                                                value="proUpdate">
                                            Update
                                        </button>
                                        <input type="hidden" name="searchValue" 
                                               value="${param.searchValue}">
                                    </td>
                                    <!--delete-->
                                    <td>
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="btAction" value="proDelete"></c:param>
                                            <c:param name="searchValue" 
                                                     value="${param.searchValue}"></c:param>
                                            <c:param name="id" value="${product.id}"></c:param>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </c:if>
        <h2 style="color: red">${requestScope.MESSAGE}</h2>
        <br>
        <a href="MainController?btAction=addProduct">
            Click the link to add a new product!
        </a>
    </body>
</html>
