<%-- 
    Document   : viewCart
    Created on : Jun 22, 2023, 8:58:48 AM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
    </head>
    <body>
        <h1>Your shopping Cart</h1>
        <c:if test="${sessionScope.CART != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Product ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Edit</th>
                        <th>Remove</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" varStatus="counter" 
                               items="${sessionScope.CART.cart}">
                        <c:set var="product" value="${item.value}" />
                        <c:set var="itemTotal" value="${product.price * product.quantity}" />
                    <form action="MainController">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                <input type="text" name="id" value="${product.id}" readonly="">
                            </td>
                            <td>${product.name}</td>
                            <td>${product.price}</td>
                            <td>
                                <input type="number" name="quantity" value="${product.quantity}" min="1" required="">
                            </td>
                            <td>
                                <button type="submit" name="btAction" value="Edit">
                                    Edit
                                </button>
                            </td>
                            <td>
                                <button type="submit" name="btAction" value="Remove">
                                    Remove
                                </button>
                            </td>
                            <td>${itemTotal}</td>
                        </tr>
                    </form>
                    <c:set var="total" value="${total + itemTotal}" />
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <h2>Total: ${total}</h2>
    <!--checkout-->
    <form action="MainController">
        <button type="submit" name="btAction" value="checkout">
            Checkout
        </button>
    </form>
    ${requestScope.CHECKOUT_MESSAGE}
    <a href="MainController?btAction=searchAll">Add more product!</a>
</body>
</html>
