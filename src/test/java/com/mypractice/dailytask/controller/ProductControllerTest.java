package com.mypractice.dailytask.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mypractice.dailytask.exception.DuplicateEntityException;
import com.mypractice.dailytask.service.ProductService;
import com.mypractice.dailytask.types.ProductInfoRequest;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private ProductsController productsController;
	
	@Mock
	private ProductService productService;
	
	@Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

	
	@Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(productsController).build();
    }
	
	@Test
	public void testcreateProducts() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
		doNothing().when(productService).save(defaultProduct());

        String url = "/product/save";


        mockMvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(defaultProduct())))
                .andExpect(status().isCreated());

	}

    @Test
    public void testcreateProductsFailure() throws Exception {

        //doThrow(new DuplicateEntityException("Product exists")).when(productService).save(defaultProduct());
        doNothing().when(productService).save(defaultProduct());

        String url = "/product/save";


        mockMvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new ProductInfoRequest())))
                .andExpect(status().isBadRequest());


    }
	
	protected static ProductInfoRequest defaultProduct() {
        final ProductInfoRequest request = new ProductInfoRequest();
        request.setName("Book");
        request.setCost(11);
        request.setInStock(true);
        return request;
    }

}
