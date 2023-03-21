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
public class OrderDetail {
    
    private int id;
    private int orderId;
    private ArrayList<ItemInCart> listIdProduc;

    public double getTotalAmount(){
        double total = 0;
        for (ItemInCart item : listIdProduc) {
            
        }
        return total;
    }
    
    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public ArrayList<ItemInCart> getListIdProduc() {
        return listIdProduc;
    }

    public void setListIdProduc(ArrayList<ItemInCart> listIdProduc) {
        this.listIdProduc = listIdProduc;
    }
   
    
}
