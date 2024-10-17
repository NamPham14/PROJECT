/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.Vector;
import java.sql.ResultSet;

/**
 * @author Admin
 */
public class DAOCategories extends DBConnection {

    public int insertCategory(Categories cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([Category_Name])\n"
                + "     VALUES\n"
                + "         ('" + cate.getCategory_Name() + "');";

        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public int addCategory(Categories cate) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([Category_Name])\n"
                + "     VALUES (?)";
                

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategory_Name());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;

    }

    public int deleteCategory(int cateID) {
        int n = 0;

        String sql = "DELETE FROM Categories WHERE CategoryID=" + cateID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;

    }

    public int updateCategory(Categories cate) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [Category_Name] = ?\n"
                + " WHERE CategoryID =?;";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cate.getCategory_Name());
            pre.setInt(2, cate.getCategoryID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        

        return n;

    }
    
    public Vector<Categories> getCategory(String sql){
        Vector<Categories> vector = new Vector<Categories>();
        
       
        try {
            Statement state = conn.createStatement( ResultSet.TYPE_SCROLL_SENSITIVE , ResultSet.CONCUR_UPDATABLE);
            ResultSet rs= state.executeQuery(sql);
            while (rs.next()) {
              int CategoryID =rs.getInt("CategoryID");
              String Category_Name= rs.getString("Category_Name");
              Categories cate =new Categories(CategoryID, Category_Name);
              vector.add(cate);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCategories.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return vector;
    }
    public static void main(String[] args) {
        DAOCategories dao = new DAOCategories();
        
        //insert 
//        int x = dao.insertCategory(new Categories("Peri1"));
//        if(x >0){
//            System.out.println("inserted");
//        }
       
        //add
         int x = dao.addCategory(new Categories("Peri3"));
        if(x >0){
            System.out.println("added");
        }

    //update
//    int x = dao.updateCategory(new Categories(7, "new Peri"));
//     if(x >0){
//          System.out.println("updated");
//       }


//    Vector<Categories> vector = dao.getCategory("Select * from Categories");
//        for (Categories categories : vector) {
//            System.out.println(categories);
//            
//        }

    }

}
