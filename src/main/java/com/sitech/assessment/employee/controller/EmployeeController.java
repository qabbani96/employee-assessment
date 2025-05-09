package com.sitech.assessment.employee.controller;

import com.sitech.assessment.employee.model.Employee;
import com.sitech.assessment.employee.model.request.EmployeeRequest;
import com.sitech.assessment.employee.model.response.CreateEmployeeResponse;
import com.sitech.assessment.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;
    @Value("${pagination.size}")
    private  int defaultPageSize;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateEmployeeResponse> create(@RequestBody EmployeeRequest employee) throws IOException {
        CreateEmployeeResponse saved = service.create(employee);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) throws IOException {
        Employee emp = service.getById(id);
        return emp != null ? ResponseEntity.ok(emp) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(emp);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double fromSalary,
            @RequestParam(required = false) Double toSalary,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) throws IOException {
        int finalSize = (size != null) ? size : defaultPageSize;
        return ResponseEntity.ok(service.search(name, fromSalary, toSalary ,page , finalSize));
    }

}
