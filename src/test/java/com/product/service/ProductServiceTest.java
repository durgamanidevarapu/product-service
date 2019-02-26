package com.product.service;

import com.product.dao.ProductServiceDao;
import com.product.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    ProductServiceDao productServiceDaoMock;

    @InjectMocks
    ProductServiceImpl productService;

    Product product = new Product(1L,"product1","proddesc1");

    @Test
    public void testsaveProduct(){

        when(productServiceDaoMock.save(product)).thenReturn(product);
       assertEquals(product,productService.saveProduct(product));
    }

    @Test
    public void testaddProduct(){

        when(productServiceDaoMock.save(product)).thenReturn(product);
    }

    @Test
    public void testfindProductById(){

        when(productServiceDaoMock.findById(1L)).thenReturn(Optional.of(product));

        assertEquals(Optional.of(product),productService.findProductById(1L));
    }

    @Test
    public void testdeleteProduct(){

        productService.deleteProduct(1L);
    }

}
