package com.MySklad.ferko.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Component
public class Product {

    private int id;
    @NotNull
    private int article;
    @NotEmpty
    private String name;
    private double lastBuyPrice;
    private double lastSalePrice;
    private int balance;

    public Product(int id, int article, String name, double lastBuyPrice, double lastSalePrice) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.lastBuyPrice = lastBuyPrice;
        this.lastSalePrice = lastSalePrice;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLastBuyPrice() {
        return lastBuyPrice;
    }

    public void setLastBuyPrice(double lastBuyPrice) {
        this.lastBuyPrice = lastBuyPrice;
    }

    public double getLastSalePrice() {
        return lastSalePrice;
    }

    public void setLastSalePrice(double lastSalePrice) {
        this.lastSalePrice = lastSalePrice;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

}
