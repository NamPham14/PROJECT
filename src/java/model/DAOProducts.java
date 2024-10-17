/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Products;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Stack;
import java.util.Vector;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class DAOProducts extends DBConnection {

    public int insertProduct(Products pro) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([Description]\n"
                + "           ,[Name]\n"
                + "           ,[Price]\n"
                + "           ,[Quantity]\n"
                + "           ,[CategoryID])\n"
                + "     VALUES\n"
                + "           ('" + pro.getDescription() + "','" + pro.getName() + "'," + pro.getPrice() + "," + pro.getQuantity() + "," + pro.getCategoryID() + ");";
        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addProduct(Products pro) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([Description]\n"
                + "           ,[Name]\n"
                + "           ,[Price]\n"
                + "           ,[Quantity]\n"
                + "           ,[CategoryID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?);";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getDescription());
            pre.setString(2, pro.getName());
            pre.setDouble(3, pro.getPrice());
            pre.setInt(4, pro.getQuantity());
            pre.setInt(5, pro.getCategoryID());
            n = pre.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteProduct(int proID) {
        int n = 0;

        String sql = "DELETE FROM [dbo].[Products] WHERE ProductID=" + proID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateProduct(Products pro) {
        int n = 0;

        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [Description] = ?\n"
                + "      ,[Name] = ?\n"
                + "      ,[Price] = ?\n"
                + "      ,[Quantity] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      \n"
                + " WHERE ProductID =?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getDescription());
            pre.setString(2, pro.getName());
            pre.setDouble(3, pro.getPrice());
            pre.setInt(4, pro.getQuantity());
            pre.setInt(5, pro.getCategoryID());
            pre.setInt(6, pro.getProductID());
            
            n= pre.executeUpdate();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }

        return n;

    }
    
    public Vector<Products> getProduct(String sql){
        Vector<Products> vector = new Vector<Products>();
        
        try {
            Statement state = conn.createStatement();
            ResultSet rs=state.executeQuery(sql);
            if(rs.next()){
               int Quantity = rs.getInt("Quantity");
               int CategoryID = rs.getInt("CategoryID");
               String Description = rs.getString("Description");
               String Name = rs.getString("Name");
               double Price= rs.getDouble("Price");
               int ProductID = rs.getInt("ProductID");
               
               Products pro = new Products(ProductID, Quantity, CategoryID, Description, Name, Price);
               vector.add(pro);
               
            }
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        
        return vector;
    }
    public static void main(String[] args) {
        DAOProducts dao = new DAOProducts();
        
        int x = dao.addProduct(new Products(10, 2, "GOOD", "Peri", 500));
        if(x>0){
            System.out.println("inserted");
        }
    }

}
