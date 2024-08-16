package com.codinftitans.backend.controller;

import com.codinftitans.backend.dto.CreateEmployeeDTO;
import com.codinftitans.backend.dto.GetEmployeeDTO;
import com.codinftitans.backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/create/employee")
    public CreateEmployeeDTO createEmployee(@RequestPart("employee")  CreateEmployeeDTO employee, @RequestPart("image")MultipartFile file){
        return employeeService.createEmployee(employee,file);
    }
    @GetMapping("/employees")
    public List<GetEmployeeDTO> getALl(){
        return employeeService.getEmployee();
    }
    @GetMapping("/employee/{idEmployee}")
    public GetEmployeeDTO getById(@PathVariable UUID idEmployee){
        return employeeService.getEmployeeById(idEmployee);
    }
}
