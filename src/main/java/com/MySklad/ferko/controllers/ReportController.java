package com.MySklad.ferko.controllers;

import com.MySklad.ferko.dao.ProductDao;
import com.MySklad.ferko.dao.WarehouseDao;
import com.MySklad.ferko.models.Product;
import com.MySklad.ferko.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class ReportController {

    private ProductDao productDao;
    private WarehouseDao warehouseDao;

    @Autowired
    public ReportController(ProductDao productDao, WarehouseDao warehouseDao) {
        this.productDao = productDao;
        this.warehouseDao = warehouseDao;
    }

    @GetMapping("product")
    public String getProductList(Model model) {
        Iterable<Product> products = productDao.getList();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("product")
    public String filterProduct(@RequestParam String filter, Model model){
       Iterable<Product> products;
       if(filter != null && !filter.isEmpty())
           products =  productDao.getByName(filter);
       else
           products = productDao.getList();
       model.addAttribute("products", products);
       return "products";
    }

    @GetMapping("balance")
    public String getBalanceList(Model model) {
        Iterable<Product> products = productDao.getList();
        model.addAttribute("products", products);
        return "balance";
    }

    @PostMapping("balance")
    public String filterBalance(@RequestParam String filter, Model model){
        Iterable<Product> products;
        if(filter != null && !filter.isEmpty()){
            for(Warehouse warehouse : warehouseDao.getList()){
                    if(warehouse.getName().equals(filter)){
                        for(Product product : productDao.getList()){
                            for(Map.Entry<Integer, Integer> pair : warehouse.getMapOfProduct().entrySet()){
                                if(product.getArticle()==pair.getKey()){
                                    product.setBalance(pair.getValue());
                                }
                            }
                    }
                }
            }
            products = productDao.getList();
        }
        else {
            for(Product product : productDao.getList()){
                product.setBalance(productDao.getProductBalance(product));
            }
            products = productDao.getList();
        }

        model.addAttribute("products", products);
        return "balance";
    }
}
