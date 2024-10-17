/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Payments;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAOPayments extends DBConnection {

    public int insertPayment(Payments pay) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Payments]\n"
                + "           ([Date_Payment]\n"
                + "           ,[Method_Payment]\n"
                + "           ,[ShoppingCartID]\n"
                + "           ,[UserID])\n"
                + "     VALUES ('" + pay.getDate_Payment() + "','" + pay.getMethod_Payment() + "'," + pay.getShoppingCartID() + "," + pay.getUserID() + ");";

        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPayments.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addPayment(Payments pay) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Payments]\n"
                + "           ([Date_Payment]\n"
                + "           ,[Method_Payment]\n"
                + "           ,[ShoppingCartID]\n"
                + "           ,[UserID])\n"
                + "     VALUES (?,?,?,?);";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pay.getDate_Payment());
            pre.setString(2, pay.getMethod_Payment());
            pre.setInt(3, pay.getShoppingCartID());
            pre.setInt(4, pay.getUserID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deletePayment(int payID) {
        int n = 0;

        String sql = "DELETE FROM [dbo].[Payments] WHERE PayID=" + payID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOPayments.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updatePayment(Payments pay) {
        int n = 0;
        String sql = "UPDATE [dbo].[Payments]\n"
                + "   SET [Date_Payment] =?\n"
                + "      ,[Method_Payment] = ?\n"
                + "      ,[ShoppingCartID] = ?\n"
                + "      ,[UserID] = ?\n"
                + " WHERE PayID =?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pay.getDate_Payment());
            pre.setString(2, pay.getMethod_Payment());
            pre.setInt(3, pay.getShoppingCartID());
            pre.setInt(4, pay.getUserID());
            pre.setInt(5, pay.getPayID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        }

        return n;

    }
    
    public Vector<Payments> getPayment (String sql){
        Vector<Payments> vector = new Vector<Payments>();
        
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
             int PayID = rs.getInt("PayID");
             int ShoppingCartID = rs.getInt("ShoppingCartID");
             int UserID= rs.getInt("UserID");
             String Method_Payment =rs.getString("Method_Payment");
             String Date_Payment =rs.getString("Date_Payment");
             
             Payments pay = new Payments(PayID, ShoppingCartID, UserID, Method_Payment, Date_Payment);
             vector.add(pay);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return  vector;
    }
    
    public static void main(String[] args) {
        DAOPayments dao = new DAOPayments();
//        
//        int n = dao.insertPayment(new Payments(4, 3,"credit_card", "2024-08-10"));
//        if(n>0){
//            System.out.println("inserted");
//        }

  int n = dao.addPayment(new Payments(7, 2,"credit_card", "2024-08-10"));
        if(n>0){
            System.out.println("added");
        }


    }

}
