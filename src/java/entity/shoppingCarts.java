/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class shoppingCarts {
 
   private int Shipping_CartID,TransactionID_User,TransactionID_Merchant,
            OrderID,UserID;

    public shoppingCarts() {
    }

    public shoppingCarts(int TransactionID_User, int TransactionID_Merchant, int OrderID, int UserID) {
        this.TransactionID_User = TransactionID_User;
        this.TransactionID_Merchant = TransactionID_Merchant;
        this.OrderID = OrderID;
        this.UserID = UserID;
    }

    public shoppingCarts(int Shipping_CartID, int TransactionID_User, int TransactionID_Merchant, int OrderID, int UserID) {
        this.Shipping_CartID = Shipping_CartID;
        this.TransactionID_User = TransactionID_User;
        this.TransactionID_Merchant = TransactionID_Merchant;
        this.OrderID = OrderID;
        this.UserID = UserID;
    }

    public int getShipping_CartID() {
        return Shipping_CartID;
    }

    public void setShipping_CartID(int Shipping_CartID) {
        this.Shipping_CartID = Shipping_CartID;
    }

    public int getTransactionID_User() {
        return TransactionID_User;
    }

    public void setTransactionID_User(int TransactionID_User) {
        this.TransactionID_User = TransactionID_User;
    }

    public int getTransactionID_Merchant() {
        return TransactionID_Merchant;
    }

    public void setTransactionID_Merchant(int TransactionID_Merchant) {
        this.TransactionID_Merchant = TransactionID_Merchant;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    @Override
    public String toString() {
        return "shoppingCarts{" + "Shipping_CartID=" + Shipping_CartID + ", TransactionID_User=" + TransactionID_User + ", TransactionID_Merchant=" + TransactionID_Merchant + ", OrderID=" + OrderID + ", UserID=" + UserID + '}';
    }
    
}
