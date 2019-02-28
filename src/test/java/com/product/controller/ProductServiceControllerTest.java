package com.product.controller;

import com.google.gson.Gson;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductServiceController.class)
public class ProductServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    Product mockProduct = new Product(1L,"product1","proddesc1");
    @Test
    public void testCreateProduct() throws Exception {

        Mockito.when(productService.saveProduct(mockProduct)).thenReturn(mockProduct);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(mockProduct)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetProductDetails(){


    }



}
