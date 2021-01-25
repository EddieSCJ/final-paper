package com.bedigital.application.resources;//package com.bedigital.application.resources;


import com.bedigital.application.domain.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeResourceTest {

    private final Employee STARTER_EMPLOYEE = new Employee("edcleidson", "1234", "Edcleidson de Souza Cardoso Junior", "edcleidson@gmail.com", true);
    private final Employee UPDATED_EMPLOYEE = new Employee("Mariana", "1234", "mariana de Souza Cardoso Junior", "mariana@gmail.com", true);
    private final Gson GSON = new GsonBuilder().create();
    private final String ENDPOINT = "/employees";
    private final Integer ID = 1;
    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    public void shouldInsertEmployees() throws Exception {
        String jsonEmployee = GSON.toJson(STARTER_EMPLOYEE);
        mockMvc.perform(post(ENDPOINT)
                .content(jsonEmployee)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Order(2)
    @Test
    public void shouldGetAllEmployers() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ENDPOINT))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<?> employees = GSON.fromJson(mvcResult.getResponse().getContentAsString(), List.class);
        assertTrue(employees.size() > 0);
    }

    @Order(3)
    @Test
    public void shouldGetOneUser() throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(ENDPOINT + "/" + ID))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString() + "aaaaaaaaaaa");
        Employee returnedEmployee = GSON.fromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
        assertTrue(returnedEmployee.equals(STARTER_EMPLOYEE));
    }

    @Order(4)
    @Test
    public void shouldUpdateAnEmployer() throws Exception {
        String jsonEmployee = GSON.toJson(UPDATED_EMPLOYEE);
        MvcResult mvcResult = mockMvc
                .perform(put(ENDPOINT + "/" + ID).content(jsonEmployee).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Employee returnedEmployee = GSON.fromJson(mvcResult.getResponse().getContentAsString(), Employee.class);
        assertTrue(returnedEmployee.equals(UPDATED_EMPLOYEE));
    }

    @Order(5)
    @Test
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete(ENDPOINT + "/" + ID))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

}
