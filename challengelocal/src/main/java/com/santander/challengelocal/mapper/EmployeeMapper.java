package com.santander.challengelocal.mapper;

import com.santander.challengelocal.dto.EmployeeDto;
import com.santander.challengelocal.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    public Employee mapFromDto(EmployeeDto employeeDto) {
        return Employee
                .builder()
                .file(employeeDto.getFile())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .birthDate(employeeDto.getBirthDate())
                .address(employeeDto.getAddress())
                .sector(employeeDto.getSector())
                .build();
    }
    public EmployeeDto mapFromDao(Employee employee) {
        return EmployeeDto
                .builder()
                .file(employee.getFile())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .birthDate(employee.getBirthDate())
                .address(employee.getAddress())
                .sector(employee.getSector())
                .build();
    }
}
