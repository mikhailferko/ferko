package com.MySklad.ferko.controllers;

import com.MySklad.ferko.dao.ProductDao;
import com.MySklad.ferko.dao.WarehouseDao;
import com.MySklad.ferko.documents.Receipt;
import com.MySklad.ferko.exceptions.NotFoundException;
import com.MySklad.ferko.models.Product;
import com.MySklad.ferko.models.Warehouse;
import com.MySklad.ferko.service.ProductReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("receipt")
public class ReceiptController {

    private ProductDao productDao;
    private WarehouseDao warehouseDao;

    @Autowired
    public ReceiptController(ProductDao productDao, WarehouseDao warehouseDao) {
        this.productDao = productDao;
        this.warehouseDao = warehouseDao;
    }

    private int count = 2;

    ProductReceipt productReceipt1 = new ProductReceipt(1, 5, 10);
    ProductReceipt productReceipt2 = new ProductReceipt(2, 6, 12);
    ProductReceipt productReceipt3 = new ProductReceipt(3, 7, 14);
    ProductReceipt productReceipt4 = new ProductReceipt(4, 8, 16);

    List<ProductReceipt> productReceiptList = new ArrayList<>();
    {
        productReceiptList.add(productReceipt1);
        productReceiptList.add(productReceipt2);
        productReceiptList.add(productReceipt3);
        productReceiptList.add(productReceipt4);
    }

    List<Receipt> receiptList = new ArrayList<>();

    {
        receiptList.add(new Receipt(1, "Sklad1", productReceiptList));
    }

    @GetMapping
    public List<Receipt> getReceiptList(){
        return receiptList;
    }

    @GetMapping("{id}")
    public Receipt getReceipt(@PathVariable int id){
        return receiptList.stream().filter(receiptList -> receiptList.getId() == id)
                .findAny().orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void postReceipt(@RequestBody Receipt receipt){
        receipt.setId(count++);
        receiptList.add(receipt);
        List<ProductReceipt> list = receipt.getListOfProducts();
        for (ProductReceipt prRec: list){
            for(Product product : productDao.getList()){
                if(prRec.getArticle()==product.getArticle()) {
                    product.setLastBuyPrice(prRec.getPrice());
                    product.setBalance(product.getBalance() + prRec.getQuantity());
                }
            }
        }
        for(Warehouse warehouse : warehouseDao.getList()){
            if(warehouse.getName().equals(receipt.getWarehouseName())){
                for(ProductReceipt prodRec : list){
                    if(warehouse.getMapOfProduct().containsKey(prodRec.getArticle())) {
                        warehouse.getMapOfProduct()
                                .put(prodRec.getArticle(),
                                        warehouse.getMapOfProduct()
                                        .get(prodRec.getArticle()) + prodRec.getQuantity());
                    } else {
                        warehouse.getMapOfProduct().put(prodRec.getArticle(), prodRec.getQuantity());
                    }
                }
            }
        }
    }
}
