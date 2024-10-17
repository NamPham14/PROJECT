/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Shipping;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOShipping;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShippingController", urlPatterns = {"/ShippingURL"})
public class ShippingController extends HttpServlet {

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
        DAOShipping dao = new DAOShipping();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            String service = request.getParameter("service");
            if(service.equals("deleteShipping")){
                dao.deleteShipping(Integer.parseInt(request.getParameter("shipID")));
                response.sendRedirect("ShippingURL?service=listAllShipping");
            }
            
            if (service.equals("insertShipping")) {
                
                String Name = request.getParameter("Name");
                String Price =request.getParameter("Price");
                
                if (Name.equals("")) {
                    out.print("Shipping name must not be empty");

                }
                double price = Double.parseDouble(Price);
                
                Shipping ship = new Shipping(Name, price);
                int n = dao.addShipping(ship);
                response.sendRedirect("ShippingURL?service=listAllShipping");
            }
            if(service.equals("listAllShipping")){
                    
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShippingController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("""
                         <form action="ShippingURL" method="get">
                      <p>Search Name: <input type="text" name="shipname" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllShipping">  
                      </p>
                          </form>
                      """);
            // Link for inserting a new product
            out.print("<p><a href=\"HTML/insertShipping.html\">Insert Shipping</a></p>");

            String sql = "SELECT * FROM Shipping";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "SELECT * FROM Shipping";
            } else {
                String shipname = request.getParameter("shipname");
                sql = "SELECT * FROM Shipping where Name like '%" + shipname + "%'";
            }
            Vector<Shipping> vector = dao.getShipping(sql);

            out.println("<table border=1>\n"
                    + "        <tr>\n"
                    + "            <th>ShippingID</th>"
                    + "            <th>Name</th>"
                    + "            <th>Price</th>"
                    + "            <th>Update</th>"
                    + "            <th>Delete</th>"
                    + "        </tr>");
            for (Shipping shipping : vector) {
                out.print("<tr>\n"
                        + "            <td>" + shipping.getShippingID() + "</td>\n"
                        + "            <td>" + shipping.getName() + "</td>\n"
                        + "            <td>" + shipping.getPrice() + "</td>\n"
                        + "            <td><a href=\"ShippingURL?service=deleteShipping&shipID="+shipping.getShippingID()+"\">Delete</a></td>\n"
                        + "            <td></td>\n"
                        + "        </tr>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }}

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
