/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Shipping;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class DAOShipping extends DBConnection {

    public int insertShipping(Shipping ship) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shipping]\n"
                + "           ([Name]\n"
                + "           ,[Price])\n"
                + "     VALUES('" + ship.getName() + "'," + ship.getPrice() + ")";

        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipping.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addShipping(Shipping ship) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Shipping]\n"
                + "           ([Name]\n"
                + "           ,[Price])\n"
                + "     VALUES(?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ship.getName());
            pre.setDouble(2, ship.getPrice());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteShipping(int shipID) {
        int n = 0;

        String sql = "DELETE FROM [dbo].[Shipping] WHERE ShippingID=" + shipID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOShipping.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateProduct(Shipping ship) {
        int n = 0;

        String sql = "UPDATE [dbo].[Shipping]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Price] =?\n"
                + " WHERE ShippingID=?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, ship.getName());
            pre.setDouble(2, ship.getPrice());
            pre.setInt(3, ship.getShippingID());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public Vector<Shipping> getShipping(String sql) {
        Vector<Shipping> vector = new Vector<Shipping>();

        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {  
                int ShippingID = rs.getInt("ShippingID");
                String Name = rs.getString("Name");
                double Price = rs.getDouble("Price");
                
                Shipping ship = new Shipping(ShippingID, Name, Price);
                vector.add(ship);
                
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
    return vector;
    }
    
    public static void main(String[] args) {
        DAOShipping dao = new DAOShipping();
        
        int n = dao.insertShipping(new Shipping("NP", 600));
        if(n>0){
            System.out.println("inserted");
        }
        
        
    }

}
