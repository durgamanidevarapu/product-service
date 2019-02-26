package com.product.service;

import com.product.Exception.ProductNotFoundException;
import com.product.controller.LogUtil;
import com.product.dao.ProductServiceDao;
import com.product.domain.Product;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "productService")
public class ProductServiceImpl  implements ProductService{

    @Autowired
    ProductServiceDao productServiceDao;

    Logger log=  LogUtil.getLog(this);

    @Override
    public Product saveProduct(Product product) {
        return productServiceDao.save(product);
    }

    @Override
    public void addProduct(Product product) {
       log.info("calling repository to store product info");
        productServiceDao.save(product);
    }

    @Override
    public void addAllProducts(List<Product> productList) {
        log.info("calling repository to store product info");
        productServiceDao.saveAll(productList);
    }

    @Override
    public List<Product> getProducts() {
        log.info("calling repository to get product info");
        Iterable<Product> iterator = productServiceDao.findAll();
        List<Product> productList = new ArrayList<>();
        iterator.forEach(productList::add);
        return productList;
    }

    @Override
    public Optional<Product> findProductById(long id) {
        return productServiceDao.findById(id);
    }

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productServiceDao.findById(id);
        if(!product.isPresent()){
            log.info("no data present");
            throw new ProductNotFoundException("id-"+id+"is not available");
        }
            return product.get();
    }


    @Override
    public void deleteProduct(long id) {
        log.info("deleting product with id:{}",id);
        productServiceDao.deleteById(id);
    }


}
