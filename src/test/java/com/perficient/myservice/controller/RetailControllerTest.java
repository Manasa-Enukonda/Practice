package com.perficient.myservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.myservice.service.RetailService;
import com.perficient.myservice.types.ProductInfoRequest;
import com.perficient.myservice.types.ProductInfoResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RetailControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private RetailController retailController;
	
	@Mock
	private RetailService retailService;
	
	@Before
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(retailController).build();
    }

	@Test
	public void testSaveRetailInfo() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		when(retailService.createRetailInfo(defaultProduct())).thenReturn(defaultProductResponse());

		MvcResult aReturn = mockMvc.perform(post("/api/v1/retail")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(defaultProduct())))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(21l))
				.andReturn();

	}

	@Test
	public void testSaveRetailInfoNegative() throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();
		when(retailService.createRetailInfo(defaultProduct())).thenReturn(null);

		MvcResult aReturn = mockMvc.perform(post("/api/v1/retail")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(defaultProduct())))
				.andExpect(status().isInternalServerError())
				.andReturn();

	}



	protected static ProductInfoResponse defaultProductResponse() {
		final ProductInfoResponse response = new ProductInfoResponse();
		response.setName("Book");
		response.setCost(11);
		response.setInStock(true);
		response.setId(21l);
		return response;
	}
	protected static ProductInfoRequest defaultProduct() {
		final ProductInfoRequest request = new ProductInfoRequest();
		request.setName("Book");
		request.setCost(11);
		request.setInStock(true);
		request.setId(21);
		return request;
	}
	
	

}
