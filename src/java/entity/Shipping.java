/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Shipping {
   
  private int ShippingID;
  private String Name;
  private double Price;

    public Shipping() {
    }

    public Shipping(int ShippingID, String Name, double Price) {
        this.ShippingID = ShippingID;
        this.Name = Name;
        this.Price = Price;
    }

    public Shipping(String Name, double Price) {
        this.Name = Name;
        this.Price = Price;
    }

    public int getShippingID() {
        return ShippingID;
    }

    public void setShippingID(int ShippingID) {
        this.ShippingID = ShippingID;
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
        return "Shipping{" + "ShippingID=" + ShippingID + ", Name=" + Name + ", Price=" + Price + '}';
    }
   
   
   
}
