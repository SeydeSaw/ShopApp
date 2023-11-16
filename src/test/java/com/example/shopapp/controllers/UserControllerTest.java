package com.example.shopapp.controllers;

import com.example.shopapp.domain.enums.Role;
import com.example.shopapp.dto.UserDto;
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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithUserDetails("user2")
    void createUserTest() throws Exception {
        UserDto userDto = getUserDto();
        String userDtoJson = objectMapper.writeValueAsString(userDto);

        MvcResult userCreationResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isCreated())
                .andReturn();

        String createUserJson = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userCreationResult))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();


        UserDto createdUser = objectMapper.readValue(createUserJson, UserDto.class);

//        userDto.setPassword(null);
        Assertions.assertEquals(userDto, createdUser);
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("user2");
        userDto.setFirstName("Anella");
        userDto.setLastName("Baumann");
        userDto.setEmail("anella.baumann@gmail.com");
        userDto.setPassword("******");
        return userDto;
    }
}