/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Categories;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOCategories;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CategoriesController", urlPatterns = {"/CategoriesURL"})
public class CategoriesController extends HttpServlet {

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
        DAOCategories dao = new DAOCategories();
        try (PrintWriter out = response.getWriter()) {
            
            String service = request.getParameter("service");
            
            if(service.equals("deleteCategories")){
                dao.deleteCategory(Integer.parseInt(request.getParameter("cateID")));
                response.sendRedirect("CategoriesURL?service=listAllCategories");
            }
            
            if(service.equals("insertCategories")){
                //get data
                String Category_Name =request.getParameter("Category_Name");
                
                if(Category_Name.equals("")){
                    System.out.println("Category Name must not be empty");
                }
                
                
                // convert value
                
                Categories cate= new Categories(Category_Name);
                int n = dao.addCategory(cate);
                response.sendRedirect("CategoriesURL?service=listAllCategories");
                
                
            }
            
            if (service.equals("listAllCategories")) {
                
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoriesController</title>");
            out.println("</head>");
            out.println("<body>");

            //form search
            out.print("<form action=\"CategoriesURL\" method=\"get\">\n"
                    + "    <p>\n"
                    + "        Search: <input type=\"text\" name=\"cate\">\n"
                    + "        <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "        <input type=\"reset\" value=\"Clear\">\n"
                    +" <input type=\"hidden\" name=\"service\" value=\"listAllCategories\">"
                    + "        \n"
                    + "    </p>");
            // link insert
            out.print(" <p><a href=\"HTML/insertCategories.html\">INSERT CATEGORY</a></p>");

            String sql = "SELECT *  FROM Categories";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "SELECT *  FROM Categories";
            } else {
                String cate = request.getParameter("cate");
                sql = "SELECT *  FROM Categories Where Category_Name like '%" + cate + "%'";
            }
            Vector<Categories> vector = dao.getCategory(sql);

            //Table
            out.println("<table border=\"1\">\n"
                    + "    <tr>\n"
                    + "        <th>CategoryID</th>\n"
                    + "        <th>Category_Name</th>\n"
                    + "        <th>Update</th>\n"
                    + "        <th>Delete</th>\n"
                    + "    </tr>");

            for (Categories categories : vector) {
                out.println("<tr>\n"
                        + "        <td>"+categories.getCategoryID()+"</td>\n"
                        + "        <td>"+categories.getCategory_Name()+"</td>\n"
                        + "        <td></td>\n"
                        + "        <td><a href=\"CategoriesURL?service=deleteCategories&cateID="+categories.getCategoryID()+"\">Delete</a></td>"
                        + "    </tr>");

            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }}
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
