package com.product.service;

import com.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public void addProduct(Product product);
    public Product saveProduct(Product product);
    public void addAllProducts(List<Product> productList);
    public List<Product> getProducts();
    //public Product getProductById(long id);
    public Optional<Product> findProductById(long id);
    public void deleteProduct(long id);
}
