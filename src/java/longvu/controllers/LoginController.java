package longvu.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longvu.user.UserDAO;
import longvu.user.UserDTO;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {
    
    private final String ERROR = "loginError.jsp";
    // Admin for managing users
    private final String ADMIN_USER_ROLE = "ADU";
    private final String ADMIN_USER_PAGE = "adminUser.jsp";
    
    // Admin for managing products
    private final String ADMIN_PRODUCT_ROLE = "ADP";
    private final String ADMIN_PRODUCT_PAGE = "adminProduct.jsp";
    
    private final String USER_ROLE = "US";
    private final String USER_PAGE = "user.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(username, password);
            if (loginUser == null) {
                request.setAttribute("ERROR_MSG", "Incorrect Username or Password!!!");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                if (ADMIN_USER_ROLE.equals(loginUser.getRoleId())) {
                    url = ADMIN_USER_PAGE;
                } else if (ADMIN_PRODUCT_ROLE.equals(loginUser.getRoleId())) {
                    url = ADMIN_PRODUCT_PAGE;
                } else if (USER_ROLE.equals(loginUser.getRoleId())) {
                    url = USER_PAGE;
                } else 
                    request.setAttribute("ERROR_MSG", "Your role is not supported yet!!!");
            }
        } catch(ClassNotFoundException | SQLException e) {
            log("Error at LoginController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
