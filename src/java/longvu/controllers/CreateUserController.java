package longvu.controllers;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longvu.user.UserDAO;
import longvu.user.UserDTO;
import longvu.user.UserError;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    private final String ERROR = "createUserError.jsp";
    private final String SUCCESS = "login.html";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        
        try {
            String username = request.getParameter("username");
            String fullName = request.getParameter("fullName");
            String roleId = request.getParameter("roleId");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            
            UserDAO dao = new UserDAO();
            UserDTO user = new UserDTO(username, password, fullName, email, roleId);
            boolean checkValidation = true;
            
            // User validation
            if (username.length() > 20 || username.length() < 5) {
                userError.setUsernameError("Username MUST be in [5, 10] characters!");
                checkValidation = false;
            }
            // check duplicate username
            boolean checkDuplicate = dao.checkDuplicate(username);
            if (checkDuplicate) {
                userError.setUsernameError("Username MUST NOT DUPLICATE!\nUsername: " + username);
                checkValidation = false;

            }
            if (fullName.length() > 50 || fullName.length() < 5) {
                userError.setFullNameError("Fullname MUST be [5, 50] characters");
                checkValidation = false;
            }
            
            if (password.length() > 10 || password.length() < 2) {
                userError.setPasswordError("Password MUST be in [2, 10] characters");
                checkValidation = false;
            }
            
            if (!password.equals(confirm)) {
                userError.setConfirmError("Two passwords do NOT MATCH!");
                checkValidation = false;
            }
            if (checkValidation) {
                boolean checkInsert = dao.createUser(user);
                if (checkInsert)
                    url = SUCCESS;
                else
                    userError.setMessageError("Cannot create an user, UNKNOWN ERROR!");
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch(ClassNotFoundException | SQLException e) {
            log("Errort at CreateController: " + e.toString());
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
