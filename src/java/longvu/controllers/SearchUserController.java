/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longvu.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longvu.user.UserDAO;
import longvu.user.UserDTO;

/**
 *
 * @author Long Vu <longvu.selfmademillionaire.com>
 */
@WebServlet(name = "SearchUserController", urlPatterns = {"/SearchUserController"})
public class SearchUserController extends HttpServlet {

    private final String ERROR = "adminUser.jsp";
    private final String SUCCESS = "adminUser.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchValue = request.getParameter("searchValue");
            if (!searchValue.trim().isEmpty()) {
                UserDAO userDAO = new UserDAO();
                userDAO.getListUsers(searchValue);
                List<UserDTO> users = userDAO.getUsers();
                if (users != null) {
                    request.setAttribute("USER_SEARCH_RESULT", users);
                    url = SUCCESS;
                } else
                    request.setAttribute("USER_MESSAGE", "No record is matched!");
            }
        } catch(ClassNotFoundException | SQLException | NamingException e) {
            log("Error at SearchUserController: " + e.toString());
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
