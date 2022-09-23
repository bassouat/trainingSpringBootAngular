package com.basseydou.trainingSpringBootAngular.controller;


import com.basseydou.trainingSpringBootAngular.exception.ResourceNotFoundException;
import com.basseydou.trainingSpringBootAngular.model.Employee;
import com.basseydou.trainingSpringBootAngular.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/basseydou")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /*Get all employees*/
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /*Get employee by id*/
    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        /*List<Employee> employeeList = employeeRepository.findAll();*/
        /*return employeeList.stream().filter(employee ->id.equals(employee.getId()) )
                .findFirst().orElseThrow(()->new ResourceNotFoundException("Employee with id : "+ id +" is not found"));*/
        Employee employeeById = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(" Employee with id : " + employeeId + " is not found"));
        return ResponseEntity.ok(employeeById);

    }

    // TODO: 20/09/2022 get Employee by firstName or lastName or email

    //Create employee REST API
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    //Update employee REST API
    @PutMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,@RequestBody Employee newEmployee){
         final Employee employeeUpdate=employeeRepository.findById(employeeId)
                        .map(employee -> {employee.setFirstname(newEmployee.getFirstname());
                                            employee.setLastName(newEmployee.getLastName());
                                            employee.setEmailId(newEmployee.getEmailId());
                        return employeeRepository.save(employee);} )
                 .orElseThrow(() -> new ResourceNotFoundException(" Employee with id : " + employeeId + " is not found"));
                    /*.orElseGet(()->{newEmployee.setId(employeeId);
                    return employeeRepository.save(newEmployee);});*/

        return ResponseEntity.ok(employeeUpdate);
    }


}
