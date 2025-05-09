package com.sitech.assessment.employee.service.imp;

import com.sitech.assessment.employee.exception.EmployeeAlreadyExistsException;
import com.sitech.assessment.employee.mapper.EmployeeMapper;
import com.sitech.assessment.employee.model.Employee;
import com.sitech.assessment.employee.model.request.EmployeeRequest;
import com.sitech.assessment.employee.model.response.CreateEmployeeResponse;
import com.sitech.assessment.employee.repository.EmployeeRepository;
import com.sitech.assessment.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImp implements EmployeeService {
    private final EmployeeRepository repository;
    private final AtomicLong idCounter;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImp(EmployeeRepository repository, AtomicLong idCounter, EmployeeMapper employeeMapper) {
        this.repository = repository;
        this.idCounter = idCounter;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public synchronized CreateEmployeeResponse create(EmployeeRequest request) throws IOException {
        List<Employee> employees = new ArrayList<>(repository.findAll());

        boolean exists = employees.stream()
                .anyMatch(e -> e.getFirstName().equalsIgnoreCase(request.getFirstName()) &&
                        e.getLastName().equalsIgnoreCase(request.getLastName()));

        if (exists) {
            throw new EmployeeAlreadyExistsException(String.format(
                    "Employee with First Name '%s' or Last Name '%s' already exists.",
                    request.getFirstName(), request.getLastName()));
        }

        Employee employee = employeeMapper.toEntity(request);
        long generatedId = idCounter.getAndIncrement();
        employee.setId(generatedId);
        employees.add(employee);

        repository.saveAll(employees);

        return CreateEmployeeResponse.builder()
                .id(generatedId)
                .build();
    }

    @Override
    public Employee getById(Long id) throws IOException{
        return repository.findAll().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Employee> search(String name, Double fromSalary, Double toSalary , Integer page , Integer size) throws IOException {
        return repository.findAllPaginated(page , size).stream()
                .filter(e -> name == null || e.getFirstName().contains(name) || e.getLastName().contains(name))
                .filter(e -> fromSalary == null || e.getSalary() >= fromSalary)
                .filter(e -> toSalary == null || e.getSalary() <= toSalary)
                .collect(Collectors.toList());
    }
}
