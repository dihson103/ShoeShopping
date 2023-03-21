/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.Dao;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Cart {

    private ArrayList<ItemInCart> listItems;

    public Cart() {
    }
    
    public Cart(String stringCart, ArrayList<Product> listProduct) {
        listItems = new ArrayList<>();
        try {
            if (stringCart != null && stringCart.length() != 0) {
                String[] s = stringCart.split("\\+");
                for (String string : s) {
                    String[] n = string.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quanity = Integer.parseInt(n[1]);
                    Product p = Dao.getProductById(id);
                    double price = p.getPrice();
                    ItemInCart item = new ItemInCart(p, quanity, price);
                    addItemToList(item);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Cart(String stringCart) {
        listItems = new ArrayList<>();
        try {
            if (stringCart != null && stringCart.length() != 0) {
                String[] s = stringCart.split("\\+");
                for (String string : s) {
                    String[] n = string.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quanity = Integer.parseInt(n[1]);
                    Product p = Dao.getProductById(id);
                    double price = p.getPrice();
                    ItemInCart item = new ItemInCart(p, quanity, price);
                    addItemToList(item);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ItemInCart> getListItems() {
        return listItems;
    }

    public ItemInCart getItemById(int id) {
        for (ItemInCart item : listItems) {
            if (item.getProduct().getId() == id) {
                return item;
            }
        }
        return null;
    }

    public int getQuanityById(int id) {
        return getItemById(id).getNumber();
    }

    public boolean addItemToList(ItemInCart item) {
        for (ItemInCart i : listItems) {
            if (i.getProduct().getId() == item.getProduct().getId()) {
                i.setNumber(i.getNumber() + item.getNumber());
                return true;
            }
        }
        return listItems.add(item);
    }

    public boolean deleteItem(int id) {
        if (getItemById(id) != null) {
            return listItems.remove(getItemById(id));
        }
        return false;
    }

    public double getTotalAmount() {
        double total = 0;
        for (ItemInCart item : listItems) {
            total += item.getPriceWhenSell() * item.getNumber();
        }
        return total;
    }
    
    public int getNumberItemInCart(){
        return listItems.size();
    }
}
