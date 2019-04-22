package com.product.service;

import com.product.domain.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    public void addProduct(Product product);
    public Product saveProduct(Product product);
    public Optional<Product> findProductById(long id);
    public void addAllProducts(List<Product> productList);
    public List<Product> getProducts();
    public void deleteProduct(long id);
}
