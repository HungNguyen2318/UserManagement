/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.servlet;

import hungnm.user.UserDAO;
import hungnm.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE130008
 */
@WebServlet(name = "SearchByserNameServlet", urlPatterns = {"/SearchByserNameServlet"})
public class SearchByserNameServlet extends HttpServlet {

    public final String LOGIN_PAGE = "login.html";
    public final String VIEW_PAGE = "search.jsp";
    public final String SEARCH_PAGE = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SEARCH_PAGE;
        HttpSession session = request.getSession(); 
        //1. Get value search from search.jsp
        String searchValue = request.getParameter("txtValueSearch");
        try (PrintWriter out = response.getWriter()) {
            if (session.isNew()) {
                url = LOGIN_PAGE;
            } else if (!searchValue.trim().equals("")) {   //make sure search value not empty
                //2. Create new class UserDAO to call method... 
                UserDAO dao = new UserDAO();
                //3. Prepare List to contain result...
                List<UserDTO> result = new ArrayList<>();
                //4. Call method findByLikeName
                result = dao.findByLikeName(searchValue);
                //5. Set result to Attribute
                request.setAttribute("Result_Search", result);
                //6. Set url to View page after Search successfull
                url = VIEW_PAGE;
            }
        } catch (SQLException ex) {
            log("SQLException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
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
