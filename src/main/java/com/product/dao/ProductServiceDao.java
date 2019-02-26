package com.product.dao;

import com.product.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductServiceDao extends CrudRepository<Product, Long> {

    public Product findAllByName(String name);


}
