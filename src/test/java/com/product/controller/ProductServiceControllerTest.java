package com.product.controller;

import com.google.gson.Gson;
import com.product.domain.Product;
import com.product.domain.ProductDto;
import com.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetProductDetails() throws Exception {
        Mockito.when(productService.findProductById(1L)).thenReturn(Optional.of(mockProduct));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/product/1").accept(
                MediaType.APPLICATION_JSON);
        String expected = "{id:1,name:product1,description:proddesc1}";
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expected,result.getResponse().getContentAsString(),false);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Mockito.when(productService.findProductById(1L)).thenReturn(Optional.of(mockProduct));
               mockMvc.perform(MockMvcRequestBuilders.put("/products/1")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(mockProduct)))
                .andExpect(status().isNoContent());
    }
    @Test
    public void testDeleteProduct() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/product/1")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    }
