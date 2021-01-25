package com.bedigital.application.repositories;

import com.bedigital.application.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EmployeeRepositoryTest {

    private Long TMP_ID = 1L;
    private final Employee STARTER_EMPLOYEE = new Employee("eddiescj", "1234", "Edcleidson de Souza Cardoso Júnior", "edcleidson@gmail.com", true);
    private final Employee UPDATED_EMPLOYEE = new Employee("UPDATED", "1234", "Edcleidson de Souza Cardoso Júnior", "edcleidson@gmail.com", true);

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void insertPreviousData() {
        TMP_ID = employeeRepository.save(STARTER_EMPLOYEE).getId();
        UPDATED_EMPLOYEE.setId(TMP_ID);
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(employeeRepository);
    }

    @Test
    public void shouldInsertAnEmployee() {
        final Employee EMPLOYEE = new Employee( "mariana", "1234", "Mariana de Souza Cardoso Júnior", "Mariana@gmail.com", true);
        Employee insertedEmployee = employeeRepository.save(EMPLOYEE);
        assertEquals(EMPLOYEE, insertedEmployee);
    }

    @Test
    public void shouldGetAnEmployeeById() {
        Employee employee = employeeRepository.findById(TMP_ID).orElse(null);
        assertEquals(STARTER_EMPLOYEE, employee);
    }

    @Test
    public void shouldReturnAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        assertTrue(employees.size() > 0);
    }

    @Test
    public void shouldUpdateAnEmployeeById() {
        Employee employee = employeeRepository.save(UPDATED_EMPLOYEE);
        assertEquals(UPDATED_EMPLOYEE, employee);
    }

    @Test
    public void shouldDeleteAnEmployeeById() {
        employeeRepository.deleteById(TMP_ID);
        Employee employee = employeeRepository.findById(TMP_ID).orElse(null);
        assertEquals(null, employee);
    }

}
