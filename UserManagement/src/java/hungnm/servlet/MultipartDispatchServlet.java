/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnm.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "MultipartDispatchServlet", urlPatterns = {"/MultipartDispatchServlet"})
public class MultipartDispatchServlet extends HttpServlet {
private final String LOGIN_PAGE = "login.html";
private final String CREATE_NEW_USER_SERVLET = "InsertNewUserServlet";
private final String UPDATE_USER_SERVLET = "UpdateUserServlet";
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
        String url = LOGIN_PAGE;
        //1. khoi tao obj FileItemFactory
        FileItemFactory factory = new DiskFileItemFactory();
        //2. khoi tao obj ServletFileUpload
        ServletFileUpload upload = new ServletFileUpload(factory);
        List items = null;       
        try {
           items = upload.parseRequest(request);
           //set items into Attribute
           request.setAttribute("ITEMS", items);
           Iterator iter = items.iterator();
           while(iter.hasNext()){
               FileItem item = (FileItem) iter.next();
               if(item.isFormField()){
                   if(item.getFieldName().equals("btAction")){
                       String button = item.getString();
                       if(button.equals("Register")){
                           url = CREATE_NEW_USER_SERVLET;
                       }else if(button.equals("Update")){
                           url = UPDATE_USER_SERVLET;
                       }
                   }
               }
           }           
        } catch (FileUploadException ex) {
        Logger.getLogger(MultipartDispatchServlet.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
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
