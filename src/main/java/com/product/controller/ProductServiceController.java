package com.product.controller;

import com.product.domain.Product;
import com.product.service.ProductService;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductServiceController {

    @Autowired
    private ProductService productService;


    Logger log = LoggerFactory.getLogger(ProductServiceController.class);

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.addProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();

    }



    @GetMapping(value="/products/")
    public  List<Product>  getProductDetails(){
        log.info("getting product details");
        List<Product> productList = productService.getProducts();
        return productList;
    }

    @GetMapping(value="/product/{id}")
    public Product  getProductDetails(@PathVariable long id){
        log.info("getting product details");
        Product product = productService.getProductById(id);
        return product;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {
        Optional<Product> productOptional = productService.findProductById(id);
        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();
        productService.getProductById(id);
        product.setId(id);
        productService.addProduct(product);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value="/delete/product")
    public void deleteProduct(@RequestBody String id){
        log.info("deleting product :{}",id);
        productService.deleteProduct(Long.parseLong(id));
    }
}
