package com.MySklad.ferko.documents;

import com.MySklad.ferko.service.ProductReceipt;

import java.util.List;

public class Receipt {
    private int id;
    private String warehouseName;
    private List<ProductReceipt> listOfProducts;


    public Receipt(int id, String warehouseName, List<ProductReceipt> listOfProducts) {
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

    public List<ProductReceipt> getListOfProducts() {
        return listOfProducts;
    }

    public void setList(List<ProductReceipt> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
}
