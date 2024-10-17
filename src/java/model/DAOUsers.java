/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Users;
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
public class DAOUsers extends DBConnection {

    public int insertUser(Users us) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Name]\n"
                + "           ,[Address]\n"
                + "           ,[Email]\n"
                + "           ,[Phone]\n"
                + "           ,[UserName]\n"
                + "           ,[Password]\n"
                + "           ,[Role])\n"
                + "     VALUES('" + us.getName() + "','" + us.getAddress() + "','" + us.getEmail() + "','" + us.getPhone() + "','" + us.getUserName() + "','" + us.getPassword() + "','" + us.getRole() + "')";

        System.out.println(sql);

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int addUser(Users us) {
        int n = 0;

        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([Name]\n"
                + "           ,[Address]\n"
                + "           ,[Email]\n"
                + "           ,[Phone]\n"
                + "           ,[UserName]\n"
                + "           ,[Password]\n"
                + "           ,[Role])\n"
                + "     VALUES(?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareCall(sql);
            pre.setString(1, us.getName());
            pre.setString(2, us.getAddress());
            pre.setString(3, us.getEmail());
            pre.setString(4, us.getPhone());
            pre.setString(5, us.getUserName());
            pre.setString(6, us.getPassword());
            pre.setString(7, us.getRole());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }

    public int deleteUser(int usID) {
        int n = 0;

        String sql = "DELETE FROM [dbo].[Users] WHERE UserID=" + usID;

        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return n;
    }

    public int updateUser(Users us) {
        int n = 0;

        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Address] = ?\n"
                + "      ,[Email] = ?\n"
                + "      ,[Phone] = ?\n"
                + "      ,[UserName] = ?\n"
                + "      ,[Password] =?\n"
                + "      ,[Role] = ?\n"
                + " WHERE UserID =?";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, us.getName());
            pre.setString(2, us.getAddress());
            pre.setString(3, us.getEmail());
            pre.setString(4, us.getPhone());
            pre.setString(5, us.getUserName());
            pre.setString(6, us.getPassword());
            pre.setString(7, us.getRole());
            pre.setInt(8, us.getUserID());
            n=pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return n;
    }
    
    public Vector<Users> getUser(String sql){
        Vector<Users> vector = new Vector<Users>();
        
        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
             String Name = rs.getString("Name");
             String Address =rs.getString("Address");
             String Email =rs.getString("Email");
             String Phone =rs.getString("Phone");
             String UserName =rs.getString("UserName");
             String Password =rs.getString("Password");
             String Role =rs.getString("Role");
             int UserID = rs.getInt("UserID");
             
             Users us = new Users(UserID, Name, Address, Email, Phone, UserName, Password, Role);
             vector.add(us);
             
  
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOUsers dao = new DAOUsers();
        
        int n = dao.insertUser(new Users("NAM", "HN", "nampv@gmail.com", "0868202662", "nampham14", "123", "admin"));
         if(n>0){
            System.out.println("inserted");
        }
    }
    

}
