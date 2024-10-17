/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import model.DAOUsers;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserURL"})
public class UserController extends HttpServlet {

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
        DAOUsers dao = new DAOUsers();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String service = request.getParameter("service");
            
            if(service.equals("deleteUsers")){
                dao.deleteUser(Integer.parseInt(request.getParameter("usID")));
                response.sendRedirect("UserURL?service=listAllUsers");
            }

            if (service.equals("insertUsers")) {

                String Name = request.getParameter("Name");
                String Address = request.getParameter("Address");
                String Email = request.getParameter("Email");
                String Phone = request.getParameter("Phone");
                String UserName = request.getParameter("UserName");
                String Password = request.getParameter("Password");
                String Role = request.getParameter("Role");

                if (Name.equals("")) {
                    out.print("User name must not be empty");

                }
                if (Email.equals("")) {
                    out.print("Emailmust not be empty");

                }
                if (UserName.equals("")) {
                    out.print("Username must not be empty");

                }
                if (Password.equals("")) {
                    out.print("Password must not be empty");

                }
                 if (Role.equals("")) {
                    out.print("Role must not be empty");

                }
                 Users us= new Users(Name, Address, Email, Phone, UserName, Password, Role);
                 int n = dao.addUser(us);
                   response.sendRedirect("UserURL?service=listAllUsers");

            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserController</title>");
            out.println("</head>");
            out.println("<body>");

            out.print("""
                         <form action="UserURL" method="get">
                      <p>Search Name: <input type="text" name="uname" id="">
                      <input type="submit" value="Search" name="submit">
                      <input type="reset" value="Clear">
                      <input type="hidden" name="service" value="listAllUsers"> 
                      </p>
                          </form>
                      """);
            out.print("<p><a href=\"HTML/insertUsers.html\">INSERT USER</a></p>");

            String sql = "SELECT * FROM Users";
            String submit = request.getParameter("submit");
            if (submit == null) { //chua nhan submit --> khong search --> sql default
                sql = "SELECT * FROM Users";
            } else {
                String uname = request.getParameter("uname");
                sql = "SELECT * FROM Users where Name like '%" + uname + "%'";
            }
            Vector<Users> vector = dao.getUser(sql);

            out.println("<table border=1>\n"
                    + "        <tr>\n"
                    + "            <th>UserID</th>"
                    + "            <th>Name</th>"
                    + "            <th>Address</th>"
                    + "            <th>Email</th>"
                    + "            <th>Phone</th>"
                    + "            <th>UserName</th>"
                    + "            <th>Password</th>"
                    + "            <th>Role</th>"
                    + "            <th>Update</th>"
                    + "            <th>Delete</th>"
                    + "        </tr>");

            for (Users users : vector) {
                out.print("<tr>\n"
                        + "            <td>" + users.getUserID() + "</td>\n"
                        + "            <td>" + users.getName() + "</td>\n"
                        + "            <td>" + users.getAddress() + "</td>\n"
                        + "            <td>" + users.getEmail() + "</td>\n"
                        + "            <td>" + users.getPhone() + "</td>\n"
                        + "            <td>" + users.getUserName() + "</td>\n"
                        + "            <td>" + users.getPassword() + "</td>\n"
                        + "            <td>" + users.getRole() + "</td>\n"
                        + "            <td></td>\n"
                        + "            <td><a href=\"UserURL?service=deleteUsers&usID="+users.getUserID()+"\">Delete</a></td>\n"
                        + "        </tr>");

            }

            out.print("</table>");
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
