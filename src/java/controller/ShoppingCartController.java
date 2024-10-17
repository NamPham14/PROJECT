/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.shopOrders;
import entity.shoppingCarts;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOShoppingCarts;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShoppingCartController", urlPatterns = {"/ShoppingCartURL"})
public class ShoppingCartController extends HttpServlet {

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
        DAOShoppingCarts dao = new DAOShoppingCarts();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");
            if(service.equals("deleteShoppingCart")){
                dao.deleteCart(Integer.parseInt(request.getParameter("carID")));
                response.sendRedirect("ShoppingCartURL?service=listAllShoppingCarts");
            }

            if (service.equals("insertShoppingCarts")) {
                String TransactionID_User = request.getParameter("TransactionID_User");
                String TransactionID_Merchant = request.getParameter("TransactionID_Merchant");
                String OrderID = request.getParameter("OrderID");
                String UserID = request.getParameter("UserID");

                int transactionID_User = Integer.parseInt(TransactionID_User);
                int transactionID_Merchant = Integer.parseInt(TransactionID_Merchant);
                int orderID =Integer.parseInt(OrderID);
                int userID = Integer.parseInt(UserID);
                
                shoppingCarts scar = new shoppingCarts(transactionID_User, transactionID_Merchant, orderID, userID);
                int n = dao.addCart(scar);
                 response.sendRedirect("ShoppingCartURL?service=listAllShoppingCarts");
            }
           if(service.equals("listAllShoppingCarts")){
               
               
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCartController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("""
                         <form action="ShoppingCartURL" method="get">
                      <p>Search Name: <input type="text" name="cart" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllShoppingCarts"> 
                      </p>
                          </form>
                      """);
            out.print("<p><a href=\"HTML/insertShoppingCarts.html\">INSERT SHOPPING CART</a></p>");

            String sql = "SELECT * FROM Shipping_Carts";
            String submit = request.getParameter("submit");
            if (submit == null) { //chua nhan submit --> khong search --> sql default
                sql = "SELECT * FROM Shipping_Carts";
            } else {
                String cart = request.getParameter("cart");
                sql = "SELECT * FROM Shipping_Carts where  TransactionID_User  like '%" + cart + "%'";
            }
            Vector<shoppingCarts> vector = dao.getCart(sql);

            out.println("<table border=1>\n"
                    + "        <tr>\n"
                    + "            <th>Shipping_CartID</th>"
                    + "            <th>TransactionID_User</th>"
                    + "            <th>TransactionID_Merchant</th>"
                    + "            <th>OrderID</th>"
                    + "            <th>UserID</th>"
                    + "            <th>Update</th>"
                    + "            <th>Delete</th>"
                    + "        </tr>");

            for (shoppingCarts carts : vector) {
                out.print("<tr>\n"
                        + "            <td>" + carts.getShipping_CartID() + "</td>\n"
                        + "            <td>" + carts.getTransactionID_User() + "</td>\n"
                        + "            <td>" + carts.getTransactionID_Merchant() + "</td>\n"
                        + "            <td>" + carts.getOrderID() + "</td>\n"
                        + "            <td>" + carts.getUserID() + "</td>\n"
                        + "            <td> </td>\n"
                        + "<td><a href=\"ShoppingCartURL?service=deleteShoppingCart&carID="+carts.getShipping_CartID()+"\">Delete </a></td>\n"
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
