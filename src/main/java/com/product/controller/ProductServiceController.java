package com.product.controller;

import com.product.domain.Product;
import com.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductServiceController {


    @Autowired
    private ProductService productService;

    Logger log=  LogUtil.getLog(this);

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedProduct.getId()).toUri();

        return ResponseEntity.created(location).build();

    }



    /*@RequestMapping(value="/get/products/", method= RequestMethod.GET)
    public  List<Product>  getProductDetails(){

        log.info("getting product details");

        List<Product> productList = productService.getProducts();

        return productList;

    }*/

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


    @RequestMapping(value="/delete/product",method= RequestMethod.DELETE)
    public void deleteProduct(@RequestBody String id){
            log.info("deleting product :{}",id);
           // productService.deleteProduct(productName);
        productService.deleteProduct(Long.parseLong(id));
    }



     /*@RequestMapping(value="/add/AllProducts",method = RequestMethod.POST,consumes="application/json")
    public void addAllProducts(@RequestBody List<Product> productList){
        if (!CollectionUtils.isEmpty(productList)){
            log.info("storing product data:{}",productList);
            productService.addAllProducts(productList);
        }
    }*/

      /*@RequestMapping(value="/store/product", method= RequestMethod.POST,consumes = "application/json")
    public  void   productService(@RequestBody Product product) {
        log.info("product data:{}",product,this);
        productService.addProduct(product);
    }*/

}
