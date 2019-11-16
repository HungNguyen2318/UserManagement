/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.servlet;

import hungnm.role.RoleDAO;
import hungnm.user.UserDAO;
import hungnm.user.UserDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
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
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (!isMultiPart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                items = upload.parseRequest(request);
//                Iterator iter = items.iterator();
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
                            e.printStackTrace();
                        }
                    }
                }
                String userId = (String) params.get("txtUserId");
                String password = (String) params.get("txtPassword");
                String confirmPassword = (String) params.get("txtPasswordConfirm");
                String username = (String) params.get("txtUsername");
                String email = (String) params.get("txtEmail");
                String phone = (String) params.get("txtPhone");
                String role = (String) params.get("txtRole");
                UserDTO userDTO = new UserDTO(userId, password, username, email, phone, fileName, role, "active");
                UserDAO userDAO = new UserDAO();
                boolean resultCreateUser = userDAO.createAccount(userDTO);
                request.setAttribute("RESULT_CREATR_ACCOUNT", resultCreateUser);
            }
        } catch (FileUploadException ex) {
            log("FileUploadException: " + ex.getMessage());
        } catch (NamingException ex) {
            log("NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("SQLException: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            log("NoSuchAlgorithmException: " + ex.getMessage());
        }finally{
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
