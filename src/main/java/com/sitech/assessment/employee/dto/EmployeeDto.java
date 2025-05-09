package com.sitech.assessment.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Double salary;
    private LocalDate joinDate;
    private String departement;
}
