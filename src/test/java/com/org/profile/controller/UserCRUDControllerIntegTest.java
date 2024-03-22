package com.org.profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.profile.dto.UserDto;
import com.org.profile.entity.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserCRUDControllerIntegTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUser() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        UserDto userDto = new UserDto();
        userDto.setEmail("someEmail");
        userDto.setAge(80);
        userDto.setName("Sandeep");

        String content = objectMapper.writeValueAsString(userDto);
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .content(content).contentType(MediaType.APPLICATION_JSON)
        );
        MockHttpServletResponse response = result.andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(200);

        UserProfile userProfile = objectMapper.readValue(response.getContentAsString(), UserProfile.class);
        assertThat(userProfile.getName()).isEqualTo("Sandeep");

    }

}