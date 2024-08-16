package com.codinftitans.backend.controller;

import com.codinftitans.backend.dto.CreateEmployeeDTO;
import com.codinftitans.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/create/employee")
    public CreateEmployeeDTO createEmployee(@RequestBody  CreateEmployeeDTO employee){
        return employeeService.createEmployee(employee);
    }
}
