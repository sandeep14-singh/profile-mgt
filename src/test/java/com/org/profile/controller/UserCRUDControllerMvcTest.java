package com.org.profile.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.profile.dto.UserDto;
import com.org.profile.entity.UserProfile;
import com.org.profile.repo.UserProfileRepository;
import com.org.profile.service.PrimeChecker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest
class UserCRUDControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileRepository userProfileRepository;

    @Test
    void testCreateUser() throws Exception {


        // mock userProfileRepository
        UserProfile toReturn = new UserProfile();
        toReturn.setAge(80);
        toReturn.setName("Sandeep");
        toReturn.setEmail("someEmail");
        when(userProfileRepository.save(any(UserProfile.class)))
                .thenReturn(toReturn);
        // mock userProfileRepository

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