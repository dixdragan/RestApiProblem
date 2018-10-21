package com.dx.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SecureRestContollerTest {


	@Autowired
	private MockMvc mvc;

	@MockBean
	private SecureRestContoller secureController;

	@Test
	public void helloTest() throws Exception {
		mvc.perform(get("/secure/distance/AB10 1XG/AB21 0XS").accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
				.header(HttpHeaders.AUTHORIZATION, "Basic YWRtaW46YWRtaW4="))
				.andExpect(status().isOk());
	}
	
	@Test
    public void accessProtected() throws Exception {
        this.mvc.perform(get("/secure/distance/AB10 1XG/AB21 0XS"))
                .andExpect(status().isUnauthorized());
    }
	
	@Test
    public void loginUser() throws Exception {
        this.mvc.perform(get("/secure/distance/AB10 1XG/AB21 0XS").with(httpBasic("user", "user")))
                .andExpect(authenticated());
    }
	
	@Test
    public void loginInvalidUser() throws Exception {
        MvcResult result = this.mvc.perform(get("/secure/distance/AB10 1XG/AB21 0XS").with(httpBasic("invalid", "invalid")))
                .andExpect(unauthenticated())
                .andExpect(status().is4xxClientError())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("HTTP Status 401"));
    }


}
