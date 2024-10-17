/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.shoppingCartItems;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOShoppingCartItem;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ShoppingCartItemController", urlPatterns = {"/ShoppingCartItemURL"})
public class ShoppingCartItemController extends HttpServlet {

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
        DAOShoppingCartItem dao = new DAOShoppingCartItem();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");

            if (service.equals("insertCartItem")) {

                String Quantity = request.getParameter("Quantity");
                String ProductID = request.getParameter("ProductID");
                String Shipping_CartID = request.getParameter("Shipping_CartID");

                int quantity = Integer.parseInt(Quantity);
                int productID = Integer.parseInt(ProductID); 
                int shipping_CartID = Integer.parseInt(Shipping_CartID);
                shoppingCartItems cartItem = new shoppingCartItems(quantity, productID, shipping_CartID);
                int n = dao.addCartItem(cartItem);
                 response.sendRedirect("ShoppingCartItemURL?service=listAllShoppingCartItem");
            }
              
            
            if (service.equals("listAllShoppingCartItem")) {
                
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShoppingCartItemController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("""
                         <form action="ShoppingCartItemURL" method="get">
                      <p>Search Name: <input type="text" name="cartname" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                       <input type="hidden" name="service" value="listAllShoppingCartItem">    
                      </p>
                          </form>
                      """);
            
            out.print("<p><a href=\"HTML/insertShoppingCartItem.html\">Insert CART ITEM</a></p>");

            String sql = "SELECT * FROM Shopping_Cart_Items";
            String submit = request.getParameter("submit");
            if (submit == null) { 
                sql = "SELECT * FROM Shopping_Cart_Items";
            } else {
                String cartname = request.getParameter("cartname");
                sql = "SELECT * FROM Shopping_Cart_Items  WHERE Quantity like '%" + cartname + "%'";
            }
            Vector<shoppingCartItems> vector = dao.getCartItem(sql);

            out.println("<table border=1>\n"
                    + "        <tr>\n"
                    + "            <th>Cart_ItemID</th>"
                    + "            <th>Quantity</th>"
                    + "            <th>ProductID</th>"
                    + "            <th>Shipping_CartID</th>"
                    + "            <th>Update</th>"
                    + "            <th>Delete</th>"
                    + "        </tr>");
            for (shoppingCartItems cartItems : vector) {
                out.print("<tr>\n"
                        + "            <td>" + cartItems.getCart_ItemID() + "</td>\n"
                        + "            <td>" + cartItems.getQuantity() + "</td>\n"
                        + "            <td>" + cartItems.getProductID() + "</td>\n"
                        + "            <td>" + cartItems.getShipping_CartID() + "</td>\n"
                        + "            <td> </td>\n"
                        + "<td></td>\n"
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
