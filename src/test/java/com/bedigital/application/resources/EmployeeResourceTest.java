package com.bedigital.application.resources;//package com.bedigital.application.resources;


import com.bedigital.application.domain.ApplicationUser;
import com.bedigital.application.domain.Employee;
import com.bedigital.application.services.ApplicationUserService;
import com.bedigital.application.services.EmployeeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(PER_CLASS)
public class EmployeeResourceTest {

    private static ApplicationUser APPLICATION_USER = new ApplicationUser("eddieEmployee", "1234");
    private final Employee STARTER_EMPLOYEE = new Employee("Edcleidson", "1234", "Edcleidson de Souza Cardoso Junior", "edcleidson@gmail.com", "+55 79 998968393",  true);
    private final Employee UPDATED_EMPLOYEE = new Employee("Mariana", "1234", "mariana de Souza Cardoso Junior", "mariana@gmail.com",  "+55 79 998968393", true);
    private final Gson GSON = new GsonBuilder().create();
    private final String ENDPOINT = "/employees";

    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private EmployeeService employeeService;

    private Long TMP_ID = 1L;
    private String bearer;

    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public void insertAuthData() {
        APPLICATION_USER = applicationUserService.save( APPLICATION_USER);
    }

    @BeforeEach
    public void insertTestingData() {
        TMP_ID = employeeService.save(STARTER_EMPLOYEE).getId();
    }

    @BeforeEach
    public void shouldAuthenticate() throws Exception {
        String userJSON = GSON.toJson(new ApplicationUser("eddieEmployee", "1234"));

        MvcResult mvcResult = mockMvc.perform(post("/login").content(userJSON)).andReturn();
        bearer = mvcResult.getResponse().getContentAsString();
    }

    @AfterAll
    public void deleteData(){
        applicationUserService.delete(APPLICATION_USER);
    }

    @Test
    public void shouldInsertEmployees() throws Exception {
        final Employee INSERT = new Employee("Edcleidson10", "1234", "Edcleidson de Souza Cardoso Junior", "edcleidson@gmail.com",  "+55 79 998968393", true);
        employeeService.save(INSERT);

        String jsonEmployee = GSON.toJson(INSERT);
        MvcResult mvcResult = mockMvc.perform(post(ENDPOINT)
                .header("Authorization", bearer)
                .content(jsonEmployee)
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
        List<?> employees = GSON.fromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void shouldGetOneUser() throws Exception {

        MvcResult mvcResult = mockMvc
                .perform(get(ENDPOINT + "/" + TMP_ID).header("Authorization", bearer))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Employee returnedEmployee = GSON.fromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
        assertTrue(returnedEmployee.equals(STARTER_EMPLOYEE));
    }

    @Test
    public void shouldUpdateAnEmployer() throws Exception {

        String jsonEmployee = GSON.toJson(UPDATED_EMPLOYEE);
        MvcResult mvcResult = mockMvc
                .perform(put(ENDPOINT + "/" + TMP_ID)
                        .header("Authorization", bearer)
                        .content(jsonEmployee)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Employee returnedEmployee = GSON.fromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
        assertTrue(returnedEmployee.equals(UPDATED_EMPLOYEE));
    }

    @Test
    public void shouldDeleteUser() throws Exception {

        mockMvc.perform(delete(ENDPOINT + "/" + TMP_ID).header("Authorization", bearer))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

}
