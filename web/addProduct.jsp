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
    <style>
        .form-row {
            display: flex;
            margin: 20px 0;
        }
        
        .form-label {
            min-width: 120px;
        }
        
        .form-input {
            flex: 0.2;
        }
        
    </style>
    
    <body>
        <h1>Add A Product</h1>
        <!--form to add a product-->
        <div class="form-container">
            <form action="MainController">
                <div class="form-row">
                    <label class="form-label">Product ID</label>
                    <input type="text" name="id" value="" required="" 
                           class="form-input"> 
                </div>
                <h3 style="color: red">${requestScope.ERROR_MSG.idError}</h3>
                <div class="form-row">
                    <label class="form-label">Product Name</label>
                    <input type="text" name="name" value="" required="" 
                           class="form-input">
                </div>
                
                <div class="form-row">
                    <label class="form-label">Product Image</label>
                    <input type="text" name="image" value="" required="" 
                           class="form-input">
                </div>
                <h3 style="color: red">${requestScope.ERROR_MSG.imageError}</h3>
                <div class="form-row">
                    <label class="form-label">Price</label>
                    <input type="number" name="price" value="" required="" 
                           min="1" class="form-input">
                </div>
                
                <div class="form-row">
                    <label class="form-label">Quantity</label>
                    <input type="number" name="quantity" value="" required="" 
                           min="1" class="form-input">
                </div>
                
                <div class="form-row">
                    <label class="form-label">Category ID</label>
                    <select name="category">
                        <option value="STA">STA</option>
                        <option value="FB">FB</option>
                    </select>
                </div>
                <button type="submit" name="btAction" value="Add">
                    Add
                </button>
                <button type="reset" value="Reset">
                    Reset
                </button>
            </form>
        </div>
    </body>
    <h2 style="color: red">${requestScope.MESSAGE}</h2>
    <a href="adminProduct.jsp">Return to the search product page</a>
</html>
