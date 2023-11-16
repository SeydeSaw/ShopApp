package com.example.shopapp.controllers;

import com.example.shopapp.entity.enums.CartStatus;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/db/DropTables.sql")
@Sql("/db/createTables.sql")
@Sql("/db/InsertData.sql")
class CartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    @WithUserDetails("user3")
    void createNewCartTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cart/create"))
                .andExpect(status().isCreated())
                .andReturn();

        String CartDtoResultJson = mvcResult.getResponse().getContentAsString();
        CartDto cartDtoResult = objectMapper.readValue(CartDtoResultJson, CartDto.class);
        Assertions.assertEquals("Alice Suss", cartDtoResult.getClientFullName());
        Assertions.assertEquals(CartStatus.ACTIVE.toString(), cartDtoResult.getStatus());
        Assertions.assertEquals(0.0, cartDtoResult.getTotalPrice());
    }

    @Test
    @WithUserDetails("user1")
    void getByIdTest() throws Exception {

        CartDto cartDto = new CartDto();
        cartDto.setId(1L);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cart/{id}", cartDto.getId()))
                .andExpect(status().isOk())
                .andReturn();

        String CartDtoResultJson = mvcResult.getResponse().getContentAsString();
        CartDto cartDtoResult = objectMapper.readValue(CartDtoResultJson, CartDto.class);
        Assertions.assertEquals(2L, cartDtoResult.getClientId());
        Assertions.assertEquals("ACTIVE", cartDtoResult.getStatus());
    }

    @Test
    @WithUserDetails("user2")
    void addProductToCartTest() throws Exception {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setProductId(2L);
        orderDetailDto.setQuantity(4);
        String orderDetailDtoJson = objectMapper.writeValueAsString(orderDetailDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cart/add-product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderDetailDtoJson))
                .andExpect(status().isOk())
                .andReturn();

        String CartDtoResultJson = mvcResult.getResponse().getContentAsString();
        CartDto cartDtoResult = objectMapper.readValue(CartDtoResultJson, CartDto.class);
        Assertions.assertEquals(2799.80, cartDtoResult.getTotalPrice());
        Assertions.assertEquals("Anella Baumann", cartDtoResult.getClientFullName());
        Assertions.assertEquals(CartStatus.ACTIVE.toString(), cartDtoResult.getStatus());
    }
}