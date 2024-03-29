/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.servlet;

import hungnm.user.UserDAO;
import hungnm.user.UserDTO;
import hungnm.user.UserErrorObj;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author SE130008
 */
@WebServlet(name = "InsertNewUserServlet", urlPatterns = {"/InsertNewUserServlet"})
public class InsertNewUserServlet extends HttpServlet {

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
        String userId = null;
        String password = null;
        String confirmPassword = null;
        String username = null;
        String email = null;
        String phone = null;
        String role = null;
        try {
            List items = null;
            items = (List) request.getAttribute("ITEMS");
            Iterator iter = items.iterator();
            Hashtable params = new Hashtable();
            String fileName = null;
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                    params.put(item.getFieldName(), item.getString());
                } else {
                    try {
                        String itemName = item.getName();
                        fileName = itemName.substring(itemName.lastIndexOf("\\") + 1);
                        System.out.println("path: " + fileName);
                        String RealPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                        System.out.println("RealPath: " + RealPath);
                        File saveFile = new File(RealPath);
                        item.write(saveFile);
                        System.out.println(fileName);
                    } catch (Exception e) {
                        
                    }
                }
            }
            UserErrorObj errorObj = new UserErrorObj();
            boolean error = false;
            userId = (String) params.get("txtUserId");
            if (userId.isEmpty()) {
                errorObj.setUserIdLengthError("UserId is empty");
                error = true;
            }
            password = (String) params.get("txtPassword");
            if (password.isEmpty()) {
                errorObj.setUserIdLengthError("Password is empty");
                error = true;
            }
            confirmPassword = (String) params.get("txtPasswordConfirm");
            if (!confirmPassword.equals(password)) {
                errorObj.setConfirmNotMatch("Confirm password is not match");
                error = true;
            }
            username = (String) params.get("txtUsername");
            if (username.isEmpty()) {
                errorObj.setUsernameLengthError("Username is empty");
                error = true;
            }
            email = (String) params.get("txtEmail");
            if (email.isEmpty()) {
                errorObj.setEmailLengthError("Email is empty");
                error = true;
            }
            phone = (String) params.get("txtPhone");
            if (phone.isEmpty()) {
                errorObj.setPhoneLengthError("Phone is empty");
                error = true;
            }
            role = (String) params.get("txtRole");
            if(fileName.isEmpty()){
                errorObj.setImageEmpty("Image is empty");
                error = true;
            }
            System.out.println(userId);
            System.out.println(password);
            System.out.println(username);
            System.out.println(email);
            System.out.println(phone);
            System.out.println(fileName);
            System.out.println(role);

            if (error) {
                request.setAttribute("ERROROBJ", errorObj);
            } else {
                UserDTO userDTO = new UserDTO(userId, password, username, email, phone, fileName, role, "active");
                UserDAO userDAO = new UserDAO();
                boolean resultCreateUser = userDAO.createAccount(userDTO);
                request.setAttribute("RESULT_CREATR_ACCOUNT", resultCreateUser);
            }

            

        } catch (NamingException ex) {
            log("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("NoSuchAlgorithmException: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher("createNewUser.jsp");
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
