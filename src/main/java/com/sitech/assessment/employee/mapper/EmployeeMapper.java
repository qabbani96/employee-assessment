package com.sitech.assessment.employee.mapper;

import com.sitech.assessment.employee.dto.EmployeeDto;
import com.sitech.assessment.employee.model.Employee;
import com.sitech.assessment.employee.model.request.EmployeeRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    //EmployeeDto toDto(Employee employee);
    Employee toEntity(EmployeeRequest employeeDto);
}
