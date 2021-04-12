package com.MySklad.ferko.service;

public class ProductMoving {

    private int article;
    private int quantity;

    public ProductMoving(int article, int quantity) {
        this.article = article;
        this.quantity = quantity;
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
}
