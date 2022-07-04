package com.santander.challengelocal.service;


import com.santander.challengelocal.dto.StatusDto;
import com.santander.challengelocal.exceptions.UserAlreadyCreatedException;
import com.santander.challengelocal.exceptions.UserNotExistException;
import com.santander.challengelocal.model.Employee;
import com.santander.challengelocal.repository.EmployeeMongoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeMongoRepository employeeMongoRepository;

    public EmployeeService(EmployeeMongoRepository employeeMongoRepository){
        this.employeeMongoRepository = employeeMongoRepository;
    }

    public List<Employee> getEmployees(String file, String firstName, String lastName, String sector){
        /*
        if(!file.isBlank()){
            List<Employee> listEmployees = new ArrayList<>();
            Employee employee = employeeMongoRepository.findByFile(file).get();
            listEmployees.add(employee);

        } else if(!sector.isBlank() ){
            employees = employeeMongoRepository.findBySector(sector);
        } else if(!firstName.isBlank()){
            employees = employeeMongoRepository.findByFirstName(firstName);
        }else if(!lastName.isBlank()){
            employees = employeeMongoRepository.findByLastName(lastName);
        }else{
            employees = employeeMongoRepository.findAll();
        }
         */

        /*
        List<Employee> employeesFilter = employees.stream().filter(employee ->
                (file.isBlank() || employee.getFile().equals(file)) &&
                (firstName.isBlank() || employee.getFirstName().equals(firstName)) &&
                (lastName.isBlank() || employee.getFirstName().equals(lastName)) &&
                (sector.isBlank() || employee.getFirstName().equals(sector))
        ).collect(Collectors.toList());

         */

        List<Employee>employees = employeeMongoRepository.findByFilters(file,firstName,lastName,sector);
        if (employees.isEmpty()) throw new UserNotExistException("No se encuentran empleados con esos datos");
        return employees;
    }


    public StatusDto employeeCreation(Employee employee){
        if(!employeeMongoRepository.findByFirstNameAndLastNameAndBirthDateAndAddressAndSector(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getBirthDate(),
                employee.getAddress(),
                employee.getSector()).isEmpty())
        throw new UserAlreadyCreatedException("El empleado ya se encuentra registrado");

        employee = saveAndGenerateFile(employee);
        return new StatusDto(201, "El usuario con legajo "
                + employee.getFile() + " ha sido creado");
    }


    private Employee saveAndGenerateFile(Employee employee){
        employee.setFile(generateFile());
        return employeeMongoRepository.save(employee);
    }

    private String generateFile(){
        long employeeCount = employeeMongoRepository.count() + 1;
        return "A" + String.format("%06d", employeeCount);
    }

    public StatusDto modifyEmployee(String file, Employee employee){
        employeeMongoRepository.findByFile(file).orElseThrow(() ->
                new UserNotExistException("El empleado no existe"));
        Employee employee1 = employeeMongoRepository.save(employee);
        return new StatusDto(201, "Se ha actualizado al empleado "
                + employee1.getFile() + " correctamente.");
    }

    public Employee getEmployee(String id){
        Employee employee = employeeMongoRepository.findByFile(id).get();
        if (employee.getFile() == null)
            throw new UserNotExistException("El empleado no existe");
        return employee;
    }
}
