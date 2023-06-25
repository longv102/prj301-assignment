<%-- 
    Document   : createProduct
    Created on : Jun 18, 2023, 9:16:36 PM
    Author     : Long Vu <longvu.selfmademillionaire.com>
--%>

<%@page import="longvu.product.ProductError"%>
<%@page import="longvu.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add A Product</title>
    </head>
    <body>
        <% 
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"ADP".equals(loginUser.getRoleId())) {
                response.sendRedirect("login.html");
                return;
            }
            ProductError error = (ProductError) request.getAttribute("ERROR_MSG");
            if (error == null) {
                error = new ProductError();
            }
            String idError = error.getIdError();
            String nameError = error.getNameError();
            String imageError = error.getImageError();
        %>
        <h1>Add A Product</h1>
        <form action="MainController">
            <label>Product ID</label>
            <input type="text" name="id" value="" required="">
            <br>
            <%= idError %>
            <br>
            <label>Product Name</label>
            <input type="text" name="name" value="" required="">
            <br>
            <%= nameError %>
            <br>
            <label>Product Image</label>
            <input type="text" name="image" value="">
            <br>
            <%= imageError %>
            <br>
            <label>Price</label>
            <input type="number" name="price" value="" required="" min="1">
            <br>
            <label>Quantity</label>
            <input type="number" name="quantity" value="" required="" min="1">
            <br>
            <label>Category ID</label>
            <select name="category">
                <option value="STA">STA</option>
                <option value="FB">FB</option>
            </select>
            <br>
            <button type="submit" name="btAction" value="Add">
                Add
            </button>
            <button type="reset" name="Reset">
                Reset
            </button>
        </form>
    </body>
    <a href="adminProduct.jsp">Return to the search product page</a>
</html>
