/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author Admin
 */
public class DBConnection {
    public Connection conn = null;
    public DBConnection(String URL, String userName , String password) {
      
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn= DriverManager.getConnection(URL,userName,password);
            System.out.println("connected");
            
        } 
        catch (ClassNotFoundException ex) {
           ex.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
            
        }
    }
    public  DBConnection()  {
        this("jdbc:sqlserver://localhost:1433;databaseName=SHOP_BILLIARD","sa","123456");
        
    }
    public ResultSet getData(String sql){
        ResultSet rs = null;
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
          ex.printStackTrace(); 
          
        }
        return rs;
    }
    public static void main(String[] args)  {
        DBConnection db = new DBConnection();
        if(db.conn !=null){
            System.out.println("1");
        }
    }
    
    
}
