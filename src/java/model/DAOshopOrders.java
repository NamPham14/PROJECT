/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.shopOrders;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DAOshopOrders extends DBConnection {

    public int insertShopOrder(shopOrders so) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shop_Orders]\n"
                + "           ([Date_Order]\n"
                + "           ,[Total_Price]\n"
                + "           ,[Address_Shipping]\n"
                + "           ,[ShippingID]\n"
                + "           ,[UserID])\n"
                + "     VALUES('" + so.getDate_Order() + "'," + so.getTotal_Price() + ",'" + so.getAddress_Shipping() + "'," + so.getShippingID() + "," + so.getUserID() + ")\n";
        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOshopOrders.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addShopOrder(shopOrders so) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shop_Orders]\n"
                + "           ([Date_Order]\n"
                + "           ,[Total_Price]\n"
                + "           ,[Address_Shipping]\n"
                + "           ,[ShippingID]\n"
                + "           ,[UserID])\n"
                + "     VALUES(?,?,?,?,?)\n";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, so.getDate_Order());
            pre.setDouble(2, so.getTotal_Price());
            pre.setString(3, so.getAddress_Shipping());
            pre.setInt(4, so.getShippingID());
            pre.setInt(5, so.getUserID());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteShopOrder(int soID) {
        int n = 0;

        String sql = "DELETE FROM [dbo].[Shop_Orders] WHERE Shop_OrderID=" + soID;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOshopOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateShopOrder(shopOrders so) {
        int n = 0;

        String sql = "UPDATE [dbo].[Shop_Orders]\n"
                + "   SET [Date_Order] = ?\n"
                + "      ,[Total_Price] = ?\n"
                + "      ,[Address_Shipping] = ?\n"
                + "      ,[ShippingID] = ?\n"
                + "      ,[UserID] = ?\n"
                + " WHERE Shop_OrderID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, so.getDate_Order());
            pre.setDouble(2, so.getTotal_Price());
            pre.setString(3, so.getAddress_Shipping());
            pre.setInt(4, so.getShippingID());
            pre.setInt(5, so.getUserID());
            pre.setInt(6, so.getShop_OrderID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }

    public Vector<shopOrders> getShopOrder(String sql) {
        Vector<shopOrders> vector = new Vector<shopOrders>();

        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int ShippingID = rs.getInt("ShippingID");
                int UserID = rs.getInt("UserID");
                String Date_Order = rs.getString("Date_Order");
                String Address_Shipping = rs.getString("Address_Shipping");
                double Total_Price = rs.getDouble("Total_Price");
                int Shop_OrderID = rs.getInt("Shop_OrderID");
                
                shopOrders so = new shopOrders(Shop_OrderID, ShippingID, UserID, Date_Order, Address_Shipping, Total_Price);
                vector.add(so);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOshopOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOshopOrders dao = new DAOshopOrders();
        
        int x = dao.insertShopOrder(new shopOrders(1, 3, "2024-10-10", "HOA LAC", 1000));
        if(x>0){
            System.out.println("inserted");
        }
    }

}
