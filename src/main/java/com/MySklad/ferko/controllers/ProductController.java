package com.MySklad.ferko.controllers;

import com.MySklad.ferko.dao.ProductDao;
import com.MySklad.ferko.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao){
        this.productDao = productDao;
    }

    @GetMapping
    public List<Product> getProductsList(){
        return productDao.getList();
    }

    @GetMapping("{id}")
    public Product getOneProduct(@PathVariable int id){
        return productDao.getOne(id);
    }


    @PostMapping
    public void postProduct(@Valid Product product){
        productDao.post(product);
    }

    @PutMapping("{id}")
    public Product updateProduct(@Valid @RequestBody Product product, @PathVariable int id){
        return productDao.update(product, id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable int id){
        productDao.delete(id);
    }
}
