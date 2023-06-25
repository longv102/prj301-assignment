/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
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
@WebServlet(name = "DeleteUserController", urlPatterns = {"/DeleteUserController"})
public class DeleteUserController extends HttpServlet {

    private final String ERROR = "SearchUserController";
    private final String SUCCESS = "SearchUserController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("username");
            UserDAO userDAO = new UserDAO();
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
                if (username.equals(loginUser.getUsername())) {
                    request.setAttribute("DELETE_ERROR", "Cannot delete this user!");
                    return;
                }
                boolean checkDelete = userDAO.deleteAUser(username);
                if (checkDelete) {
                    request.setAttribute("DELETE_MSG", "Delete successfully!");
                    url = SUCCESS;
                }
            }
        } catch(SQLException | NamingException e) {
          log("Error at DeleteUserController: " + e.toString());  
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
