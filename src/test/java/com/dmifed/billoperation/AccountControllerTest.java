package com.dmifed.billoperation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
@WithMockUser

class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountController accountController;

    private String testAccountJson = "{\"name\":\"vas\",\"email\":\"mail@vas\"}";
    private Account mockAccount = new Account("vas", "mail@vas");



    @Test
    void create() throws Exception {
        Mockito.when(accountController.create(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockAccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/create?name=\"vas\"&email=\"mail@vas\"")
                .accept(MediaType.APPLICATION_JSON).content(testAccountJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() {
    }

    @Test
    void getAccount() {
    }

    @Test
    void delete() {
    }
}