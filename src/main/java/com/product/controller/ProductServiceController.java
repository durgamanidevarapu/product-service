package com.product.controller;

import com.product.Exception.ProductNotFoundException;
import com.product.domain.Product;
import com.product.service.ProductService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    Logger log = LogUtil.getLog(this);

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {

        Product savedProduct = productService.saveProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping(value = "/product/{id}")
    public Product getProductDetails(@PathVariable long id) {

        log.info("getting product details");
        Optional<Product> productOptional = productService.findProductById(id);
        if (!productOptional.isPresent()) {
            log.info("no data present");
            throw new ProductNotFoundException("id-" + id + "is not available");
        }
        return (productOptional.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

        Optional<Product> productOptional = productService.findProductById(id);
        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        product.setId(id);
        productService.addProduct(product);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/product")
    public void deleteProduct(@RequestBody String id) {
        log.info("deleting product :{}", id);
        productService.deleteProduct(Long.parseLong(id));
    }


}
