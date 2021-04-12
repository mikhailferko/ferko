package com.MySklad.ferko.documents;

import com.MySklad.ferko.service.ProductMoving;
import com.MySklad.ferko.service.ProductReceipt;

import java.util.List;
import java.util.Map;

public class Moving {

    private int id;
    private String warehouseNameFrom;
    private String warehouseNameTo;
    private List<ProductMoving> listOfProducts;

    public Moving(int id, String warehouseNameFrom, String warehouseNameTo, List<ProductMoving> listOfProducts) {
        this.id = id;
        this.warehouseNameFrom = warehouseNameFrom;
        this.warehouseNameTo = warehouseNameTo;
        this.listOfProducts = listOfProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseNameFrom() {
        return warehouseNameFrom;
    }

    public void setWarehouseNameFrom(String warehouseNameFrom) {
        this.warehouseNameFrom = warehouseNameFrom;
    }

    public String getWarehouseNameTo() {
        return warehouseNameTo;
    }

    public void setWarehouseNameTo(String warehouseNameTo) {
        this.warehouseNameTo = warehouseNameTo;
    }

    public List<ProductMoving> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<ProductMoving> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

}
