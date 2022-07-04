package com.santander.challengelocal.controller;

import com.santander.challengelocal.dto.EmployeeDto;
import com.santander.challengelocal.dto.StatusDto;
import com.santander.challengelocal.mapper.EmployeeMapper;
import com.santander.challengelocal.model.Employee;
import com.santander.challengelocal.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public StatusDto createEmployee(@RequestBody EmployeeDto employee){
    //return employeeMapper.mapFromDto(this.employeeService.employeeCreation(employee));
    return employeeService.employeeCreation(employeeMapper.mapFromDto(employee));
    }

    @PutMapping("/employee")
    @ResponseStatus(HttpStatus.OK)
    public StatusDto modifyEmployee(@RequestParam(name = "id") String id, @Valid @RequestBody EmployeeDto employee){
        return employeeService.modifyEmployee(id, employeeMapper.mapFromDto(employee));
    }

    @GetMapping(value = "/employee")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDto> getEmployees (
            @RequestParam(name = "file", defaultValue = "") String file,
            @RequestParam(name = "firstname", defaultValue = "") String firstName,
            @RequestParam(name = "lastname", defaultValue = "") String lastName,
            @RequestParam(name = "sector", defaultValue = "") String sector
    ) {
        return employeeService.getEmployees(file, firstName, lastName, sector).stream().map(
                employeeMapper::mapFromDao)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/employee",params = {"id"})
    @ResponseStatus(HttpStatus.OK)
    public EmployeeDto getEmployee(@RequestParam("id") String id){
        return employeeMapper.mapFromDao(employeeService.getEmployee(id));
    }





}
