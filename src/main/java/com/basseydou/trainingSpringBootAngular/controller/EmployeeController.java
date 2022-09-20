package com.basseydou.trainingSpringBootAngular.controller;


import com.basseydou.trainingSpringBootAngular.exception.ResourceNotFoundException;
import com.basseydou.trainingSpringBootAngular.model.Employee;
import com.basseydou.trainingSpringBootAngular.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/basseydou")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /*Get all employees*/
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
    return employeeRepository.findAll();
    }

    /*Get employee by id*/
    @GetMapping(path = "/employees/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Long id){
        List<Employee> employeeList = employeeRepository.findAll();
        /*return employeeList.stream().filter(employee ->id.equals(employee.getId()) )
                .findFirst().orElseThrow(()->new ResourceNotFoundException("Employee with id : "+ id +" is not found"));*/
        return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(" Employee with id : "+ id + " is not found"));

    }

}
