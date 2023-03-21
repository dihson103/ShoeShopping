/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class ItemInCart {
    private Product product;
    private int number;
    private double priceWhenSell;
    private boolean isChoose = false;

    public ItemInCart() {
    }

    public ItemInCart(Product product, int number, double priceWhenSell) {
        this.product = product;
        this.number = number;
        this.priceWhenSell = priceWhenSell;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPriceWhenSell() {
        return priceWhenSell;
    }

    public void setPriceWhenSell(double priceWhenSell) {
        this.priceWhenSell = priceWhenSell;
    }

    public boolean isIsChoose() {
        return isChoose;
    }

    public void setIsChoose(boolean isChoose) {
        this.isChoose = isChoose;
    }
    
    
    
}
