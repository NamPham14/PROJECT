/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.shopOrders;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOshopOrders;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShopOrdersController", urlPatterns = {"/ShopOrdersURL"})
public class ShopOrdersController extends HttpServlet {

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
        DAOshopOrders dao = new DAOshopOrders();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
             String service = request.getParameter("service");
             
             if(service.equals("deleteShopOrders")){
                 dao.deleteShopOrder(Integer.parseInt(request.getParameter("soID")));
              response.sendRedirect("ShopOrdersURL?service=listAllShopOrders");
                
             }
             
             if(service.equals("insertShopOrders")){
                String ShippingID = request.getParameter("ShippingID");
                String UserID = request.getParameter("UserID");
                String Address_Shipping = request.getParameter("Address_Shipping");
                String Total_Price = request.getParameter("Total_Price");
                String Date_Order = request.getParameter("Date_Order");
                
                
                 if (Date_Order.equals("")) {
                    out.print("Date_Order must not be empty");

                }
                  if (Address_Shipping.equals("")) {
                    out.print("Address_Shipping must not be empty");

                }
                  
                  int  shippingID = Integer.parseInt(ShippingID);
                  int  userID = Integer.parseInt(UserID);
                  double total_Price = Double.parseDouble(Total_Price);
                  
                  shopOrders so = new shopOrders(shippingID, userID, Date_Order, Address_Shipping, total_Price);
                  int n = dao.addShopOrder(so);
                  response.sendRedirect("ShopOrdersURL?service=listAllShopOrders");
                
                
             }
             if(service.equals("listAllShopOrders")){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopOrdersController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("""
                         <form action="ShopOrdersURL" method="get">
                      <p>Search Name: <input type="text" name="address" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllShopOrders">
                      </p>
                          </form>
                      """);
            out.print("<p><a href=\"HTML/insertShopOrders.html\">Insert SHOP OERDER</a></p>");

            String sql = "SELECT * FROM Shop_Orders";
            String submit = request.getParameter("submit");
            if (submit == null) {
                sql = "SELECT * FROM Shop_Orders";
            } else {
                String address = request.getParameter("address");
                sql = "SELECT * FROM Shop_Orders where Address_Shipping  like '%" + address + "%'";
            }
            Vector<shopOrders> vector = dao.getShopOrder(sql);

            out.println("<table border=1>\n"
                    + "        <tr>\n"
                    + "            <th>Shop_OrderID</th>"
                    + "            <th>ShippingID</th>"
                    + "            <th>UserID</th>"
                    + "            <th>Date_Order</th>"
                    + "            <th>Address_Shipping</th>"
                    + "            <th>Total_Price</th>"
                    + "            <th>Update</th>"
                    + "            <th>Delete</th>"
                    + "        </tr>");
            for (shopOrders orders : vector) {
                out.print("<tr>\n"
                        + "            <td>" + orders.getShop_OrderID() + "</td>\n"
                        + "            <td>" + orders.getShippingID() + "</td>\n"
                        + "            <td>" + orders.getUserID() + "</td>\n"
                        + "            <td>" + orders.getDate_Order() + "</td>\n"
                        + "            <td>" + orders.getAddress_Shipping() + "</td>\n"
                        + "            <td>" + orders.getTotal_Price() + "</td>\n"
                        + "            <td> </td>\n"
                        + "<td><a href=\"ShopOrdersURL?service=deleteShopOrders&soID="+orders.getShop_OrderID()+"\">Delete</a></td>\n"
                        + "        </tr>");

            }
            out.print("</table>");
            out.println("</body>");
            out.println("</html>");
        }
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
