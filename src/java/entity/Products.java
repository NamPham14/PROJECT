/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Products {

    private int ProductID, Quantity, CategoryID;
    private String Description, Name;
    private double Price;

    public Products() {
    }

    public Products(int ProductID, int Quantity, int CategoryID, String Description, String Name, double Price) {
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.CategoryID = CategoryID;
        this.Description = Description;
        this.Name = Name;
        this.Price = Price;
    }
    

    public Products(int Quantity, int CategoryID, String Description, String Name, double Price) {
        this.Quantity = Quantity;
        this.CategoryID = CategoryID;
        this.Description = Description;
        this.Name = Name;
        this.Price = Price;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    @Override
    public String toString() {
        return "Products{" + "ProductID=" + ProductID + ", Quantity=" + Quantity + ", CategoryID=" + CategoryID + ", Description=" + Description + ", Name=" + Name + ", Price=" + Price + '}';
    }

}
