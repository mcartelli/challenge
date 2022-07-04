package com.santander.challengelocal.repository;

import com.santander.challengelocal.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeMongoRepository extends MongoRepository<Employee,String> {
    public Optional<Employee> findByFile(String file);
    public List<Employee> findByFirstNameAndLastNameAndBirthDateAndAddressAndSector(String name, String lastName,LocalDate birthDate, String address, String sector);

    @Query(value = "{sector:'?0'}", fields = "{}")
    public List<Employee> findBySector(String sector);

    @Query(value = "{'file' : {$regex : /?0/}, 'firstName' : {$regex : /?1/},'lastName' : {$regex : /?2/}, 'sector' : {$regex : /?3/}}")
    public List<Employee> findByFilters(String file, String firstName, String lastName, String sector);


    public long count();
}
