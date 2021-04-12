package com.MySklad.ferko.service;

public class ProductSale {

    private int article;
    private int quantity;
    private double price;



    public ProductSale(int article, int quantity, double price) {
        this.article = article;
        this.quantity = quantity;
        this.price = price;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
