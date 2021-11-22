package com.dmifed.billoperation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
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

    @Test
    void create() throws Exception {
        Account mockAccount = new Account("vas", "mail@vas");
        Mockito.when(accountController.create(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockAccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/create?name=\"vas\"&email=\"mail@vas\"")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        Account mockAccount = new Account("vas", "new@new");
        Mockito.when(accountController.update(Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(mockAccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/account/update/1/?email=\"new@new\"")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getAccount() throws Exception{
        Account mockAccount = new Account("vas", "mail@vas");
        Mockito.when(accountController.getAccount(Mockito.anyLong())).thenReturn(mockAccount);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/account/1/")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String jsonExpected = "{\"name\":\"vas\",\"email\":\"mail@vas\"}";
        JSONAssert.assertEquals(jsonExpected, result.getResponse()
                .getContentAsString(), false);

    }
}
