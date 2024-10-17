/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Users {
  
   private int UserID;
   private String Name,Address,Email,Phone,UserName,Password,Role;

    public Users() {
    }

    public Users(int UserID, String Name, String Address, String Email, String Phone, String UserName, String Password, String Role) {
        this.UserID = UserID;
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Phone = Phone;
        this.UserName = UserName;
        this.Password = Password;
        this.Role = Role;
    }
    

    public Users(String Name, String Address, String Email, String Phone, String UserName, String Password, String Role) {
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Phone = Phone;
        this.UserName = UserName;
        this.Password = Password;
        this.Role = Role;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "Users{" + "UserID=" + UserID + ", Name=" + Name + ", Address=" + Address + ", Email=" + Email + ", Phone=" + Phone + ", UserName=" + UserName + ", Password=" + Password + ", Role=" + Role + '}';
    }
    
}
