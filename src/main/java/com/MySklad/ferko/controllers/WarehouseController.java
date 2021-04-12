package com.MySklad.ferko.controllers;

import com.MySklad.ferko.dao.WarehouseDao;
import com.MySklad.ferko.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("warehouse")
public class WarehouseController {

    private WarehouseDao warehouseDao;

    @Autowired
    public WarehouseController(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    @GetMapping
    public List<Warehouse> getWarehousesList(){
        return warehouseDao.getList();
    }

    @GetMapping("{id}")
    public Warehouse getOneWarehouse(@PathVariable int id){
        return warehouseDao.getOne(id);
    }


    @PostMapping
    public void postWaresouse(@RequestBody Warehouse warehouse){
        warehouseDao.post(warehouse);
    }

    @PutMapping("{id}")
    public Warehouse updateWarehouse(@RequestBody Warehouse warehouse, @PathVariable int id){
        return warehouseDao.update(warehouse, id);
    }

    @DeleteMapping("{id}")
    public void deleteWarehouse(@PathVariable int id){
        warehouseDao.delete(id);
    }
}
