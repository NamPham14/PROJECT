/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Products;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOProducts;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductsController", urlPatterns = {"/ProductsURL"})
public class ProductsController extends HttpServlet {

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
        DAOProducts dao = new DAOProducts();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductsController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("<form action=\"ProductsURL\" method=\"get\">\n"
                    + "  <p>\n"
                    + "    Search : <input type=\"text\" name=\"pname\">\n"
                    + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                    + "    <input type=\"reset\" value=\"Clear\">\n"
                    + "    \n"
                    + "  </p>\n"
                    + "</form>");
            //link insert
            out.print("");

            String sql = "SELECT * FROM Products";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "SELECT * FROM Products";
            } else {
                String pname = request.getParameter("pname");
                sql = "SELECT * FROM Products Where Name like '%" + pname + "%'";
            }
            Vector<Products> vector = dao.getProduct(sql);

            out.println("<table border=\"1\">\n"
                    + "  <tr>\n"
                    + "    <th>ProductID</th>\n"
                    + "    <th>Description</th>\n"
                    + "    <th>Name</th>\n"
                    + "    <th>Quantity</th>\n"
                    + "    <th>Price</th>\n"
                    + "    <th>CategoryID</th>\n"
                    + "    <th>Update</th>\n"
                    + "    <th>Delete</th>\n"
                    + "  </tr>");

            for (Products products : vector) {
                out.println("<tr>\n"
                        + "    <td>"+products.getProductID()+"</td>\n"
                        + "    <td>"+products.getDescription()+"</td>\n"
                        + "    <td>"+products.getName()+"</td>\n"
                        + "    <td>"+products.getQuantity()+"</td>\n"
                        + "    <td>"+products.getPrice()+"</td>\n"
                        + "    <td>"+products.getCategoryID()+"</td>\n"
                        + "    <td></td>\n"
                        + "    <td></td>\n"
                        + "  </tr>");

            }
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
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
