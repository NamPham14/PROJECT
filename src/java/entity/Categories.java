/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Categories {
    
   private int CategoryID;
   private String Category_Name;

    public Categories() {
    }

    public Categories(String Category_Name) {
        this.Category_Name = Category_Name;
    }
    

    public Categories(int CategoryID, String Category_Name) {
        this.CategoryID = CategoryID;
        this.Category_Name = Category_Name;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String Category_Name) {
        this.Category_Name = Category_Name;
    }

    @Override
    public String toString() {
        return "Categories{" + "CategoryID=" + CategoryID + ", Category_Name=" + Category_Name + '}';
    }
    
    
}
