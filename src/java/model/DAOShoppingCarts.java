/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.shoppingCarts;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAOShoppingCarts extends DBConnection {

    public int insertCart(shoppingCarts car) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shipping_Carts]\n"
                + "           ([TransactionID_User]\n"
                + "           ,[TransactionID_Merchant]\n"
                + "           ,[OrderID]\n"
                + "           ,[UserID])\n"
                + "     VALUES(" + car.getTransactionID_User() + "," + car.getTransactionID_Merchant() + "," + car.getOrderID() + "," + car.getUserID() + ")";
        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShoppingCarts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addCart(shoppingCarts car) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shipping_Carts]\n"
                + "           ([TransactionID_User]\n"
                + "           ,[TransactionID_Merchant]\n"
                + "           ,[OrderID]\n"
                + "           ,[UserID])\n"
                + "     VALUES(?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, car.getTransactionID_User());
            pre.setInt(2, car.getTransactionID_Merchant());
            pre.setInt(3, car.getOrderID());
            pre.setInt(4, car.getUserID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteCart(int carID) {
        int n = 0;
        String sql = "DELETE FROM [dbo].[Shipping_Carts] WHERE Shipping_CartID=" + carID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShoppingCarts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCart(shoppingCarts car) {
        int n = 0;
        String sql = "UPDATE [dbo].[Shipping_Carts]\n"
                + "   SET [TransactionID_User] = ?\n"
                + "      ,[TransactionID_Merchant] = ?\n"
                + "      ,[OrderID] = ?\n"
                + "      ,[UserID] = ?\n"
                + " WHERE Shipping_CartID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, car.getTransactionID_User());
            pre.setInt(2, car.getTransactionID_Merchant());
            pre.setInt(3, car.getOrderID());
            pre.setInt(4, car.getUserID());
            pre.setInt(5, car.getShipping_CartID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }
    public Vector<shoppingCarts> getCart(String sql){
        Vector<shoppingCarts> vector= new Vector<shoppingCarts>();
        
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
             int  TransactionID_User = rs.getInt("TransactionID_User");
             int  TransactionID_Merchant = rs.getInt("TransactionID_Merchant");
             int  OrderID = rs.getInt("OrderID");
             int  UserID = rs.getInt("UserID");
             int  Shipping_CartID = rs.getInt("Shipping_CartID");
             
            shoppingCarts car = new shoppingCarts(Shipping_CartID, TransactionID_User, TransactionID_Merchant, OrderID, UserID);
            vector.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShoppingCarts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    public static void main(String[] args) {
        DAOShoppingCarts dao = new DAOShoppingCarts();
        
        int n = dao.addCart(new shoppingCarts(2, 4, 4, 3));
        if (n>0) {
            System.out.println("inserted");
            
        }
    }

}
