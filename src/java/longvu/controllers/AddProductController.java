package longvu.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longvu.product.ProductDAO;
import longvu.product.ProductDTO;
import longvu.product.ProductError;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
@WebServlet(name = "AddProductController", urlPatterns = {"/AddProductController"})
public class AddProductController extends HttpServlet {

    private final String ERROR = "addProduct.jsp";
    private final String SUCCESS = "adminProduct.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String image = request.getParameter("image");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String category = request.getParameter("category");
            
            ProductDAO dao = new ProductDAO();
            ProductDTO product = new ProductDTO(id, name, image, price, quantity, category);
            ProductError error = new ProductError();
            boolean checkValidation = true;
            // product validation
            if (!id.startsWith("P") || id.length() != 4) {
                error.setIdError("The product's id MUST start with the letter P, "
                        + "and the length is in 4 characters!");
                checkValidation = false;
            }
            // check duplicate of product's id
            boolean checkDuplicate = dao.checkDuplicate(id);
            if (checkDuplicate) {
                error.setIdError("The product's id CANNOT DUPLICATE. ID: " + product.getId());
                checkValidation = false;
            }
            
            if (image == null) {
                error.setImageError("Provide an image for the product!");
                checkValidation = false;
            }
            
            if (checkValidation) {
                boolean checkAdd = dao.addAProduct(product);
                if (checkAdd)
                    url = SUCCESS;
                else
                    error.setMessageError("Unknown error. Cannot add a product!");
            } else {
                request.setAttribute("ERROR_MSG", error);
            }
        } catch (ClassNotFoundException | SQLException | NamingException e) {
            log("Error at AddProductController: " + e.toString());
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
