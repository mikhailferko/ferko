package com.MySklad.ferko.controllers;

import com.MySklad.ferko.dao.WarehouseDao;
import com.MySklad.ferko.documents.Moving;
import com.MySklad.ferko.documents.Sale;
import com.MySklad.ferko.exceptions.NotFoundException;
import com.MySklad.ferko.models.Warehouse;
import com.MySklad.ferko.service.ProductMoving;
import com.MySklad.ferko.service.ProductReceipt;
import com.MySklad.ferko.service.ProductSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("moving")
public class MovingController {

    private WarehouseDao warehouseDao;

    @Autowired
    public MovingController(WarehouseDao warehouseDao){
        this.warehouseDao = warehouseDao;
    }

    private int count = 2;

    ProductMoving productMoving1 = new ProductMoving(1, 1);
    ProductMoving productMoving2 = new ProductMoving(2, 2);
    ProductMoving productMoving3 = new ProductMoving(3, 3);
    ProductMoving productMoving4 = new ProductMoving(4, 4);

    List<ProductMoving> productMovingList = new ArrayList<>();
    {
        productMovingList.add(productMoving1);
        productMovingList.add(productMoving2);
        productMovingList.add(productMoving3);
        productMovingList.add(productMoving4);
    }

    List<Moving> movingList = new ArrayList<>();

    {
        movingList.add(new Moving(1, "Sklad1", "Sklad2", productMovingList));
    }

    @GetMapping
    public List<Moving> getMovingList(){
        return movingList;
    }

    @GetMapping("{id}")
    public Moving getMoving(@PathVariable int id){
        return movingList.stream().filter(movingList -> movingList.getId() == id)
                .findAny().orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void postMoving(@RequestBody Moving moving) {
        moving.setId(count++);
        movingList.add(moving);
        for(Warehouse warehouse : warehouseDao.getList()) {
            if (warehouse.getName().equals(moving.getWarehouseNameFrom())) {
                for (ProductMoving prodMov : moving.getListOfProducts()) {
                    warehouse.getMapOfProduct().put(prodMov.getArticle(),
                            warehouse.getMapOfProduct()
                                    .get(prodMov.getArticle()) - prodMov.getQuantity());
                }
            }
        }
            for(Warehouse warehouse : warehouseDao.getList()) {
            if(warehouse.getName().equals(moving.getWarehouseNameTo())){
                for(ProductMoving prodMov : moving.getListOfProducts()){
                    if(warehouse.getMapOfProduct().containsKey(prodMov.getArticle())) {
                        warehouse.getMapOfProduct()
                                .put(prodMov.getArticle(),
                                        warehouse.getMapOfProduct()
                                                .get(prodMov.getArticle()) + prodMov.getQuantity());
                    } else {
                        warehouse.getMapOfProduct().put(prodMov.getArticle(), prodMov.getQuantity());
                    }
                }
            }
        }
        }
    }

