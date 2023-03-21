/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Order {
    
    private int id;
    private int userId;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String note;
    private String orderDate;
    private String status;
    private ArrayList<ItemInCart> listIdItems;

    public Order() {
    }

    public Order(int id, int userId, String fullName, String email, String phone, String address, String note, String orderDate, String status, ArrayList<ItemInCart> listIdProduct) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.orderDate = orderDate;
        this.status = status;
        this.listIdItems = listIdProduct;
    }
    
    

    public ArrayList<ItemInCart> getListIdItems() {
        return listIdItems;
    }

    public void setListIdItems(ArrayList<ItemInCart> listIdItems) {
        this.listIdItems = listIdItems;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getTotalAmount(){
        double total = 0;
        for (ItemInCart item : listIdItems) {
            total += item.getNumber()*item.getPriceWhenSell();
        }
        return total;
    }
    
}
