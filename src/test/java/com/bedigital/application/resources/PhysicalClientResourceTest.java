package com.bedigital.application.resources;
import com.bedigital.application.domain.ApplicationUser;
import com.bedigital.application.domain.PhysicalClient;
import com.bedigital.application.domain.PhysicalClient;
import com.bedigital.application.repositories.ApplicationUserRepository;
import com.bedigital.application.services.ApplicationUserService;
import com.bedigital.application.services.PhysicalClientService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(PER_CLASS)
public class PhysicalClientResourceTest {
    private static ApplicationUser APPLICATION_USER = new ApplicationUser("eddiePhysicalClient", "1234");
    private final PhysicalClient STARTER_CLIENT = new PhysicalClient("Eddie10", new Date("09/11/2001"), "130.309.314-63", "eddiescj10@gmail.com",  "+55 79 998968393",  "Rua estancia 1292, SE, Brasil");
    private final PhysicalClient UPDATED_CLIENT = new PhysicalClient("Eddiescj", new Date("09/11/2001"), "13030931463", "eddiescj0@gmail.com",  "+55 79 998968393", "Rua 1292, SE, Brasil");
    private final Gson GSON = new GsonBuilder().create();
    private final String ENDPOINT = "/physical-clients";
    private Long TMP_ID = 1L;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private PhysicalClientService physicalClientService;
    private String bearer;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void insertData() {
        APPLICATION_USER = applicationUserService.save(APPLICATION_USER);
    }

    @BeforeEach
    public void insertTestingData() {
        TMP_ID = physicalClientService.save(STARTER_CLIENT).getId();
    }

    @BeforeEach
    public void authenticate() throws Exception {
        String userJSON = GSON.toJson(new ApplicationUser("eddiePhysicalClient", "1234"));

        MvcResult mvcResult = mockMvc.perform(post("/login").content(userJSON)).andReturn();
        bearer = mvcResult.getResponse().getContentAsString();
    }

    @AfterAll
    public void deleteData(){
        applicationUserService.delete(APPLICATION_USER);
    }

    @Test
    public void shouldInsertClient() throws Exception {
        final PhysicalClient INSERT = new PhysicalClient("Eddie101", new Date("09/11/2001"), "130.309.314-63", "eddiescj10@gmail.com",  "+55 79 998968393",  "Rua estancia 1292, SE, Brasil");

        String jsonClient = GSON.toJson(INSERT);
        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .header("Authorization", bearer)
                .content(jsonClient)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void shouldGetAllEmployers() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ENDPOINT).header("Authorization", bearer))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<?> clients = GSON.fromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertTrue(clients.size() > 0);
    }

    @Test
    public void shouldGetOneUser() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ENDPOINT + "/" + TMP_ID).header("Authorization", bearer)
                )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        PhysicalClient returnedClient = GSON.fromJson(mvcResult.getResponse().getContentAsString(), PhysicalClient.class);
        assertEquals(STARTER_CLIENT, returnedClient);
    }

    @Test
    public void shouldUpdateAnEmployer() throws Exception {
        String jsonClient = GSON.toJson(UPDATED_CLIENT);
        MvcResult mvcResult = mockMvc
                .perform(put(ENDPOINT + "/" + TMP_ID)
                        .header("Authorization", bearer)
                        .content(jsonClient)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        PhysicalClient returnedClient = GSON.fromJson(mvcResult.getResponse().getContentAsString(), PhysicalClient.class);
        assertEquals(UPDATED_CLIENT, returnedClient);

    }

    @Test
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete(ENDPOINT + "/" + TMP_ID).header("Authorization", bearer))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

}
