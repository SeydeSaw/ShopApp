package com.example.shopapp.controllers;

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

        String userCreationResultJson = mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userDtoJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();
        UserDto userCreationResult = objectMapper.readValue(userCreationResultJson, UserDto.class);

        String createUserJson = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userCreationResult.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        UserDto createdUser = objectMapper.readValue(createUserJson, UserDto.class);

        Assertions.assertEquals(userDto.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(userDto.getFirstName(), createdUser.getFirstName());
        Assertions.assertEquals(userDto.getLastName(), createdUser.getLastName());
        Assertions.assertEquals(userDto.getEmail(), createdUser.getEmail());
    }

    private UserDto getUserDto() {
        UserDto userDto = new UserDto();
        userDto.setUsername("user4");
        userDto.setFirstName("Anella");
        userDto.setLastName("Baumann");
        userDto.setEmail("anella.baumann@gmail.com");
        userDto.setPassword("123Pas");
        return userDto;
    }
}