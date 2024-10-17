/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class shoppingCartItems {

    private int Cart_ItemID ,Quantity,ProductID,Shipping_CartID;

    public shoppingCartItems() {
    }

    public shoppingCartItems(int Cart_ItemID, int Quantity, int ProductID, int Shipping_CartID) {
        this.Cart_ItemID = Cart_ItemID;
        this.Quantity = Quantity;
        this.ProductID = ProductID;
        this.Shipping_CartID = Shipping_CartID;
    }

    public shoppingCartItems(int Quantity, int ProductID, int Shipping_CartID) {
        this.Quantity = Quantity;
        this.ProductID = ProductID;
        this.Shipping_CartID = Shipping_CartID;
    }

    public int getCart_ItemID() {
        return Cart_ItemID;
    }

    public void setCart_ItemID(int Cart_ItemID) {
        this.Cart_ItemID = Cart_ItemID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getShipping_CartID() {
        return Shipping_CartID;
    }

    public void setShipping_CartID(int Shipping_CartID) {
        this.Shipping_CartID = Shipping_CartID;
    }

    @Override
    public String toString() {
        return "shoppingCartItems{" + "Cart_ItemID=" + Cart_ItemID + ", Quantity=" + Quantity + ", ProductID=" + ProductID + ", Shipping_CartID=" + Shipping_CartID + '}';
    }
     
     
}
