package com.product.controller;

import com.product.controller.ProductServiceController;
import com.product.domain.Product;
import com.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import  org.springframework.test.web.servlet.result.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductServiceController.class)
public class ProductServiceControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    Product mockProduct = new Product(1L,"product1","productdesc");

    @Test
    public void testCreateProduct() throws Exception {
        String expectedProduct = "{id:1,name:product1,description:productdesc}";
        Mockito.when(productService.addProduct(mockProduct)).thenReturn(mockProduct);
        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(expectedProduct))
                .andExpect(status().isCreated());

    }

}
