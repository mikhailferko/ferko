package com.MySklad.ferko.dao;

import com.MySklad.ferko.exceptions.NotFoundException;
import com.MySklad.ferko.models.Product;
import com.MySklad.ferko.models.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class ProductDao {

    private WarehouseDao warehouseDao;

    @Autowired
    public ProductDao(WarehouseDao warehouseDao) {
        this.warehouseDao = warehouseDao;
    }

    private List<Product> products = new ArrayList<>();
    private static int counter;

    {
        products.add(new Product(++counter, 0000001, "iPhone", 1000, 1500));
        products.add(new Product(++counter, 0000002, "Mac", 1500, 2000));
        products.add(new Product(++counter, 0000003, "Samsung", 700, 1000));
        products.add(new Product(++counter, 0000004, "iWatch", 500, 700));
    }

    public List<Product> getList(){
        return products;
    }

    public Product getOne(int id){
        return getById(id);
    }

    private Product getById(int id){
        return products.stream().filter(products -> products.getId() == id)
                .findAny().orElseThrow(NotFoundException::new);
    }

    public List<Product> getByName(String name){
        List<Product> list = new ArrayList<>();
        for(Product product : products){
            if (product.getName().equals(name))
                list.add(product);
        }
        return list;
    }

    public void post(Product product){
        product.setId(++counter);
        products.add(product);
    }

    public Product update(Product product, int id){
        Product productfromDB = getById(id);
        productfromDB.setId(id);
        productfromDB.setArticle(product.getArticle());
        productfromDB.setName(product.getName());
        productfromDB.setLastBuyPrice(product.getLastBuyPrice());
        productfromDB.setLastSalePrice(product.getLastSalePrice());
        return productfromDB;
    }

    public void delete(int id){
        products.remove(getById(id));
    }

    public int getProductBalance(Product product){
        int sum = 0;
        for(Warehouse warehouse : warehouseDao.getList()){
            for(Map.Entry<Integer, Integer> pair : warehouse.getMapOfProduct().entrySet()){
                if(pair.getKey() == product.getArticle()){
                    sum+=pair.getValue();
                }
            }
        }
        return sum;
    }
}
