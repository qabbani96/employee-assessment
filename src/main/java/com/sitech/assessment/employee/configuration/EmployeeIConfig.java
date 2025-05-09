package com.sitech.assessment.employee.configuration;

import com.sitech.assessment.employee.model.Employee;
import com.sitech.assessment.employee.repository.EmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class EmployeeIConfig {

    @Bean
    public AtomicLong idCounter(EmployeeRepository repository) throws IOException {
        List<Employee> employees = repository.findAll();
        long maxId = employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0L);
        return new AtomicLong(maxId + 1);
    }


}
