package com.nataliogomes.jobsearchrest.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentModel {
    @Id
    private int id;

    private String name;
    private int age;
}
