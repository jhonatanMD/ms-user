package com.ms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.business.IUserBusiness;
import com.ms.model.dto.UserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Value("${email}")
    private String emailTest;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IUserBusiness userBusiness;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testSaveUserOk() throws Exception {
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setName("Bob");
        userRequest.setEmail(emailTest);
        userRequest.setPassword("Password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void testSaveUserBadRequest() throws Exception {
        UserRequestDto userRequest = new UserRequestDto();
        userRequest.setName("Bob");
        userRequest.setEmail(emailTest);
        userRequest.setPassword("password123");

        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());


    }
}
