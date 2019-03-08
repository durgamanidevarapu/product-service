package com.product.controller;

import com.product.Exception.ProductNotFoundException;
import com.product.domain.Product;
import com.product.domain.ProductDto;
import com.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    Logger log = LoggerFactory.getLogger(ProductServiceController.class);

    @PostMapping("/products")
    public ResponseEntity<Object> createProduct(@RequestBody ProductDto productDto) {

        Product savedProduct = productService.saveProduct(convertToEntity(productDto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedProduct.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @GetMapping(value = "/product/{id}")
    public ProductDto getProductDetails(@PathVariable long id) {

        log.info("getting product details");
        Optional<Product> productOptional = productService.findProductById(id);
        if (!productOptional.isPresent()) {
            log.info("no data present");
            throw new ProductNotFoundException("id-" + id + "is not available");
        }
        return convertToDto(productOptional.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@RequestBody ProductDto productDto, @PathVariable long id) {

        Optional<Product> productOptional = productService.findProductById(id);
        if (!productOptional.isPresent())
            return ResponseEntity.notFound().build();

        Product product = updateProductEntity(productDto,productOptional.get());

        productService.addProduct(product);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(value = "/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        log.info("deleting product :{}", id);
        productService.deleteProduct(id);
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper().map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) {
        return modelMapper().map(productDto, Product.class);
    }

    private Product updateProductEntity(ProductDto productDto,Product product){

        Product productEntity = convertToEntity(productDto);
        productEntity.setId(product.getId());
        if(StringUtils.isEmpty(productEntity.getName())){
            productEntity.setName(product.getName());
        }
        if(StringUtils.isEmpty(product.getDescription())){
            productEntity.setName(product.getDescription());
        }
        return productEntity;
    }


}
