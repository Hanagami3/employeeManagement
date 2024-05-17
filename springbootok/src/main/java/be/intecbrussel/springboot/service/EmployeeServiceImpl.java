package be.intecbrussel.springboot.service;

import be.intecbrussel.springboot.model.Employee;
import be.intecbrussel.springboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> postOptional = employeeRepository.findById(id);
        if(!postOptional.isPresent()){
            throw new IllegalStateException("post doesn't exist");
        }
        return postOptional.get();
    }

    @Override
    public void deleteEmployeeByID(Long id){
        this.employeeRepository.deleteById(id);
    }

    @Override
    public boolean isUniqueMail(String email, Long id) {
        Employee testEmployee = employeeRepository.findByEmail(email);
        if (testEmployee == null){
            return true;
        }
        return false;
    }
}
