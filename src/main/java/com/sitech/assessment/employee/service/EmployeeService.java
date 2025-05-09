package com.sitech.assessment.employee.service;

import com.sitech.assessment.employee.model.Employee;
import com.sitech.assessment.employee.model.request.EmployeeRequest;
import com.sitech.assessment.employee.model.response.CreateEmployeeResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public interface EmployeeService {

    CreateEmployeeResponse create(EmployeeRequest employee) throws IOException;
    Employee getById(Long id) throws IOException;
    List<Employee> search(String name, Double fromSalary, Double toSalary) throws IOException;

}
