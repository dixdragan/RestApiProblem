package com.dx.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.dx.model.Distance;


@RunWith(SpringRunner.class)
@WebMvcTest(value=PublicRestControllerTest.class, secure=false)

public class PublicRestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private PublicRestController prControllerMock;
	
	@Test
	public void helloTest() throws Exception {
		this.mockMvc.perform(get("/guest/distance/AB101XG/AB210XS").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(status().isOk());
	}
	
	ResponseEntity<Object> mockRED = ResponseEntity.ok(new Distance());

	@Test
    public void getByCodeTest() throws Exception {
		
        when(prControllerMock.getByCode(Mockito.anyString(),Mockito.anyString())).thenReturn(mockRED);
        
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/guest/distance/AB101XG/AB210XS").accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        System.out.print(mvcResult);
        
        String expectedString = "{\r\n" + 
        		"    \"postCodeFrom\": {\r\n" + 
        		"        \"id\": 1,\r\n" + 
        		"        \"code\": \"AB10 1XG\",\r\n" + 
        		"        \"latitude\": 57.14416516,\r\n" + 
        		"        \"longitude\": -2.114847768\r\n" + 
        		"    },\r\n" + 
        		"    \"postCodeTo\": {\r\n" + 
        		"        \"id\": 250,\r\n" + 
        		"        \"code\": \"AB21 0XS\",\r\n" + 
        		"        \"latitude\": 57.20759747,\r\n" + 
        		"        \"longitude\": -2.286653354\r\n" + 
        		"    },\r\n" + 
        		"    \"distanceInKm\": 12.529408006791005,\r\n" + 
        		"    \"distanceStringInKm\": \"12.529408006791005 km\"\r\n" + 
        		"}";
        
        JSONAssert.assertEquals(expectedString, mvcResult.getResponse().getContentAsString(), false);
    }
}
