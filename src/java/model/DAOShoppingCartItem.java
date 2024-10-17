/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.shoppingCartItems;
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
public class DAOShoppingCartItem extends DBConnection {

    public int insertCartItem(shoppingCartItems cari) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Shopping_Cart_Items]\n"
                + "           ([Quantity]\n"
                + "           ,[ProductID]\n"
                + "           ,[Shipping_CartID])\n"
                + "     VALUES(" + cari.getQuantity() + "," + cari.getProductID() + "," + cari.getShipping_CartID() + ")";
        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShoppingCartItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addCartItem(shoppingCartItems cari) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Shopping_Cart_Items]\n"
                + "           ([Quantity]\n"
                + "           ,[ProductID]\n"
                + "           ,[Shipping_CartID])\n"
                + "     VALUES(?,?,?)";

        try {
            PreparedStatement pre = conn.prepareCall(sql);
            pre.setInt(1, cari.getQuantity());
            pre.setInt(2, cari.getProductID());
            pre.setInt(3, cari.getShipping_CartID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteCartItem(int cariID) {
        int n = 0;

        String sql = "DELETE FROM [dbo].[Shopping_Cart_Items] WHERE Cart_ItemID=" + cariID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShoppingCartItem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateCartItem(shoppingCartItems cari) {
        int n = 0;

        String sql = "UPDATE [dbo].[Shopping_Cart_Items]\n"
                + "   SET [Quantity] = ?\n"
                + "      ,[ProductID] = ?\n"
                + "      ,[Shipping_CartID] = ?\n"
                + " WHERE Cart_ItemID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cari.getQuantity());
            pre.setInt(2, cari.getProductID());
            pre.setInt(3, cari.getShipping_CartID());
            pre.setInt(4, cari.getCart_ItemID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }

    public Vector<shoppingCartItems> getCartItem(String sql) {
        Vector<shoppingCartItems> vector = new Vector<shoppingCartItems>();

        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int Cart_ItemID = rs.getInt("Cart_ItemID");
                int Quantity = rs.getInt("Quantity");
                int ProductID = rs.getInt("ProductID");
                int Shipping_CartID = rs.getInt("Shipping_CartID");
                
                shoppingCartItems cari = new shoppingCartItems(Cart_ItemID, Quantity, ProductID, Shipping_CartID);
                vector.add(cari);
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOShoppingCartItem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }
    public static void main(String[] args) {
        DAOShoppingCartItem dao = new DAOShoppingCartItem();
        int x = dao.addCartItem(new shoppingCartItems(4, 3, 2));
        if(x >0){
            System.out.println("added");
        }
    }

}
