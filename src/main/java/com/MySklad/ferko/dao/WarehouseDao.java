package com.MySklad.ferko.dao;

import com.MySklad.ferko.exceptions.NotFoundException;
import com.MySklad.ferko.models.Product;
import com.MySklad.ferko.models.Warehouse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class WarehouseDao {

    List<Warehouse> warehouses = new ArrayList<>();
    private int counter;

    {
        warehouses.add(new Warehouse(++counter, "Sklad1", new HashMap<>()));
        warehouses.add(new Warehouse(++counter, "Sklad2", new HashMap<>()));
        warehouses.add(new Warehouse(++counter, "Sklad3", new HashMap<>()));
        warehouses.add(new Warehouse(++counter, "Sklad4", new HashMap<>()));
    }

    public List<Warehouse> getList(){
        return warehouses;
    }

    public Warehouse getOne(int id){
        return getById(id);
    }

    private Warehouse getById(int id){
        return warehouses.stream().filter(warehouses -> warehouses.getId() == id)
                .findAny().orElseThrow(NotFoundException::new);
    }

    public List<Warehouse> getByName(String name){
        List<Warehouse> list = new ArrayList<>();
        for(Warehouse warehouse : warehouses){
            if (warehouse.getName().equals(name))
                list.add(warehouse);
        }
        return list;
    }

    public void post(Warehouse warehouse){
        warehouse.setId(++counter);
        warehouses.add(warehouse);
    }

    public Warehouse update(Warehouse warehouse, int id){
        Warehouse warehousefromDB = getById(id);
        warehousefromDB.setId(id);
        warehousefromDB.setName(warehouse.getName());
        warehousefromDB.setMapOfProduct(warehouse.getMapOfProduct());
        return warehousefromDB;
    }

    public void delete(int id){
        warehouses.remove(getById(id));
    }

}
