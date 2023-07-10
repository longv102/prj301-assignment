package longvu.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
public class MainController extends HttpServlet {

    private final String START_PAGE = "login.html";
    
    private final String LOGIN = "Login";
    private final String LOGIN_CONTROLLER = "LoginController";
    
    private final String ADD_PRODUCT = "addProduct";
    private final String ADD_PRODUCT_PAGE = "addProduct.jsp";
    
    private final String ADD = "Add";
    private final String ADD_PRODUCT_CONTROLLER = "AddProductController";
    
    private final String SEARCH_PRODUCT = "proSearch";
    private final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    
    private final String DELETE_PRODUCT = "proDelete";
    private final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    
    private final String UPDATE_PRODUCT = "proUpdate";
    private final String UPDATE_PRODUCT_CONTROLLER = "UpdateProductController";
    
    private final String LOGOUT = "Logout";
    private final String LOGOUT_CONTROLLER = "LogoutController";
    
    private final String CREATE_USER = "createUser";
    private final String CREATE_USER_PAGE = "createUser.jsp";
    
    private final String CREATE = "Create";
    private final String CREATE_USER_CONTROLLER = "CreateUserController";
    
    private final String SHOPPING_SEARCH = "shopSearch";
    private final String SHOPPING_SEARCH_CONTROLLER = "ShoppingSearchController";
        
    private final String SHOPPING_SEARCH_ALL_PRODUCTS = "searchAll";
    private final String SHOPPING_SEARCH_ALL_PRODUCTS_CONTROLLER = "ShoppingSearchAllController";
    
    private final String VIEW_CART = "Cart";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    
    private final String ADD_TO_CART = "addCart";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartController";
    
    private final String EDIT = "Edit";
    private final String EDIT_CART_CONTROLLER = "EditCartController";
    
    private final String REMOVE = "Remove";
    private final String REMOVE_CART_CONTROLLER = "RemoveCartController";
    
    private final String CHECKOUT = "checkout";
    private final String CHECKOUT_CART_CONTROLLER = "CheckoutCartController";
    
    private final String SEARCH_USER = "userSearch";
    private final String SEARCH_USER_CONTROLLER = "SearchUserController";
    
    private final String DELETE_USER = "userDelete";
    private final String DELETE_USER_CONTROLLER = "DeleteUserController";
    
    private final String UPDATE_USER = "userUpdate";
    private final String UPDATE_USER_CONTROLLER = "UpdateUserController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = START_PAGE;
        try {
            String button = request.getParameter("btAction");
            if (button == null) {
                url = START_PAGE;
            } else if (LOGIN.equals(button)) {
                url = LOGIN_CONTROLLER;
            } else if (LOGOUT.equals(button)) {
                url = LOGOUT_CONTROLLER;
            } else if (CREATE_USER.equals(button)) {
                url = CREATE_USER_PAGE;
            } else if (CREATE.equals(button)) {
                url = CREATE_USER_CONTROLLER;
            } else if (SEARCH_PRODUCT.equals(button)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(button)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (UPDATE_PRODUCT.equals(button)) {
                url = UPDATE_PRODUCT_CONTROLLER;
            } else if (ADD_PRODUCT.equals(button)) {
                url = ADD_PRODUCT_PAGE;
            } else if (ADD.equals(button)) {
                url = ADD_PRODUCT_CONTROLLER;
            } else if (SHOPPING_SEARCH.equals(button)) {
                url = SHOPPING_SEARCH_CONTROLLER;
            } else if (SHOPPING_SEARCH_ALL_PRODUCTS.equals(button)) {
                url = SHOPPING_SEARCH_ALL_PRODUCTS_CONTROLLER;
            } else if (ADD_TO_CART.equals(button)) {
                url = ADD_TO_CART_CONTROLLER;
            } else if (VIEW_CART.equals(button)) {
                url = VIEW_CART_PAGE;
            } else if (EDIT.equals(button)) {
                url = EDIT_CART_CONTROLLER;
            } else if (REMOVE.equals(button)) {
                url = REMOVE_CART_CONTROLLER;
            } else if (CHECKOUT.equals(button)) {
                url = CHECKOUT_CART_CONTROLLER;
            } else if (SEARCH_USER.equals(button)) {
                url = SEARCH_USER_CONTROLLER;
            } else if (DELETE_USER.equals(button)) {
                url = DELETE_USER_CONTROLLER;
            } else if (UPDATE_USER.equals(button)) {
                url = UPDATE_USER_CONTROLLER;
            } 
        } catch(Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
