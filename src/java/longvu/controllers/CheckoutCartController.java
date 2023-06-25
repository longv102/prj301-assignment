/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longvu.cart.Cart;
import longvu.order.OrderDAO;
import longvu.order.OrderDTO;
import longvu.orderdetail.OrderDetailDAO;
import longvu.orderdetail.OrderDetailDTO;
import longvu.product.ProductDAO;
import longvu.product.ProductDTO;
import longvu.user.UserDTO;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
@WebServlet(name = "CheckoutCartController", urlPatterns = {"/CheckoutCartController"})
public class CheckoutCartController extends HttpServlet {

    private final String ERROR = "viewCart.jsp";
    private final String SUCCESS = "viewCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            // get username of the user
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String username = loginUser.getUsername();
            Timestamp orderDate = Timestamp.from(Instant.now());
            if (cart != null) {
                ProductDAO productDAO = new ProductDAO();
                double total = 0;
                for (ProductDTO product : cart.getCart().values()) {
                    String productId = product.getId();
                    int purchaseQuantity = product.getQuantity();
                    int currentQuantity = productDAO.getProductQuantity(productId);
                    if (currentQuantity < purchaseQuantity) {
                        request.setAttribute("CHECKOUT_MESSAGE", "The product " + productId + " is out of stock!");
                        return;
                    }
                    total += product.getPrice() * purchaseQuantity;
                }
                // create OrderDAO
                OrderDAO orderDAO = new OrderDAO();
                OrderDTO order = new OrderDTO(orderDate, total, username);
                // get the orderId (Note: orderId is IDENTITY)
                int orderId = orderDAO.insert(order);
                // log(orderId + "");
                // create OrderDetailDAO
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                for (ProductDTO product : cart.getCart().values()) {
                    String productId = product.getId();
                    int purchaseQuantity = product.getQuantity();
                    double price = product.getPrice();
                    OrderDetailDTO orderDetail = new OrderDetailDTO(1, orderId, productId, price, purchaseQuantity);
                    boolean checkOrderDetailInsert = orderDetailDAO.insert(orderDetail);
                    // update the quantity of the product in Product
                    int currentQuantity = productDAO.getProductQuantity(productId);
                    int newCurrentQuantity = currentQuantity - purchaseQuantity; 
                    ProductDTO updateProductQuantity = new ProductDTO(productId, "", "", price, newCurrentQuantity, "");
                    boolean checkUpdateQuantity = productDAO.updateProductQuantity(updateProductQuantity);
                    if (checkUpdateQuantity) {
                        request.setAttribute("CHECKOUT_MESSAGE", "Checkout successfully!");
                        url = SUCCESS;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            log("Error at CheckoutCartController: " + e.toString());
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
