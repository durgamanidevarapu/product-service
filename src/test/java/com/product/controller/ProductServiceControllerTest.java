package com.product.controller;

import com.product.application.ProductServiceApplication;
import com.product.domain.Product;
import com.product.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = ProductServiceApplication.class)
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductServiceController.class, secure = false)
public class ProductServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

   /* @Autowired
    private WebApplicationContext wac;*/

    @MockBean
    private ProductService productService;

   private Product mockProduct = new Product(1L,"product1","proddesc1");


   /* @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }*/


    @Test
    public void testCreateProduct() throws Exception {
        Mockito.when(productService.saveProduct(mockProduct)).thenReturn(mockProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/products").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());
        String expected = "{id:1L,name:product1,description:proddesc1}";

        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

        assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }



}
