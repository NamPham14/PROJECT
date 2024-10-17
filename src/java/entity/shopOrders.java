/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class shopOrders {
               
   private int Shop_OrderID,ShippingID,UserID;
   private String Date_Order,Address_Shipping;
   private double Total_Price;

    public shopOrders() {
    }

    public shopOrders(int Shop_OrderID, int ShippingID, int UserID, String Date_Order, String Address_Shipping, double Total_Price) {
        this.Shop_OrderID = Shop_OrderID;
        this.ShippingID = ShippingID;
        this.UserID = UserID;
        this.Date_Order = Date_Order;
        this.Address_Shipping = Address_Shipping;
        this.Total_Price = Total_Price;
    }
    
    

    public shopOrders(int ShippingID, int UserID, String Date_Order, String Address_Shipping, double Total_Price) {
        this.ShippingID = ShippingID;
        this.UserID = UserID;
        this.Date_Order = Date_Order;
        this.Address_Shipping = Address_Shipping;
        this.Total_Price = Total_Price;
    }

    public int getShop_OrderID() {
        return Shop_OrderID;
    }

    public void setShop_OrderID(int Shop_OrderID) {
        this.Shop_OrderID = Shop_OrderID;
    }

    public int getShippingID() {
        return ShippingID;
    }

    public void setShippingID(int ShippingID) {
        this.ShippingID = ShippingID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getDate_Order() {
        return Date_Order;
    }

    public void setDate_Order(String Date_Order) {
        this.Date_Order = Date_Order;
    }

    public String getAddress_Shipping() {
        return Address_Shipping;
    }

    public void setAddress_Shipping(String Address_Shipping) {
        this.Address_Shipping = Address_Shipping;
    }

    public double getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(double Total_Price) {
        this.Total_Price = Total_Price;
    }

    @Override
    public String toString() {
        return "shopOrders{" + "Shop_OrderID=" + Shop_OrderID + ", ShippingID=" + ShippingID + ", UserID=" + UserID + ", Date_Order=" + Date_Order + ", Address_Shipping=" + Address_Shipping + ", Total_Price=" + Total_Price + '}';
    }
    
    
    
}
