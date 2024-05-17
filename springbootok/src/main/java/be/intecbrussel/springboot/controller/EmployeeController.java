package be.intecbrussel.springboot.controller;

import be.intecbrussel.springboot.model.Employee;
import be.intecbrussel.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping("/")
    public String viewHomePage(Model model){
     model.addAttribute("listEmployees", employeeService.getAllEmployee());
        return "index";
   }

    @RequestMapping("/index_error")
    public String viewHomePageError(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployee());
        return "index_error";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model){
        //create model attribute to bind form data
        Employee employee = new Employee();
        //Thymeleaf template will access this empty employee object for binding form data
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
//    public String saveEmployee(@ModelAttribute("employee") Employee employee){
//        employeeService.saveEmployee(employee);
//        return "redirect:/";
//    }

    public String saveEmployee(@ModelAttribute("employee") @Validated Employee employee, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!employeeService.isUniqueMail(employee.getEmail(), employee.getId())) {
            return "index_error";
        }

        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") Long id, Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id){
        this.employeeService.deleteEmployeeByID(id);
        return "redirect:/";
    }



}
