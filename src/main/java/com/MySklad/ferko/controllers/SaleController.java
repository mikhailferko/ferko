package com.MySklad.ferko.controllers;


import com.MySklad.ferko.dao.ProductDao;
import com.MySklad.ferko.dao.WarehouseDao;
import com.MySklad.ferko.documents.Sale;
import com.MySklad.ferko.exceptions.NotFoundException;
import com.MySklad.ferko.models.Product;
import com.MySklad.ferko.models.Warehouse;
import com.MySklad.ferko.service.ProductSale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("sale")
public class SaleController {

    private ProductDao productDao;
    private WarehouseDao warehouseDao;

    @Autowired
    public SaleController(ProductDao productDao, WarehouseDao warehouseDao) {
        this.productDao = productDao;
        this.warehouseDao = warehouseDao;
    }

    private int count = 2;

    ProductSale productSale1 = new ProductSale(1, 1, 15);
    ProductSale productSale2 = new ProductSale(2, 2, 16);
    ProductSale productSale3 = new ProductSale(3, 3, 17);
    ProductSale productSale4 = new ProductSale(4, 4, 18);

    List<ProductSale> productSaleList = new ArrayList<>();
    {
        productSaleList.add(productSale1);
        productSaleList.add(productSale2);
        productSaleList.add(productSale3);
        productSaleList.add(productSale4);
    }

    List<Sale> saleList = new ArrayList<>();

    {
        saleList.add(new Sale(1, "sklad1", productSaleList));
    }

    @GetMapping
    public List<Sale> getSaleList(){
        return saleList;
    }

    @GetMapping("{id}")
    public Sale getSale(@PathVariable int id){
        return saleList.stream().filter(saleList -> saleList.getId() == id)
                .findAny().orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void postSale(@RequestBody Sale sale){
        sale.setId(count++);
        saleList.add(sale);
        List<ProductSale> list = sale.getListOfProducts();
        for (ProductSale prSale: list){
            for(Product product : productDao.getList()){
                if(prSale.getArticle()==product.getArticle()) {
                    product.setLastSalePrice(prSale.getPrice());
                    product.setBalance(product.getBalance() - prSale.getQuantity());
                }
            }
        }
        for(Warehouse warehouse : warehouseDao.getList()){
            if(warehouse.getName().equals(sale.getWarehouseName())){
                for(ProductSale prodSa : list){
                    warehouse.getMapOfProduct().put(prodSa.getArticle(),
                            warehouse.getMapOfProduct()
                            .get(prodSa.getArticle()) - prodSa.getQuantity());
                }
            }
        }
    }
}
