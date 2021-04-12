package com.MySklad.ferko.dao;

import com.MySklad.ferko.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


class ProductDaoTest {

    private ProductDao productDao;

    @Autowired
    public ProductDaoTest(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Test
    void getList() {
        Product product1 = new Product(1, 0000001, "iPhone", 1000, 1500);
        Product product2 = new Product(2, 0000002, "Mac", 1500, 2000);
        Product product3 = new Product(3, 0000003, "Samsung", 700, 1000);
        Product product4 = new Product(4, 0000004, "iWatch", 500, 700);

        List<Product> expected = productDao.getList();

        List<Product> actual = new ArrayList<>();
        actual.add(product1);
        actual.add(product2);
        actual.add(product3);
        actual.add(product4);

        Assert.assertEquals(expected, actual);
    }

    @Test
    void getOne() {
    }

    @Test
    void getByName() {
    }

    @Test
    void post() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}