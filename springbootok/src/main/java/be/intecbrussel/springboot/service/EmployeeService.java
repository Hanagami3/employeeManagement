package be.intecbrussel.springboot.service;

import be.intecbrussel.springboot.model.Employee;

import java.util.List;
    public interface EmployeeService {

        List<Employee> getAllEmployee();
        void saveEmployee (Employee employee);
        Employee getEmployeeById(Long id);

        void deleteEmployeeByID(Long id);

        boolean isUniqueMail(String email, Long id);
    }
