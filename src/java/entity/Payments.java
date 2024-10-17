/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Payments {

   private int PayID, ShoppingCartID, UserID;
   private String Method_Payment, Date_Payment;

    public Payments() {
    }

    public Payments(int PayID, int ShoppingCartID, int UserID, String Method_Payment, String Date_Payment) {
        this.PayID = PayID;
        this.ShoppingCartID = ShoppingCartID;
        this.UserID = UserID;
        this.Method_Payment = Method_Payment;
        this.Date_Payment = Date_Payment;
    }
    

    public Payments(int ShoppingCartID, int UserID, String Method_Payment, String Date_Payment) {
        this.ShoppingCartID = ShoppingCartID;
        this.UserID = UserID;
        this.Method_Payment = Method_Payment;
        this.Date_Payment = Date_Payment;
    }

    public int getPayID() {
        return PayID;
    }

    public void setPayID(int PayID) {
        this.PayID = PayID;
    }

    public int getShoppingCartID() {
        return ShoppingCartID;
    }

    public void setShoppingCartID(int ShoppingCartID) {
        this.ShoppingCartID = ShoppingCartID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getMethod_Payment() {
        return Method_Payment;
    }

    public void setMethod_Payment(String Method_Payment) {
        this.Method_Payment = Method_Payment;
    }

    public String getDate_Payment() {
        return Date_Payment;
    }

    public void setDate_Payment(String Date_Payment) {
        this.Date_Payment = Date_Payment;
    }

    @Override
    public String toString() {
        return "Payments{" + "PayID=" + PayID + ", ShoppingCartID=" + ShoppingCartID + ", UserID=" + UserID + ", Method_Payment=" + Method_Payment + ", Date_Payment=" + Date_Payment + '}';
    }

}
