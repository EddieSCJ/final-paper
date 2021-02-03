package com.bedigital.application.services;

import com.bedigital.application.domain.Employee;
import com.bedigital.application.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee update(Long id, Employee employee) {
        String hashedPassword = UtilsService.hashPassword(employee.getPassword());
        employee.setUsername(employee.getUsername().toLowerCase());
        employee.setPassword(hashedPassword);
        employee.setId(id);

        return employeeRepository.save(employee);
    }

    public Employee save(Employee employee) {
        String hashedPassword = UtilsService.hashPassword(employee.getPassword());
        employee.setUsername(employee.getUsername().toLowerCase());
        employee.setPassword(hashedPassword);

        return employeeRepository.save(employee);
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
