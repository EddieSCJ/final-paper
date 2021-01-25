package com.bedigital.application.services;

import com.bedigital.application.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class EmployeeServiceTest {

    private Long TMP_ID = 1L;
    private final Employee STARTER_EMPLOYEE = new Employee("eddiescj", "1234", "Edcleidson de Souza Cardoso Júnior", "edcleidson@gmail.com", true);
    private final Employee UPDATED_EMPLOYEE = new Employee("UPDATED", "1234", "Edcleidson de Souza Cardoso Júnior", "edcleidson@gmail.com", true);

    @SpyBean
    private EmployeeService employeeService;

    @BeforeEach
    public void insertPreviousData() {
        TMP_ID = employeeService.save(STARTER_EMPLOYEE).getId();
        UPDATED_EMPLOYEE.setId(TMP_ID);
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(employeeService);
    }

    @Test
    public void shouldInsertAnEmployee() {
        Employee insertedEmployee = employeeService.save(STARTER_EMPLOYEE);
        assertEquals(STARTER_EMPLOYEE, insertedEmployee);
    }

    @Test
    public void shouldGetAnEmployeeById() {
        Employee employee = employeeService.findById(TMP_ID);
        assertEquals(STARTER_EMPLOYEE, employee);
    }

    @Test
    public void shouldUpdateAnEmployee() {
        Employee employee = employeeService.save(UPDATED_EMPLOYEE);
        assertEquals(UPDATED_EMPLOYEE, employee);
    }

    @Test
    public void shouldDeleteAnEmployee() {
        employeeService.deleteById(TMP_ID);
        Employee employee = employeeService.findById(TMP_ID);
        assertEquals(null, employee);
    }


}
