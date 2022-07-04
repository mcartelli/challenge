package com.santander.challengelocal.dto;

import com.santander.challengelocal.model.Sector;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class EmployeeDto {
    private String file;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String sector;
}
