package com.santander.challengelocal.model;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDate;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    private String id;
    @Indexed(unique = true)
    private String file;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String sector;



}
