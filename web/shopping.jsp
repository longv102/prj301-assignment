<%-- 
    Document   : shopping
    Created on : Jun 22, 2023, 7:55:00 AM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="longvu.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.usebootstrap.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/shoppingStyle.css">
        <title>Shopping Page</title>
    </head>
    <body>
        <!--authorization-->
        <div class="header">
            <h1>Welcome to Long Vu's Store</h1>
            <div class="navigation-bar">
                <form action="MainController">
                    <div>
                        <label>Search Product</label>
                        <input type="text" name="searchValue" value="${param.searchValue}">
                        <button type="submit" name="btAction" value="shopSearch">
                            Search
                        </button>
                    </div>

                    <div style="" class="logout">
                        <button type="submit" name="btAction" value="Logout">
                            Logout
                        </button>
                    </div>
                </form>

                <form action="MainController">
                    <div>
                        <label>Search All Products</label>
                        <button type="submit" name="btAction" value="searchAll">
                            Search All
                        </button>
                    </div>

                    <div class="Cart">
                        <button type="submit" name="btAction" value="Cart">
                            View Cart
                        </button>
                    </div>
                </form>
            </div>
        </div>
        <!-- add to cart successfully message -->
        <h3 style="text-align: center;">${requestScope.CART_MESSAGE} ${requestScope.CART_MESSAGE_ERROR}</h3>
        <div class="list-product">
            <div class="container">
                <div class="row">
                    <c:if test="${requestScope.SEARCH_RESULT != null}">
                        <c:forEach var="product" items="${requestScope.SEARCH_RESULT}">
                            <form action="MainController">
                                <div class="product col-lg-4 col-sm-12">
                                    <input type="hidden" name="searchValue" value="${param.searchValue}" readonly="">
                                    <!--product's image-->
                                    <img src="${product.image}" alt="product-image">
                                    <div class="product-info">
                                        <!--product's info-->
                                        <input type="hidden" name="id" value="${product.id}" readonly="">
                                        <!--<input type="hidden" name="name" value="" readonly="">-->
                                        <!--<input type="hidden" name="price" value="" readonly="">-->
                                        <p>${product.name}</p>
                                        <p>Price: ${product.price}</p>
                                        <select name="quantity" id="">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                        </select>
                                        <button type="submit" name="btAction" value="addCart">
                                            Add to cart
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
        <h2 style="color: red">${requestScope.MESSAGE}</h2>
    </body>
</html>
