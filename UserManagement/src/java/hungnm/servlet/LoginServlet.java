/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.servlet;

import hungnm.role.RoleDAO;
import hungnm.role.RoleDTO;
import hungnm.user.UserDAO;
import hungnm.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    private final String INVALID_PAGE = "invalid.html";
    private final String SEARCH_PAGE = "search.jsp";

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
        PrintWriter out = response.getWriter();
        String url = INVALID_PAGE;
        String role = null;
        String username = null;

        try {
            String userId = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            UserDAO dao = new UserDAO();
            String resultUsername = dao.checkLogin(userId, password);
            log(resultUsername);
            if (resultUsername != null) {
                String[] arr = resultUsername.split("[:]");
                username = arr[0];
                System.out.println("Username: " + username);
                role = arr[1];
                System.out.println("Role: " + role);
                if (!role.equals("admin")) {
                    String urlRewriting = "DispathController?"
                            + "btAction=Profile"
                            + "&key=" + userId;
                    url = urlRewriting;
                } else {
                    List<UserDTO> listAllUser = dao.findByLikeName("", null);
                    url = SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("USERLOGIN", username);
                    request.setAttribute("RESULT_SEARCH", listAllUser);
                }
            }
        } catch (SQLException ex) {
            log("SQLException: " + ex.getMessage());
//ex.printStackTrace();
        } catch (NamingException ex) {
            log("NamingException: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("NoSuchAlgorithmException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
