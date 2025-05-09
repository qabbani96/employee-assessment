package com.sitech.assessment.employee.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sitech.assessment.employee.exception.FileNotFoundException;
import com.sitech.assessment.employee.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmployeeRepository {
    private final Path filePath ;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public EmployeeRepository(@Value("${file.path}") Path filePath) {
        this.filePath = filePath;
    }

    public List<Employee> findAll() throws IOException {
        if(Files.notExists(filePath))
            throw new FileNotFoundException("File DataBase Does Not Exist");

        if (Files.size(filePath) == 0)
            return new ArrayList<>();

        return Arrays.asList(mapper.readValue(filePath.toFile(), Employee[].class));
    }

    public void saveAll(List<Employee> employees) throws IOException {
        mapper.writeValue(filePath.toFile(), employees);
    }
}
