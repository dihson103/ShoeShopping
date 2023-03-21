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
public class ListChoose {
    private ArrayList<ItemInCart> listChoose;

    public ListChoose(ArrayList<ItemInCart> listChoose) {
        this.listChoose = listChoose;
    }

    public ArrayList<ItemInCart> getListChoose() {
        return listChoose;
    }
    
    public double getTotalAmount(){
        double total = 0;
        for (ItemInCart item : listChoose) {
            total += item.getNumber()*item.getPriceWhenSell();
        }
        return total;
    }
}
