/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Payments;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOPayments;

/**
 *
 * @author Admin
 */
@WebServlet(name = "PaymentsController", urlPatterns = {"/PaymentsURL"})
public class PaymentsController extends HttpServlet {

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

        DAOPayments dao = new DAOPayments();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String service = request.getParameter("service");

            if (service.equals("deletePayments")) {
                dao.deletePayment(Integer.parseInt(request.getParameter("payID")));
                response.sendRedirect("PaymentsURL?service=listAllPayments");
            }

            if (service.equals("insertPayments")) {
                //get data

                String ShoppingCartID = request.getParameter("ShoppingCartID");
                String UserID = request.getParameter("UserID");
                String Method_Payment = request.getParameter("Method_Payment");
                String Date_Payment = request.getParameter("Date_Payment");

                if (Method_Payment.equals("") || Date_Payment.equals("")) {
                    out.println("Method_Payment and Date_Payment must not be empty");
                    return;
                }

                int shoppingCartid = Integer.parseInt(ShoppingCartID);
                int userid = Integer.parseInt(UserID);

                Payments pay = new Payments(shoppingCartid, userid, Method_Payment, Date_Payment);
                int n = dao.addPayment(pay);
                response.sendRedirect("PaymentsURL?service=listAllPayments");

            }

            if (service.equals("listAllPayments")) {

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet PaymentsController</title>");
                out.println("</head>");
                out.println("<body>");

                out.print("<form action=\"PaymentsURL\" method=\"get\">\n"
                        + "   <p>\n"
                        + "    Search: <input type=\"text\" name=\"paymethod\">\n"
                        + "    <input type=\"submit\" name=\"submit\" value=\"Search\">\n"
                        + "    <input type=\"reset\" value=\"Clear\">\n"
                        + "    <input type=\"hidden\" name=\"service\" value=\"listAllPayments\">\n"
                        + "   </p>\n"
                        + "\n"
                        + "</form>");

                //link insert
                out.print("<p><a href=\"HTML/insertPayments.html\">INSERT PAYMENT</a></p>");

                String sql = "SELECT * FROM Payments";
                String submit = request.getParameter("submit");
                if (submit == null) {
                    sql = "SELECT * FROM Payments";
                } else {
                    String paymethod = request.getParameter("paymethod");
                    sql = "SELECT * FROM Payments WHERE Method_Payment like '%" + paymethod + "%'";
                }
                Vector<Payments> vector = dao.getPayment(sql);

                //table
                out.println("<table border=\"1\">\n"
                        + "  <tr>\n"
                        + "    <th>PayID</th>\n"
                        + "    <th>ShoppingCartID</th>\n"
                        + "    <th>UserID</th>\n"
                        + "    <th>Method_Payment</th>\n"
                        + "    <th>Date_Payment</th>\n"
                        + "    <th>Update</th>\n"
                        + "    <th>Delete</th>\n"
                        + "  </tr>");

                for (Payments payments : vector) {
                    out.println("<tr>\n"
                            + "    <td>" + payments.getPayID() + "</td>\n"
                            + "    <td>" + payments.getShoppingCartID() + "</td>\n"
                            + "    <td>" + payments.getUserID() + "</td>\n"
                            + "    <td>" + payments.getMethod_Payment() + "</td>\n"
                            + "    <td>" + payments.getDate_Payment() + "</td>\n"
                            + "    <td></td>\n"
                            + "   <td><a href=\"PaymentsURL?service=deletePayments&payID=" + payments.getPayID() + "\">Delete</a></td>"
                            + "  </tr>");

                }

                out.println("</table>");
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
