package com.dmifed.billoperation;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by DIMA, on 22.11.2021
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = BillOperationController.class)
@WithMockUser
class BillOperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BillOperationController billController;

    @Test
    void withdrawal() throws NotEnoughMoneyException {
        String testBillJson = "{\"number\":1010,\"user\":1,\"balance\":10}";
        Bill mockBill = new Bill(1010, 1);
        Mockito.when(billController.withdrawal(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(mockBill);
        assertThrows(NotEnoughMoneyException.class, ()->{
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bill/withdrawal/")
                    .accept(MediaType.APPLICATION_JSON).contentType(testBillJson).contentType(MediaType.APPLICATION_JSON);
            MvcResult result = mockMvc.perform(requestBuilder).andReturn();
            System.out.println("result :" + result.getResponse().getContentAsString());
        });
    }

    @Test
    void refill() throws Exception{
        String testBillJson = "{\"number\":1010,\"user\":1,\"balance\":10}";
        Bill mockBill = new Bill(1010, 1);
        mockBill.setBalance(mockBill.getBalance() + 10);
        Mockito.when(billController.refill(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(mockBill);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/bill/refill/")
                .accept(MediaType.APPLICATION_JSON).content(testBillJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        JSONAssert.assertEquals(testBillJson, response.getContentAsString(), false);
    }

    @Test
    void create() throws Exception {
        String testBillJson = "{\"number\":1010,\"user\":1}";
        Bill mockBill = new Bill(1010, 10);
        Mockito.when(billController.create(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(mockBill);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/bill/create?number=1010&user=1")
                .accept(MediaType.APPLICATION_JSON).content(testBillJson).contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void getBalance() throws Exception {
        Mockito.when(billController.getBalance(Mockito.anyLong())).thenReturn(0L);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/bill/balance/1/")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        long expectedBalance = 0L;
        JSONAssert.assertEquals(String.valueOf(expectedBalance), result.getResponse()
                .getContentAsString(), false);
    }
}
