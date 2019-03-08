package com.product.service;

import com.product.dao.ProductServiceDao;
import com.product.domain.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductServiceDao productServiceDao;

    Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

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
    public void deleteProduct(long id) {
        log.info("deleting product with id:{}", id);
        productServiceDao.deleteById(id);
    }

    public void updateProduct(Product product) {
        log.info("calling repository to store product info");
        //productServiceDao.(product);
    }
}
