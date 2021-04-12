package com.MySklad.ferko.models;

import java.util.Map;

public class Warehouse {

    private int id;
    private String name;
    private Map<Integer, Integer> mapOfProduct;

    public Warehouse(int id, String name, Map<Integer, Integer> mapOfProduct) {
        this.id = id;
        this.name = name;
        this.mapOfProduct = mapOfProduct;
    }

    public Warehouse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getMapOfProduct() {
        return mapOfProduct;
    }

    public void setMapOfProduct(Map<Integer, Integer> mapOfProduct) {
        this.mapOfProduct = mapOfProduct;
    }
}
