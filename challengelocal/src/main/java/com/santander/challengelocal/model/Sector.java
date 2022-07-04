package com.santander.challengelocal.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sector {

    @Id
    private String id;
    private String name;
    private String area;
    private String floor;
    private Long lastFile;
}
