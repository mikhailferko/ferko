package com.MySklad.ferko.documents;

import com.MySklad.ferko.service.ProductReceipt;
import com.MySklad.ferko.service.ProductSale;

import java.util.List;

public class Sale {

    private int id;
    private String warehouseName;
    private List<ProductSale> listOfProducts;


    public Sale(int id, String warehouseName, List<ProductSale> listOfProducts) {
        this.id = id;
        this.warehouseName = warehouseName;
        this.listOfProducts = listOfProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public List<ProductSale> getListOfProducts() {
        return listOfProducts;
    }

    public void setList(List<ProductSale> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}
