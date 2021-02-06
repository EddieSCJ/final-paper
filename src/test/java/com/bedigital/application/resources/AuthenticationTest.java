package com.bedigital.application.resources;

import com.bedigital.application.domain.ApplicationUser;
import com.bedigital.application.repositories.ApplicationUserRepository;
import com.bedigital.application.services.ApplicationUserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTest {

    private static final ApplicationUser APPLICATION_USER = new ApplicationUser("eddie", "1234");

    private static final Gson GSON = new GsonBuilder().create();

    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void insertData() {
        applicationUserService.save(APPLICATION_USER);
    }

    @Test
    public void shouldAuthenticate() throws Exception {
        String userJSON = GSON.toJson(new ApplicationUser("eddie", "1234"));

        MvcResult mvcResult = mockMvc.perform(post("/login").content(userJSON)).andReturn();
        String bearer = mvcResult.getResponse().getContentAsString();
        assertThat(!bearer.isEmpty());
    }



}
